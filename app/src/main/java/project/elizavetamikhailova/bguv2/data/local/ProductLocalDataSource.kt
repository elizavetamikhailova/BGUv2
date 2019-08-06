package project.elizavetamikhailova.bguv2.data.local

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.local.db.dao.ProductDao
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import java.util.concurrent.Callable
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(val productDao: ProductDao){
    fun getProductsByCategory(category : Category) : Observable<List<Product>> {
        return productDao.getByCategoryName(category.realTableName!!).toObservable()
    }

    @SuppressLint("CheckResult") //memory leak?
    fun saveProducts(list: List<Product>, category: Category) : Completable {
        deleteAllProducts().subscribeWith(object : DisposableCompletableObserver() {
            override fun onComplete() {
                list.map { insertProducts(it) }
            }

            override fun onError(e: Throwable) {

            }
        })

        return Single.just(1).toCompletable()
    }

    private fun insertProducts(product: Product){

        val callable = Callable<Void> {
            productDao.insert(product)
            null
        }
        Completable.fromCallable(callable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    private fun deleteAllProducts() : Completable {
        val callable = Callable<Void> {
            productDao.deleteAll()
            null
        }
        return Completable.fromCallable(callable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}