package com.mohit.machinemanagerapi.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi.presentation.viewmodel.ProductViewModel

@Composable
fun EditProductScreen(
    productId: Int,
    viewModel: ProductViewModel,
    onBack: () -> Unit
) {
    val selectedProduct by viewModel.selectedProduct.collectAsState()

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    if (selectedProduct == null) {
        CircularProgressIndicator()
        return
    }

    var title by remember(selectedProduct) { mutableStateOf(selectedProduct!!.title) }
    var description by remember(selectedProduct) { mutableStateOf(selectedProduct!!.description) }
    var price by remember(selectedProduct) { mutableStateOf(selectedProduct!!.price.toString()) }
    var category by remember(selectedProduct) { mutableStateOf(selectedProduct!!.category) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Edit Product")

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") }
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") }
        )

        Button(
            onClick = {
                viewModel.updateProduct(
                    id = productId,
                    product = selectedProduct!!.copy(
                        title = title,
                        description = description,
                        price = price.toDoubleOrNull() ?: 0.0,
                        category = category
                    )
                )
                onBack()
            }
        ) {
            Text("Update Product")
        }
    }
}