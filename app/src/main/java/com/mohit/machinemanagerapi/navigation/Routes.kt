package com.mohit.machinemanagerapi.navigation

object Routes {

    const val Products = "products"

    const val ProductDetail = "product_detail/{productId}"

    const val AddProduct = "add_product"

    const val EditProduct = "edit_product/{productId}"

    fun productDetail(id: Int): String {
        return "product_detail/$id"
    }

    fun editProduct(id: Int): String {
        return "edit_product/$id"
    }
}