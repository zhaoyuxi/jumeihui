package com.xixi.jimeihui.definition;

public class Category {
    static private String[] homeCategories = {"关注","推荐","知识","游玩","美食","心灵","鸡汤","养生","汽车"};
    static private String[] imageCategories = {"推荐","摄影","旅游","美食","美女","家庭","孩子","动物","家居装潢","建筑","花卉园艺","艺术","绘画"};
    static private String[][] videoCategories = {{""}};
    static public String[] getTopCategory() {
        return null;
    }
    static public String[] getHomeCategory() {
        return homeCategories.clone();
    }
    static public String[] getImageCategory() {
        return imageCategories.clone();
    }
    static public String[] getVideoCategory() {
        return null;
    }
    static public String[] getHomeThirdCategory(int index) {
        return null;
    }
    static public String[] getImageThirdCategory(int index) {
        return null;
    }
    static public String[] getVideoThirdCategory(int index) {
        return null;
    }
}
