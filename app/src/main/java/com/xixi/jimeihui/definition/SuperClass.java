package com.xixi.jimeihui.definition;

public enum SuperClass {
    Home("主页",0),
    Imagine("图册", 1),
    Video("视频",2),
    Mine("我的",3);

    private String name;
    private int position;
    private SuperClass(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }
    public int getPosition() {
        return position;
    }
}

