package project.elizavetamikhailova.bguv2.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun getRetrofitRX(): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("http://4bgu.tk/api/test/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.build()
    }
}