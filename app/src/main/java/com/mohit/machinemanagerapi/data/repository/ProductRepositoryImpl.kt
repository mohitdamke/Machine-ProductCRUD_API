package com.mohit.machinemanagerapi.data.repository

import com.mohit.machinemanagerapi.data.remote.ProductApi
import com.mohit.machinemanagerapi.data.remote.mapper.toDto
import com.mohit.machinemanagerapi.data.remote.mapper.toProduct
import com.mohit.machinemanagerapi.domain.model.Product
import com.mohit.machinemanagerapi.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return api.getProducts()
            .products
            .map {
                it.toProduct()
            }
    }

    override suspend fun getProductById(id: Int): Product {
        return api.getProductById(id)
            .toProduct()
    }

    override suspend fun addProduct(product: Product): Product {
        return api.addProduct(
            product.toDto()
        ).toProduct()
    }

    override suspend fun updateProduct(
        id: Int,
        product: Product
    ): Product {
        return api.updateProduct(
            id = id,
            product = product.toDto()
        ).toProduct()
    }

    override suspend fun deleteProduct(id: Int): Product {
        return api.deleteProduct(id)
            .toProduct()
    }
}