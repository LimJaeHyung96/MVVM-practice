package com.example.mvvmprac.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * BaseKotlinActivity<ActivitySbsMainBinding>
 * 와 같이 상속 받을 때, ActivitySbsMainBinding 과 같은 파일이 자동생성되지 않는다면
 * 1. 해당 엑티비티의 레이아웃이 <layout></layout> 으로 감싸져 있는지 확인
 * 2. 다시 빌드 수행 or 클린 빌드 후 다시 빌드 수행
 * 3. 이름 확인 : sbs_main_activity => ActivitySbsMainBinding
 */

abstract class BaseKotlinActivity<T : ViewDataBinding, R : BaseKotlinViewModel> : AppCompatActivity() {
    lateinit var viewDataBinding: T

    //setContentView로 호출할 layout의 Id
    abstract val layoutResourceId : Int

    //ViewModel로 쓰일 변수
    abstract val viewModel : R

    //레이아웃을 띄운 직 후 호출
    //뷰나 액티비티의 속성들을 초기화
    abstract fun initStartView()

    //두 번째로 호출
    //데이터 바인딩 및 Rxjava 설정
    abstract fun initDataBinding()

    //바인딩 이후 할 일을 여기서 구현
    //클릭 리스너도 여기서 설정
    //그 외에 설정할 것이 있으면 이 곳에서 설정
    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        initStartView()
        initDataBinding()
        initAfterBinding()
    }
}