package com.example.mvvmprac.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mvvmprac.model.DataModel
import com.example.mvvmprac.model.enum.KakaoSearchSortEnum
import com.example.mvvmprac.base.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model : DataModel) : BaseKotlinViewModel() {
    fun getImageSearch(query: String, page: Int, size:Int){
        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run{}
                if(documents.size > 0){
                    _imageSearchResponseLiveData.postValue(this)
                }
            }, {
                Log.d(TAG,"response error, message : ${it.message}")
            }))
    }
}