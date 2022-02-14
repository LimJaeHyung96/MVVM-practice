package com.example.mvvmprac.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.mvvmprac.util.SingleLiveEvent
import com.example.mvvmprac.util.SnackbarMessage
import com.example.mvvmprac.util.SnackbarMessageString
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseKotlinViewModel : ViewModel() {

    private val snackbarMessage = SnackbarMessage()
    private val snackbarMessageString = SnackbarMessageString()

    //Rxjava의 observing을 위한 부분
    //addDisposable을 이용하여 추가
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable : Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun showSnackbar(stringResourceID:Int){
        snackbarMessage.value = stringResourceID
    }

    fun showSnackbar(str:String){
        snackbarMessageString.value = str
    }

    fun observeSnackbarMessage(lifecycleOwner: LifecycleOwner, ob:(Int) -> Unit){
        snackbarMessage.observe(lifecycleOwner, ob)
    }

    fun observeSnackbarMessageStr(lifecycleOwner: LifecycleOwner, ob:(String) -> Unit){
        snackbarMessageString.observe(lifecycleOwner, ob)
    }
}