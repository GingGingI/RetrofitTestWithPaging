package c.gingdev.retrofittestwithpaging.dataSource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import c.gingdev.retrofittestwithpaging.Retrofit
import c.gingdev.retrofittestwithpaging.models.listData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class itemDataSource: PageKeyedDataSource<Int, listData>() {
    protected fun Any?.notNull(f: ()-> Unit) {
        if (this != null) f()
    }
    protected fun Any?.isNull(f: ()-> Unit) {
        if (this == null) f()
    }

    companion object {
        val PAGE_SIZE = 30
        val FIRST_PAGE = 1
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, listData>) {
        Retrofit.getInstance()
            .getRetrofitService()
            .getDatas(FIRST_PAGE, PAGE_SIZE)
            .enqueue(object: Callback<JsonArray> {
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.e("An Error Founded", t.message)
                }

                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    response.body().notNull {
                        Log.i("item", "getItem")
                        callback.onResult(getDataList(response.body()), null, FIRST_PAGE + 1)
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, listData>) {
        Retrofit.getInstance()
            .getRetrofitService()
            .getDatas(params.key, PAGE_SIZE)
            .enqueue(object: Callback<JsonArray> {
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.e("An Error Founded", t.message)
                }

                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    response.body().notNull {
                        val Key = params.key + 1
                        Log.i("item", "getItem Number $Key")
                        callback.onResult(getDataList(response.body()), Key)
                    }
                }
            })
    }
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, listData>) {
        Retrofit.getInstance()
            .getRetrofitService()
            .getDatas(params.key, PAGE_SIZE)
            .enqueue(object: Callback<JsonArray> {
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.e("An Error Founded", t.message)
                }

                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    val Key = if (params.key > 1) params.key - 1 else null
                    response.body().notNull {
                        Log.i("item", "getItem Number $Key")
                        callback.onResult(getDataList(response.body()), Key)
                    }
                }

            })
    }

    private fun getDataList(data: JsonArray?): MutableList<listData> {
        val list: ArrayList<listData> = ArrayList()
        data!!.iterator<JsonObject>().forEach { list.add(listData(it.get("data").asString)) }
        return list.toMutableList()
    }

    operator fun <T> JsonArray.iterator(): Iterator<T>
        = (0 until size()).asSequence().map { get(it) as T }.iterator()
}