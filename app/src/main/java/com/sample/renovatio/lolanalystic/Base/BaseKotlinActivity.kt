package com.sample.renovatio.lolanalystic.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar


/**
 *     BaseKotlinActivity<ActivitySbsMainBinding>
 *     와 같이 상속 받을 때, ActivitySbsMainBinding 과 같은 파일이 자동 생성되지 않는 다면
 *     1. 해당 액티비티의 레이아웃이 <layout></layout>으로 감싸져 있는지 확인
 *     2. 다시 빌드 수행 or 클린 빌드 후 다시 빌드 수행
 *     3. 이름 확인: sbs_main_activity => ActivitySbsMainBinding
 * */
abstract class BaseKotlinActivity<T:ViewDataBinding, R: BaseKotlinViewModel> : AppCompatActivity(){
    lateinit var viewDataBinding: T

    /**
     * @layoutResourceId setContentView 로 호출할 layout 의 리소스 id
     * */
    abstract val layoutResourceId: Int

    /**
     * @viewModel 뷰모델로 쓰일 변수
     * */
    abstract val viewModel: R

    /**
     * 레이아웃을 띄운 직후 호출
     * 뷰나 애기티비의 속성 등을 초기화
     * */

    abstract fun initStartView()

    /**
     * 두번째로 호출
     * 데이터 바인딩 및 rxjava 설정
     * */
    abstract fun initDataBinding()

    /**
     * 바인딩 이후에 할 일을 여기에 구현
     * 그 외에 설정할 것이 있으면 이곳에서 설정
     * 클릭 리스너도 이곳에서 설정
     * */

    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

//        snackbarObserving()
        initStartView()
        initDataBinding()
        initAfterBinding()
    }

//    private fun snackbarObserving() {
//        viewModel.observeSnackbarMessage(this) {
//            Snackbar.make(findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
//        }
//        viewModel.observeSnackbarMessageStr(this){
//            Snackbar.make(findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
//        }
//    }

}