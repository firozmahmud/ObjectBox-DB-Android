package com.example.objectbox_db_android.objectbox;

import android.content.Context;


import com.example.objectbox_db_android.model.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBox {

    private static BoxStore boxStore = null;

    public static void init(Context context) {

        boxStore = MyObjectBox.builder().androidContext(context).build();

    }

    public static BoxStore get() {
        return boxStore;
    }
}
