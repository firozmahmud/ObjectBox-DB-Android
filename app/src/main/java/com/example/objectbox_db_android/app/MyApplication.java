package com.example.objectbox_db_android.app;

import android.app.Application;
import com.example.objectbox_db_android.objectbox.ObjectBox;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init ObjectBox
        ObjectBox.init(this);
    }
}
