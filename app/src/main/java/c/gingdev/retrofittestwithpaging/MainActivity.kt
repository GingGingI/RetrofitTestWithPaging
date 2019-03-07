package c.gingdev.retrofittestwithpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import c.gingdev.retrofittestwithpaging.adapter.itemAdapter
import c.gingdev.retrofittestwithpaging.viewModels.listViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listVM by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(listViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = itemAdapter().also {
            listVM.itemlist.observe(this, Observer(it::submitList))
            listVM.adapter(it)
        }
    }
}
