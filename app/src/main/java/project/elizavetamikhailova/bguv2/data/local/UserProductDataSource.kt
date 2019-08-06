package project.elizavetamikhailova.bguv2.data.local

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import project.elizavetamikhailova.bguv2.data.local.db.dao.UserProductDao
import project.elizavetamikhailova.bguv2.view.uimodels.Day
import project.elizavetamikhailova.bguv2.view.uimodels.UserProduct
import java.util.concurrent.Callable


import javax.inject.Inject

class UserProductDataSource @Inject constructor(val userProductDao: UserProductDao){

    fun getUserProductByDay(day: Day) : Maybe<List<UserProduct>>{
        return userProductDao.getByDay(day)
    }

    fun getUserProductByWeek(week: List<Day>) : Maybe<List<UserProduct>>{
        return userProductDao.getByWeek(week[0], week[1], week[2], week[3], week[4], week[5], week[6])
    }

    fun insertProduct(userProducts: UserProduct) : Observable<Long> {
       return Observable.fromCallable { userProductDao.insert(userProducts) }
    }

    fun deleteAllProducts() : Completable {
        val callable = Callable<Void> {
            userProductDao.deleteAll()
            null
        }
        return Completable.fromCallable(callable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}