package project.elizavetamikhailova.bguv2.view.ui.view_models.add_food

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.repository.CategoryRepository
import project.elizavetamikhailova.bguv2.view.uimodels.Category

import javax.inject.Inject

class CategoryViewModel @Inject constructor(var categoryRepository: CategoryRepository) : ViewModel() {

    val isLoading = ObservableField(false)

    var categories = MutableLiveData<List<Category>>()

    var protectedCategories : MutableList<Category> = mutableListOf()

    val connect = ObservableField(true)

    private val compositeDisposable = CompositeDisposable()

    fun loadCategories() {
        connect.set(categoryRepository.networkManager.isConnectedToInternet)
        isLoading.set(true)
        compositeDisposable.add(categoryRepository.getCategories()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<List<Category>>() {
                override fun onError(e: Throwable) {
                }
                override fun onNext(data: List<Category>) {
                    categories.value = data
                    protectedCategories.addAll(data)
                }
                override fun onComplete() {
                    isLoading.set(false)
                    connect.set(true)
                }
            }))
    }

//    fun searchCategories(text : CharSequence){
//        val textToSearch = text.toString()
//        if(textToSearch != "" && categories.value != null){
//            isLoading.set(true)
//            categories.value = protectedCategories
//            compositeDisposable.add(categoryRepository.getSearchedCategories(categories.value!!, textToSearch)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object  : DisposableObserver<List<Category>>(){
//                    override fun onComplete() {
//                        isLoading.set(false)
//                    }
//                    override fun onNext(t: List<Category>) {
//                        categories.value = t
//                    }
//                    override fun onError(e: Throwable) {
//                        isLoading.set(false)
//                    }
//                }))
//        }else{
//            loadCategories()
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}