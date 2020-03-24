package kz.app.anatoliy.translator

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TranslatorApi {
    companion object Factory{
        fun create(): TranslatorApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://simplechat2345.herokuapp.com/")
                .build()

            return retrofit.create(TranslatorApi::class.java)
        }
    }

    @GET("index.json")
    fun get(): Observable<List<DataElement>>
}
