package project.elizavetamikhailova.bguv2.view.ui.view_models.statistic

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.local.UserProductDataSource
import project.elizavetamikhailova.bguv2.helpers.statistic.DateSorter
import project.elizavetamikhailova.bguv2.helpers.statistic.StatisticComposite
import project.elizavetamikhailova.bguv2.helpers.statistic.WholeDayProductsSummary
import project.elizavetamikhailova.bguv2.view.uimodels.*
import javax.inject.Inject

class WeekStatisticViewModel @Inject constructor(val userProductDataSource: UserProductDataSource) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var week = mutableListOf<WholeDayProductsSummary>()
    var stat = MutableLiveData<MutableList<StatisticComposite>>()
    val daySorter = DateSorter()
    val user = User()

    private fun getWeek(day: Day): List<Day> {
        return daySorter.setWeek(day)
    }

    fun loadUserProduct(day: Day) {
        for (it in getWeek(day)) {
            Log.i("KKALS day", it.toString())
            compositeDisposable.add(
                userProductDataSource.getUserProductByDay(it)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableMaybeObserver<List<UserProduct>>() {
                        override fun onSuccess(data: List<UserProduct>) {
                            val wholeDayProductsSummary = WholeDayProductsSummary(data)
                            if(data.isEmpty()){
                                Log.i("KKALS data", "$data $it")
                                wholeDayProductsSummary.day = it
                            }
                            week.add(wholeDayProductsSummary)
                            Log.i("KKALS", week.toString())
                            stat.value = getStat(user, daySorter.sortWeek(week))
                        }

                        override fun onComplete() {
                        }

                        override fun onError(e: Throwable) {

                        }

                    }
                    )
            )
        }

    }

    private fun getStat(user: User, week: MutableList<WholeDayProductsSummary>): MutableList<StatisticComposite>{
        Log.i("KKALS sorted", week.toString())
        Log.i("KKALS getStat", week.toString())
        val statisticComposite = mutableListOf<StatisticComposite>()
        for (day in week){
            statisticComposite.add(StatisticComposite(day, user))
        }

        return statisticComposite
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}