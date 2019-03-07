package c.gingdev.retrofittestwithpaging.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import c.gingdev.retrofittestwithpaging.models.listData

class itemDataSpirceFactpry: DataSource.Factory<Int, listData>() {
    private val itemList: MutableLiveData<PageKeyedDataSource<Int, listData>>
            = MutableLiveData()

    override fun create(): DataSource<Int, listData> {
        val itemDataSource = itemDataSource()
        itemList.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemliveDataSource() : MutableLiveData<PageKeyedDataSource<Int, listData>>
        = itemList
}