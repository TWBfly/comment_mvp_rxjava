package com.cogito.lexiang.data.api

import com.cogito.lexiang.rx.RxSchedulers
import com.hwgl.sz.util.RetrofitFactory

/**
 * Created by Tang on 2019/8/23
 */
class ApiService private constructor() {
    private val api: Api = RetrofitFactory.instance.create(Api::class.java)

    private object ApiServiceHolder {
        val S_INSTANCE = ApiService()
    }

    companion object {
        val instance: ApiService
            get() = ApiServiceHolder.S_INSTANCE
    }


    fun test() = api.getTest().compose(RxSchedulers.composeFlowable())
}