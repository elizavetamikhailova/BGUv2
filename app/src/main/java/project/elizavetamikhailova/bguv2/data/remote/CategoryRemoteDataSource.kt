package project.elizavetamikhailova.bguv2.data.remote

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.retrofit.ProductsApi
import project.elizavetamikhailova.bguv2.retrofit.RetrofitClient
import project.elizavetamikhailova.bguv2.view.uimodels.Category

class CategoryRemoteDataSource {

    private var retrofitClient: RetrofitClient = RetrofitClient()


    fun getCategories(): Observable<List<Category>> {
        return retrofitClient.getRetrofitRX().create<ProductsApi>(ProductsApi::class.java)
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

