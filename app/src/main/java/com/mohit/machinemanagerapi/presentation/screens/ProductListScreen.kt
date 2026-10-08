package com.mohit.machinemanagerapi.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi.presentation.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel,
    onProductClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    val products by viewModel.products.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Products")

        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::searchProducts,
            label = { Text("Search") }
        )

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (isError.isNotEmpty()) {
            Text("Error: $isError")
        }

        LazyColumn {
            items(products) { product ->

                Text(
                    text = "${product.title} - $${product.price}",
                    modifier = Modifier.clickable {
                        onProductClick(product.id)
                    }
                )

                Button(
                    onClick = { onDeleteClick(product.id) }
                ) {
                    Text("Delete")
                }
            }
        }

        Button(onClick = onAddClick) {
            Text("Add Product")
        }
    }
}