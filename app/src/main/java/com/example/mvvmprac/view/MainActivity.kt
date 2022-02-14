package com.example.mvvmprac.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvmprac.MainSearchRecyclerViewAdapter
import com.example.mvvmprac.R
import com.example.mvvmprac.base.BaseKotlinActivity
import com.example.mvvmprac.databinding.ActivityMainBinding
import com.example.mvvmprac.viewModel.MainViewModel
import io.reactivex.internal.operators.observable.ObservableElementAt
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding,MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel:MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter: MainSearchRecyclerViewAdapter by inject()

    private val recyclerView : RecyclerView = findViewById(R.id.main_activity_search_recycler_view)
    private val button = findViewById<Button>(R.id.main_activity_search_button)
    private val textView = findViewById<EditText>(R.id.main_activity_search_text_view)

    override fun initStartView() {
        recyclerView.run {
            adapter = mainSearchRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(3,1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.imageSearchResponseLiveData.observe(this, Observer {
            it.documents.forEach { document ->
                mainSearchRecyclerViewAdapter.addImageItem(document.image_url, document.doc_url)
            }
            mainSearchRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        button.setOnClickListener {
            viewModel.getImageSearch(textView.text.toString(),1,80)
        }
    }
}
