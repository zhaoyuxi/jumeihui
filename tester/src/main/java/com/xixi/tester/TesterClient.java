package com.xixi.tester;

import com.alibaba.fastjson.JSON;
import com.xixi.client.Client;
import com.xixi.client.Response;
import com.xixi.client.ResultNotification;
import com.xixi.comm.error.Result;
import com.xixi.comm.home.ArticleWorks;

import java.util.List;

public class TesterClient implements Client {
    @Override
    public Result Request(String topCategory, String secondCategory, ResultNotification notify) {
        List<ArticleWorks> works = ArticleWorksGen.Generate(20);
        String body = JSON.toJSONString(works);
        Response resp = new Response();
        resp.setBody(body);
        resp.setResult(Result.Succeed);
        notify.Notify(resp);
        return Result.Succeed;
    }
}
