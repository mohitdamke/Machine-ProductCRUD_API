package com.mohit.machinemanagerapi.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi.domain.model.Product
import com.mohit.machinemanagerapi.presentation.viewmodel.ProductViewModel

@Composable
fun AddProductScreen(
    viewModel: ProductViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Add Product")

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
                viewModel.addProduct(
                    Product(
                        id = 0,
                        title = title,
                        description = description,
                        price = price.toDoubleOrNull() ?: 0.0,
                        category = category,
                        thumbnail = ""
                    )
                )
                onBack()
            }
        ) {
            Text("Add Product")
        }
    }
}