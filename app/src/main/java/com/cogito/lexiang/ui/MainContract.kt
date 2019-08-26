package com.cogito.lexiang.ui

import com.cogito.lexiang.base.BasePresenter
import com.cogito.lexiang.base.BaseView

/**
 * Created by Tang on 2019/8/23
 */
interface MainContract {
    interface View : BaseView{
       fun showView(result: String)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun showData()
    }
}