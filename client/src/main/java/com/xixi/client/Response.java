package com.xixi.client;

import com.xixi.comm.error.Result;

public class Response {
    private String body;
    private Result result;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
