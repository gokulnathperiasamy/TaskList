package com.kpgn.tasklist.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


public class TaskListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
