package project.elizavetamikhailova.bguv2.view.ui.view_models.add_food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.local.UserProductDataSource
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct
import javax.inject.Inject

class UserProductsViewModel @Inject constructor(val userProductDataSource: UserProductDataSource) : ViewModel()  {

    private val compositeDisposable = CompositeDisposable()

    var userProducts = MutableLiveData<List<UserProduct>>()

    fun loadUserProducts(day: Day){
        //add compositeDisposable
            compositeDisposable.add(userProductDataSource.getUserProductByDay(day)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableMaybeObserver<List<UserProduct>>() {
                    override fun onSuccess(t: List<UserProduct>) {
                        userProducts.value = t
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
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