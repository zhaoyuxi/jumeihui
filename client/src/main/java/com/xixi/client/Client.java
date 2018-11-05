package com.xixi.client;

import com.xixi.comm.error.Result;
public interface Client {
    public Result Request(String topCategory, String secondCategory, ResultNotification notify);
}
