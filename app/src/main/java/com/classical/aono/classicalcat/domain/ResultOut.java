package com.classical.aono.classicalcat.domain;

import java.io.Serializable;

/**
 * Created by gotha on 2017/11/6.
 */

public class ResultOut implements Serializable {
    private String success;
    private String result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultOut{" +
                "success='" + success + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
