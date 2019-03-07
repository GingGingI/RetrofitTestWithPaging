package c.gingdev.retrofittestwithpaging.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import c.gingdev.retrofittestwithpaging.adapter.itemAdapter
import c.gingdev.retrofittestwithpaging.dataSource.itemDataSource
import c.gingdev.retrofittestwithpaging.dataSource.itemDataSpirceFactpry
import c.gingdev.retrofittestwithpaging.models.listData

class listViewModel: ViewModel {
    private var adapter: itemAdapter? = null

    val itemlist: LiveData<PagedList<listData>>
    val dataSource: LiveData<PageKeyedDataSource<Int, listData>>

    constructor() {
        val dataSourceFactory = itemDataSpirceFactpry()
        dataSource = dataSourceFactory.getItemliveDataSource()

        val pagedListConfig: PagedList.Config
            = (PagedList.Config.Builder())
            .setEnablePlaceholders(false)
            .setPageSize(itemDataSource.PAGE_SIZE).build()

        itemlist = (LivePagedListBuilder(dataSourceFactory, pagedListConfig).build())
    }

    fun adapter(): itemAdapter
            = adapter ?: itemAdapter().also { adapter = it }
    fun adapter(adapter: itemAdapter) { this.adapter = adapter }
}