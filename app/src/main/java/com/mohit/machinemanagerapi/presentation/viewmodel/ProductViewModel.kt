package com.mohit.machinemanagerapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohit.machinemanagerapi.domain.model.Product
import com.mohit.machinemanagerapi.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val allProducts = mutableListOf<Product>()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow("")
    val isError = _isError.asStateFlow()

    fun getProducts(forceRefresh: Boolean = false) {
        if (allProducts.isNotEmpty() && !forceRefresh) {
            _products.value = allProducts.toList()
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                val result = repository.getProducts()

                allProducts.clear()
                allProducts.addAll(result)

                _products.value = result
            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getProductById(id: Int) {
        val localProduct = allProducts.find { it.id == id }
        if (localProduct != null) {
            _selectedProduct.value = localProduct
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                _selectedProduct.value = repository.getProductById(id)
            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchProducts(query: String) {
        _searchQuery.value = query

        if (query.isBlank()) {
            _products.value = allProducts.toList()
            return
        }

        _products.value = allProducts.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                val addedProduct = repository.addProduct(product)

                allProducts.add(0, addedProduct)
                _products.value = allProducts.toList()
            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProduct(id: Int, product: Product) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                val updatedProduct = repository.updateProduct(id, product)

                val index = allProducts.indexOfFirst { it.id == id }
                allProducts[index] = updatedProduct

                _products.value = allProducts.toList()

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                repository.deleteProduct(id)

                allProducts.removeAll { it.id == id }
                _products.value = allProducts.toList()

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went Wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
