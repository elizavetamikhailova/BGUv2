package project.elizavetamikhailova.bguv2.data.repository

import io.reactivex.Observable
import project.elizavetamikhailova.bguv2.data.local.CategoryLocalDataSource
import project.elizavetamikhailova.bguv2.data.remote.CategoryRemoteDataSource
import project.elizavetamikhailova.bguv2.utils.managers.NetworkManager
import project.elizavetamikhailova.bguv2.view.uimodels.Category
import javax.inject.Inject
import kotlin.collections.ArrayList


class CategoryRepository @Inject constructor(var networkManager: NetworkManager, val localDataSource : CategoryLocalDataSource)  {

    private val remoteDataSource = CategoryRemoteDataSource()

    fun getCategories(): Observable<List<Category>> {

        networkManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getCategories().flatMap {
                    return@flatMap localDataSource.saveCategories(it)
                        .toSingleDefault(it)
                        .toObservable()
                }
            }
        }

        return localDataSource.getCategories()
    }

    private fun search(categories : List<Category>, textToSearch : String) : List<Category>{ //взять лист из локального или удаленного источника
        val list = ArrayList<Category>(categories)
        val lowerTextToSearch = " " + textToSearch.toLowerCase()
        val words = lowerTextToSearch.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        //TODO: tabels in bgu and null????
        words.map { word ->  categories.map { category ->  if(!category.tablesInBgu!!.toLowerCase().contains(word)) list.remove(category)}}
        return list.toList()
    }


    fun getSearchedCategories(categories : List<Category>, textToSearch : String): Observable<List<Category>> {
        return Observable.create { emitter ->
            emitter.onNext(search(categories, textToSearch))
            emitter.onComplete()
        }
    }


}
