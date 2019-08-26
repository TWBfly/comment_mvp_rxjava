package com.cogito.lexiang.ui

import com.cogito.lexiang.R
import com.cogito.lexiang.base.BaseActivity
import com.cogito.lexiang.utils.log.LogUtils

class MainActivity : BaseActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun showView(result: String) {
        LogUtils.e("==showView==$result")
    }

    private lateinit var presenter: MainPresenter

    override fun initPresenter(): MainPresenter {
        presenter = MainPresenter()
        return presenter
    }

    override fun contentLayoutId(): Int = R.layout.activity_main

    override fun initView() {
    }

    override fun initData() {
        presenter.showData()
    }

    override fun initDestroy() {
    }

}
