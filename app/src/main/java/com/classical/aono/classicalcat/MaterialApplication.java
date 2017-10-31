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
//        AppClient.initAppClient();
//        DisplayUtil.init(this);
    }

    public String textSize = "";
    public String getextSize() {
        return textSize;
    }
    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }
    public String textLine = "";
    public String getextLine() {
        return textLine;
    }
    public void setTextLine(String textLine) {
        this.textLine = textLine;
    }

}
