package com.mohit.machinemanagerapi.data.remote

import com.mohit.machinemanagerapi.data.remote.dto.ProductDto
import com.mohit.machinemanagerapi.data.remote.dto.ProductResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductResponseDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id : Int
    ) : ProductDto

    @POST("products/add")
    suspend fun addProduct(
        @Body product: ProductDto
    ) : ProductDto

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id : Int,
        @Body product: ProductDto
    ): ProductDto

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id : Int
    ): ProductDto

}









