package c.gingdev.retrofittestwithpaging

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInjection {
    fun provideRetrofit(baseUrl: String): Retrofit
        = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}