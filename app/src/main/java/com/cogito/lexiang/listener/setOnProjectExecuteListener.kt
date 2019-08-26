package com.cogito.lexiang.listener

import com.alibaba.android.alpha.OnProjectExecuteListener

/**
 * Created by Tang on 2019/8/23
 */
abstract class setOnProjectExecuteListener:OnProjectExecuteListener {
    override fun onProjectStart() {
    }

    override fun onProjectFinish() {
    }

    override fun onTaskFinish(taskName: String?) {
    }
}