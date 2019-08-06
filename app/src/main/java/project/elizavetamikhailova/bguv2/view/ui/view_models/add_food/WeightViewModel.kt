package project.elizavetamikhailova.bguv2.view.ui.view_models.add_food

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.local.UserProductDataSource
import project.elizavetamikhailova.bguv2.helpers.add_food.WeightHelper
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import project.elizavetamikhailova.bguv2.view.uimodels.Product
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct
import javax.inject.Inject

class WeightViewModel @Inject constructor( val userProductDataSource: UserProductDataSource) : ViewModel() {

    var product = MutableLiveData<Product>()

    var currentProduct = ObservableField<Product>()

    var weight = ObservableField<Int>()

    var isInserted = MutableLiveData<Boolean>()

    var day = Day()

    private val compositeDisposable = CompositeDisposable()

    fun updateValues(textWeight: CharSequence){
        val weightHelper = WeightHelper()
        if(textWeight.toString() != ""){
            weight.set(Integer.valueOf(textWeight.toString()))
            product.value = (weightHelper.updateProductValueByWeight(currentProduct, weight))
        }else{
            product.value = currentProduct.get()
        }
    }

    fun saveUserProduct(){
        compositeDisposable.add(userProductDataSource.insertProduct(UserProduct(day = day, product = product.value!!, weight = weight.get()!!.toString()))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                isInserted.value = true
            })
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}