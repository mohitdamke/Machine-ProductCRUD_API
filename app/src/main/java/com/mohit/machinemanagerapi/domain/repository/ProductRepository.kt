package com.mohit.machinemanagerapi.domain.repository

import com.mohit.machinemanagerapi.domain.model.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product

    suspend fun addProduct(product: Product): Product

    suspend fun updateProduct(id: Int, product: Product): Product

    suspend fun deleteProduct(id: Int): Product
}