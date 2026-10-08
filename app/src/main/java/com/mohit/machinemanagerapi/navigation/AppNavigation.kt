package com.mohit.machinemanagerapi.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohit.machinemanagerapi.presentation.screens.AddProductScreen
import com.mohit.machinemanagerapi.presentation.screens.EditProductScreen
import com.mohit.machinemanagerapi.presentation.screens.ProductDetailScreen
import com.mohit.machinemanagerapi.presentation.screens.ProductListScreen
import com.mohit.machinemanagerapi.presentation.viewmodel.ProductViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: ProductViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Products
    ) {

        composable(Routes.Products) {
            ProductListScreen(
                viewModel = viewModel,
                onProductClick = {
                    navController.navigate(Routes.productDetail(it))
                },
                onAddClick = {
                    navController.navigate(Routes.AddProduct)
                },
                onDeleteClick = {
                    viewModel.deleteProduct(it)
                }
            )
        }

        composable(Routes.ProductDetail) { entry ->

            val id = entry.arguments?.getString("productId")?.toIntOrNull() ?: 0

            ProductDetailScreen(
                productId = id,
                viewModel = viewModel,
                onEditClick = {
                    navController.navigate(Routes.editProduct(id))
                },
                onDeleteClick = {
                    viewModel.deleteProduct(id)
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.AddProduct) {
            AddProductScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.EditProduct) { entry ->

            val id = entry.arguments?.getString("productId")?.toIntOrNull() ?: 0

            EditProductScreen(
                productId = id,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}