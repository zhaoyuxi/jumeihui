package com.xixi.comm.home;

public class Category {
    static private String[] homeCategories = {"关注","推荐","知识","旅游","美食","美篇", "心灵鸡汤","养生","汽车"};
    static public String[] getCategory() {
        return homeCategories.clone();
    }
    static public String[] getSecondCategory(int index) {
        return null;
    }
    static public String[] getImageThirdCategory(int index) {
        return null;
    }
    static public String[] getVideoThirdCategory(int index) {
        return null;
    }
}
