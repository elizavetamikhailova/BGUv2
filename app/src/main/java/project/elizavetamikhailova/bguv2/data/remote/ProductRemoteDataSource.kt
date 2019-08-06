package project.elizavetamikhailova.bguv2.data.remote

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.retrofit.ProductsApi
import project.elizavetamikhailova.bguv2.retrofit.RetrofitClient
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.Product

class ProductRemoteDataSource{
    private var retrofitClient: RetrofitClient = RetrofitClient()


    fun getProducts(category: Category): Observable<List<Product>> {
        return retrofitClient.getRetrofitRX().create<ProductsApi>(ProductsApi::class.java)
            .getProductsByCategory(category.realTableName!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}