package com.cogito.lexiang.utils

import android.content.Context
import com.cogito.lexiang.BuildConfig

/**
 * Created by Tang on 2019/8/23
 */
fun getBaseUrl():String{
    return if (BuildConfig.DEBUG){
        "https://www.easy-mock.com/mock/5cbd31e2e89dcf26e1ce4cff/mock/"
    }else{
        "http://39.100.225.4:8080/"
    }
}

/**
 * 获取版本名称
 */
fun getLocalVersionName(ctx: Context): String {
    var localVersion = ""
    try {
        val packageInfo = ctx.applicationContext
            .packageManager
            .getPackageInfo(ctx.packageName, 0)
        localVersion = packageInfo.versionName
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return localVersion
}