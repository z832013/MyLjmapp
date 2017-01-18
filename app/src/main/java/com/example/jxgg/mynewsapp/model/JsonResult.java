package com.example.jxgg.mynewsapp.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sedigital.selogutil.SELogUtil;

import java.io.IOException;

/**
 * Created by JXGG on 2017/1/17.
 */

public class JsonResult {
    private String reason;
    private String result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static JsonResult parse(String json) throws IOException {
        JsonResult jsonResult=new JsonResult();
        try {
            JSONObject jsonObject= JSON.parseObject(json);
            jsonResult.setResult(jsonObject.getString("result"));
            jsonResult.setReason(jsonObject.getString("reason"));
        }catch (JSONException e){
            SELogUtil.logE("错误："+e);
        }

        return jsonResult;
    }
}
