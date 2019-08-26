package com.cogito.lexiang.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.cogito.lexiang.MyApplication
import com.cogito.lexiang.R
import com.cogito.lexiang.listener.setOnProjectExecuteListener
import com.cogito.lexiang.task.ConfigTask
import com.cogito.lexiang.utils.AppManager
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Created by Tang on 2019/8/22
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity(),BaseView {
    private lateinit var mPresenter: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val task = ConfigTask(MyApplication.instance)
        initImmersionBar()

        content.addView(LayoutInflater.from(this).inflate(contentLayoutId(), null))
        mPresenter = initPresenter()
        mPresenter.takeView(this as V)
        initView()
        initData()
        AppManager.instance.addActivity(this)

        task.setOnProjectExecuteListener(object : setOnProjectExecuteListener() {
            override fun onTaskFinish(taskName: String?) {
            }

            override fun onProjectStart() {
            }

            override fun onProjectFinish() {
//                initView()
            }
        })
        task.start()

//        RetrofitUrlManager.getInstance().putDomain("mock", "https://api.douban.com")
    }

    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this as V)
    }

    abstract fun contentLayoutId(): Int

    abstract fun initView()

    abstract fun initData()

    abstract fun initPresenter(): T

    abstract fun initDestroy()

    override fun showLoading() {

    }
    override fun hideLoading() {


    }
    override fun showToast() {
        ToastUtils.show("==成功==")
    }



    private fun initImmersionBar() {
        ImmersionBar.with(this).titleBar(title_bar).init()
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            ImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).init()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        initDestroy()
        mPresenter.detachView()
        AppManager.instance.finishAllActivity()
    }


}