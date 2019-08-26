package com.cogito.lexiang.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.util.*


/**
 * Created by Tang on 2018/11/2
 */
class AppManager private constructor() {
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    /*
        Activity入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /*
        Activity出栈
     */
    fun finishActivity(activity: Activity) {
        activityStack.remove(activity)
        activity.finish()
    }

    /*
        获取当前栈顶
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /*
        清理栈
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    //需要从A界面跳转到B界面，再从B界面跳转到C界面，再从C界面跳转到D界面，最后需要从D界面跳回到A界面，并且把B、C 、D界面都干掉
    fun finishTopActivity(activity: Activity,clazz: Class<*>){
        val intent = Intent(activity, clazz)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
        activity.finish()
    }
    fun finishTopActivity(activity: Activity,clazz: Class<*>,bundle:Bundle){
        val intent = Intent(activity, clazz)
        intent.putExtras(bundle)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
        activity.finish()
    }
    /*
        退出应用程序
     */
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}