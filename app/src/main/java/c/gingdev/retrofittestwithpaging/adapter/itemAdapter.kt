package c.gingdev.retrofittestwithpaging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.retrofittestwithpaging.R
import c.gingdev.retrofittestwithpaging.models.listData
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class itemAdapter: PagedListAdapter<listData, itemAdapter.itemViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder
        = itemViewHolder(parent)

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object: DiffUtil.ItemCallback<listData>() {
            /** data list에 변경사항이 있을경우 예(: itemAdd)
             * PageListAdapter가 diffCallback을 사용하여
             * 이전데이터와 어느것이 다른지 확인 후.
             * 데이터 rebind 및 Animate*/
            override fun areItemsTheSame(oldItem: listData, newItem: listData): Boolean
                    = oldItem.dataNumber == newItem.dataNumber
            /** 데이터가 동일한지 검사.*/
            override fun areContentsTheSame(oldItem: listData, newItem: listData): Boolean
                    = oldItem == newItem
        }
    }

    class itemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)) {
        private val textView = itemView.text

        var listData: listData? = null

        fun bindTo(listData: listData?) {
            this.listData = listData

            textView.text = listData?.dataNumber.toString()
        }
    }
}