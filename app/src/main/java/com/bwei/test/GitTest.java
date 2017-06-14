package com.bwei.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by caojun on 2017/6/14.
 */

public class GitTest extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("myMessage","caojun");
        Log.e("myMessage","caojun");
        Log.e("myMessage","caojun");
        Log.e("myMessage","caojun");
        Log.e("myMessage","caojun");
        Log.e("myMessage","caojun");
        Log.e("myMessage","caojun3");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("myMessage","onStart");
    }
}
