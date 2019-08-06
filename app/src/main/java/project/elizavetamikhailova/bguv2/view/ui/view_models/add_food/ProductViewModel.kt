package project.elizavetamikhailova.bguv2.view.ui.view_models.add_food

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.repository.ProductRepository
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import javax.inject.Inject

class ProductViewModel @Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    val isLoading = ObservableField(false)

    var products = MutableLiveData<List<Product>>()

    var protectedProducts : MutableList<Product> = mutableListOf()

    val connect = ObservableField(true)

    private val compositeDisposable = CompositeDisposable()

    fun loadProducts(category: Category) {
        connect.set(productRepository.networkManager.isConnectedToInternet)
        isLoading.set(true)
        compositeDisposable.add(productRepository.getProductsByCategory(category)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<List<Product>>() {
                override fun onError(e: Throwable) {
                }
                override fun onNext(data: List<Product>) {
                    products.value = data
                    Log.i("KKALS", data.toString())
                    protectedProducts.addAll(data)
                }
                override fun onComplete() {
                    isLoading.set(false)
                    connect.set(true)
                }
            }))
    }
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}