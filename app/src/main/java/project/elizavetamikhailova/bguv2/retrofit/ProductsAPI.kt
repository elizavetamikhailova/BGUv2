package project.elizavetamikhailova.bguv2.retrofit

import io.reactivex.Observable
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.Product

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("get-all-categories")
    fun getAllCategories(): Observable<List<Category>>

    @GET("get-products-by-category")
    fun getProductsByCategory(@Query("category") categoryName: String): Observable<List<Product>>

    @GET("get-all-products")
    fun getAllProducts(): Observable<List<Product>>
}