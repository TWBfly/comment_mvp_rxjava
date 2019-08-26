package com.cogito.lexiang

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.cogito.lexiang.utils.log.LogUtils
import com.tencent.bugly.beta.Beta
import kotlin.properties.Delegates

/**
 * Created by Tang on 2019/8/23
 */
class MyApplication: Application() {
    companion object {
        var instance: MyApplication by Delegates.notNull()
    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        // 安装tinker
        Beta.installTinker()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //log全局开关
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG)
        LogUtils.getConfig().setGlobalTag("twb")

    }
}