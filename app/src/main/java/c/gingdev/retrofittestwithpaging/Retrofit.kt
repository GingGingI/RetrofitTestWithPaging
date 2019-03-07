package c.gingdev.retrofittestwithpaging

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class Retrofit {
    private val retrofit = retrofitInjection
    .provideRetrofit("http://10.0.2.2:3000")

    fun getRetrofitService() : retrofitService
        = retrofit.create(retrofitService::class.java)

    companion object {
        private var retrofitInstance: c.gingdev.retrofittestwithpaging.Retrofit? = null

        fun getInstance(): c.gingdev.retrofittestwithpaging.Retrofit
            = retrofitInstance ?: synchronized(this) {
            retrofitInstance ?: buildRetrofitInstance().also { retrofitInstance = it }
        }

        private fun buildRetrofitInstance(): c.gingdev.retrofittestwithpaging.Retrofit
            = Retrofit()
    }

    interface retrofitService {
        @GET("/")
        fun getDatas(@Query("from") from: Int,
                     @Query("to") to: Int): Call<JsonArray>
    }
}