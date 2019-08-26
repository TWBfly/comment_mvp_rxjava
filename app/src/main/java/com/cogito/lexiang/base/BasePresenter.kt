package com.cogito.lexiang.base

import com.cogito.lexiang.data.api.ApiService
import java.lang.ref.Reference
import java.lang.ref.SoftReference




/**
 * Created by Tang on 2019/8/23
 */
abstract class BasePresenter<V> {
    //View接口类型的软引用
    protected var mViewRef: Reference<V>? = null

    fun attachView(view: V) {
        //建立关系
        mViewRef = SoftReference<V>(view)
    }

    protected fun getView(): V {
        return mViewRef?.get()!!
    }

    fun isViewAttached(): Boolean {
        return mViewRef != null && mViewRef?.get() != null
    }

    fun detachView() {
        if (mViewRef != null) {
            mViewRef?.clear()
        }
    }

    abstract fun takeView(view: V)
    fun apiService(): ApiService = ApiService.instance

}