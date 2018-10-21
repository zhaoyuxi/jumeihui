package com.xixi.jimeihui.image.framework;

public enum RecommendElement {
    Follows(0),
    Column(1),
    RecommendFollows(2),
    PictureWaterFall(3);

    private int type;
    RecommendElement(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
