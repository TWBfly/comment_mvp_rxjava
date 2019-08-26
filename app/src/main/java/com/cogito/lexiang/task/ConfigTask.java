package com.cogito.lexiang.task;

import android.content.Context;
import com.alibaba.android.alpha.*;

/**
 * Created by Tang on 2019/1/24
 * 加速启动
 */
public class ConfigTask {
    private Context mContext;
    private OnProjectExecuteListener mOnProjectExecuteListener;

    public ConfigTask(Context context) {
        mContext = context;
    }

    public void start() {
        config();
        AlphaManager.getInstance(mContext).start();
    }

    public void setOnProjectExecuteListener(OnProjectExecuteListener listener) {
        mOnProjectExecuteListener = listener;
    }

    private void config() {
        Project.Builder builder = new Project.Builder().withTaskCreator(new MyTaskCreator());
        builder.add(TASK_TOAST);
        builder.add(TASK_BUGLY);
        builder.setProjectName("innerGroup");
        if (mOnProjectExecuteListener != null) {
            builder.setOnProjectExecuteListener(mOnProjectExecuteListener);
        }
        AlphaManager.getInstance(mContext).addProject(builder.create());
    }

    private static final String TASK_TOAST = "toastTask";
    private static final String TASK_BUGLY = "buglyTask";

    public static class MyTaskCreator implements ITaskCreator {
        @Override
        public Task createTask(String taskName) {
            switch (taskName) {
                case TASK_TOAST:
                    return new ToastTask();
                case TASK_BUGLY:
                    return new BuglyTask();
            }
            return null;
        }
    }
}
