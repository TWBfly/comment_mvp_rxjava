package com.hwgl.sz.util

import com.cogito.lexiang.MyApplication
import com.cogito.lexiang.utils.getBaseUrl
import com.readystatesoftware.chuck.ChuckInterceptor
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Tang on 2018/11/2
 */
class RetrofitFactory private constructor() {
    /**
     *  单例实现
     */
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    //    val FORM_CONTENT_TYPE = MediaType.parse("application/json;charset=utf-8")
    private val interceptor: Interceptor
    private val retrofit: Retrofit

    //初始化
    init {
        //通用拦截
        interceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .removeHeader("Content-Type")
                .header("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")
//                .addHeader("token","b13756f774a9410393ccb4f95a5053d3")
                .method(chain.request().method, chain.request().body)
                .build()

            chain.proceed(request)
        }

        //Retrofit实例化
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()
    }

    /**
     *  OKHttp创建
     *  https://github.com/JessYanCoding/RetrofitUrlManager
     */
    private fun initClient(): OkHttpClient {
        return RetrofitUrlManager.getInstance().with(
            OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addInterceptor(interceptor)
                .addInterceptor(ChuckInterceptor(MyApplication.instance))
                .retryOnConnectionFailure(true) //设置重连
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        )
            .build()

    }

    /**
     * 日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     *  具体服务实例化
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}