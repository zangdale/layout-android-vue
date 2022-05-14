package com.orange.ldv.controller;

import com.orange.ldv.annotation.AppAutowired;
import com.orange.ldv.annotation.AppController;
import com.orange.ldv.annotation.AppRequestMapper;
import com.orange.ldv.annotation.AppRequestMethod;
import com.orange.ldv.entity.Article;
import com.orange.ldv.services.TestServices;

import java.util.Date;
import java.util.List;

@AppController(path = "test")
public class TestController {

    @AppRequestMapper(path = "/get6", method = AppRequestMethod.GET)
    public String getTest6(String str) {
        return str;
    }

    @AppRequestMapper(path = "/get", method = AppRequestMethod.GET)
    public String getTest(String str, int str2) {
        return "这是修改后的字符串" + str + "," + str2;
    }

    @AppRequestMapper(path = "/get2", method = AppRequestMethod.GET)
    public String[] getTest2(String str, int str2) {
        return new String[]{"这是修改后的字符串", str, str2 + ""};
    }

    @AppRequestMapper(path = "/get3", method = AppRequestMethod.GET)
    public String[] getTest3(String[] str, int str2) {
        return new String[]{"这是修改后的字符串", "数组长度是:" + str.length, str2 + ""};
    }

    @AppRequestMapper(path = "/get4", method = AppRequestMethod.GET)
    public Article getTest4(Article article) {
        article.setUpdatedTime(new Date());
        return article;
    }


    @AppRequestMapper(path = "/get5", method = AppRequestMethod.GET)
    public List<Article> getTest5(List<Article> articles) {
        return articles;
    }

    @AppAutowired
    private TestServices testServices;

    @AppRequestMapper(path = "/get7", method = AppRequestMethod.GET)
    public String getTest7() {
        return testServices.getString();
    }




}
