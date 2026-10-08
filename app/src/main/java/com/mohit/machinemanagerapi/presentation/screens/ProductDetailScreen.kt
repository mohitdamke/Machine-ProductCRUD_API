package com.mohit.machinemanagerapi.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi.presentation.viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductViewModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val product by viewModel.selectedProduct.collectAsState()

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    if (product == null) {
        CircularProgressIndicator()
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(product!!.title)
        Text("Category: ${product!!.category}")
        Text("Price: $${product!!.price}")
        Text(product!!.description)

        Button(onClick = onEditClick) {
            Text("Edit")
        }

        OutlinedButton(onClick = onDeleteClick) {
            Text("Delete")
        }
    }
}