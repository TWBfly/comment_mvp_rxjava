package com.cogito.lexiang.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment



/**
 * Created by Tang on 2019/8/22
 */
abstract class BaseFragment<V, T : BasePresenter<V>> : Fragment() {
    private lateinit var rootView: View
    private lateinit var mPresenter: T
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutId(), container, false)
        mPresenter = initPresenter()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.detachView()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onResume() {
        super.onResume()
        mPresenter.attachView(this as V)
    }
    abstract fun initView()

    abstract fun initData()

    abstract fun initPresenter(): T

    abstract fun getLayoutId(): Int

}