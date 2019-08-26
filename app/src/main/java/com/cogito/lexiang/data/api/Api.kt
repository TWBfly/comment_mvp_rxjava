package com.cogito.lexiang.data.api

import com.cogito.lexiang.bean.TestBean
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by Tang on 2019/8/23
 */
interface Api {
    @GET("mock1")
     fun getTest():Flowable<TestBean>
}