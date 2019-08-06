package project.elizavetamikhailova.bguv2.view.ui.view_models.statistic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.local.UserProductDataSource
import project.elizavetamikhailova.bguv2.helpers.enums.NutrientType
import project.elizavetamikhailova.bguv2.helpers.statistic.WholeDayProductsSummary
import project.elizavetamikhailova.bguv2.view.uimodels.*
import javax.inject.Inject

class StatisticViewModel @Inject constructor(val userProductDataSource: UserProductDataSource) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var protsStatistic = MutableLiveData<DayStatistic>()
    var fatsStatistic = MutableLiveData<DayStatistic>()
    var carbsStatistic = MutableLiveData<DayStatistic>()
    var kcalsStatistic = MutableLiveData<KcalsStatistic>()

    fun loadUserProducts(day: Day){
        compositeDisposable.add(userProductDataSource.getUserProductByDay(day)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableMaybeObserver<List<UserProduct>>() {
                override fun onSuccess(data: List<UserProduct>) {
                    val user = User()
                    val wholeDayProductsSummary = WholeDayProductsSummary(data)
                    user.apply {
                        wholeDayProductsSummary.apply {
                            protsStatistic.value = DayStatistic(nutrientType = NutrientType.PROTS, _nutrientSummary = allProts,
                                _normOfNutrient = normOfProts, normOfKcals = normOfKcals)
                            fatsStatistic.value = DayStatistic(nutrientType = NutrientType.FATS, _nutrientSummary = allFats,
                                _normOfNutrient = normOfFats, normOfKcals = normOfKcals)
                            carbsStatistic.value = DayStatistic(nutrientType = NutrientType.CARBS, _nutrientSummary = allCarbs,
                                _normOfNutrient = normOfCarbs, normOfKcals = normOfKcals)
                            kcalsStatistic.value = KcalsStatistic(_summary = allKcals, normOfKcals = normOfKcals)
                        }
                    }
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