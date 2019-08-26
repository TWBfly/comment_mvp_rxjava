package com.cogito.lexiang.rx;

import com.hjq.toast.ToastUtils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Tang on 2019/1/18
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        _onError(t.getMessage());
        ToastUtils.show(t.getMessage());
    }

    @Override
    public void onComplete() {

    }

    protected abstract void _onNext(T t);

    protected void _onError(String message) {
    }
}
