package com.example.mvvmprac.model

import com.example.mvvmprac.model.enum.KakaoSearchSortEnum
import com.example.mvvmprac.model.response.ImageSearchResponse
import io.reactivex.Single

interface DataModel {
    fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int) : Single<ImageSearchResponse>
}