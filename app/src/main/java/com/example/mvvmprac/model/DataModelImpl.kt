package com.example.mvvmprac.model

import com.example.mvvmprac.model.enum.KakaoSearchSortEnum
import com.example.mvvmprac.model.response.ImageSearchResponse
import com.example.mvvmprac.model.service.KakaoSearchService
import io.reactivex.Single

class DataModelImpl(private val service: KakaoSearchService) : DataModel {
    private val KAKAO_APP_KEY = "b0dd017925389a49ef2e42c69acc07e5"

    override fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int) : Single<ImageSearchResponse>{
        return service.searchImage(auth= "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    }
}