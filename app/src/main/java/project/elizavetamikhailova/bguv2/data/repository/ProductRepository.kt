package project.elizavetamikhailova.bguv2.data.repository

import android.util.Log
import io.reactivex.Observable
import project.elizavetamikhailova.bguv2.data.local.ProductLocalDataSource
import project.elizavetamikhailova.bguv2.data.remote.CategoryRemoteDataSource
import project.elizavetamikhailova.bguv2.data.remote.ProductRemoteDataSource
import project.elizavetamikhailova.bguv2.utils.managers.NetworkManager
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(var networkManager: NetworkManager, val localDataSource: ProductLocalDataSource) {
    private val remoteDataSource = ProductRemoteDataSource()

    fun getProductsByCategory(category: Category): Observable<List<Product>> {

        networkManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getProducts(category).flatMap {
                    it.map { product -> product.categoryName = category.realTableName!! }
                    return@flatMap localDataSource.saveProducts(it, category)
                        .toSingleDefault(it)
                        .toObservable()
                }
            }
        }

        return localDataSource.getProductsByCategory(category)
    }
}