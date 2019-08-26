package com.cogito.lexiang.task

import com.alibaba.android.alpha.Task
import com.cogito.lexiang.BuildConfig
import com.cogito.lexiang.MyApplication
import com.cogito.lexiang.utils.getLocalVersionName
import com.cogito.lexiang.utils.log.LogUtils
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport

/**
 * Created by Tang on 2019/8/23
 */
val appId = "f60b28dee0"
class BuglyTask:Task("buglyTask") {
    override fun run() {
        val strategy = CrashReport.UserStrategy(MyApplication.instance)
        strategy.appChannel = "tencent"
        strategy.appVersion = getLocalVersionName(MyApplication.instance)
        strategy.appPackageName = "com.cogito.lexiang"
        CrashReport.setIsDevelopmentDevice(MyApplication.instance, BuildConfig.DEBUG)
        CrashReport.initCrashReport(Bugly.applicationContext, appId, BuildConfig.DEBUG, strategy)
        Bugly.init(MyApplication.instance, appId, BuildConfig.DEBUG, strategy)
        LogUtils.e("bugly初始化==")
    }

}