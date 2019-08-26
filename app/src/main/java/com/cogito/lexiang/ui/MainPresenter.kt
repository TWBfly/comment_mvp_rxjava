package com.cogito.lexiang.ui

import com.cogito.lexiang.bean.TestBean
import com.cogito.lexiang.rx.BaseSubscriber
import com.cogito.lexiang.utils.log.LogUtils

/**
 * Created by Tang on 2019/8/23
 */
class MainPresenter: MainContract.Presenter() {
    private lateinit var mainView: MainContract.View

    override fun showData() {
        LogUtils.e("==showData==")
        apiService().test().subscribe(object : BaseSubscriber<TestBean>() {
            override fun _onNext(t: TestBean) {
                LogUtils.e("_onNext=")
                mainView.showToast()

                mainView.showView( t.data.result)
            }

            override fun _onError(message: String?) {
                super._onError(message)
                LogUtils.e("error=="+message)
            }
        })
    }

   override fun takeView(view: MainContract.View) {
       LogUtils.e("==takeView==")
        this.mainView = view
    }
}