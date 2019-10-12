package com.example.submission1.rest;

import com.example.submission1.statics.EndPoint;

public class ApiUtil {

    public static Request getRequest() {
        return RetrofitUtil.getRetrofit(EndPoint.BASE_URL).create(Request.class);
    }
}
