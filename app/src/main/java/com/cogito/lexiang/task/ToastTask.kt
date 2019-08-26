package com.cogito.lexiang.task

import com.alibaba.android.alpha.Task
import com.cogito.lexiang.MyApplication
import com.cogito.lexiang.utils.log.LogUtils
import com.hjq.toast.ToastUtils

/**
 * Created by Tang on 2019/8/23
 */
class ToastTask: Task("toastTask",true) {
    override fun run() {
        LogUtils.e("ToastTask初始化==")
        ToastUtils.init(MyApplication.instance)
    }

}