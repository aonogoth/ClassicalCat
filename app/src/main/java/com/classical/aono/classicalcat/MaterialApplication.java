package com.classical.aono.classicalcat;

import android.app.Application;

import com.classical.aono.classicalcat.common.AppClient;
import com.classical.aono.classicalcat.common.DisplayUtil;

/**
 * Created by gotha on 2017/9/27.
 */

public class MaterialApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.initAppClient();
        DisplayUtil.init(this);
    }
}
