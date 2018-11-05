package com.xixi.comm.basic;

import java.util.ArrayList;
import java.util.List;

public enum TemplateType {
    FullWords(0, "文章"),
    WordsLeftImageRight(1, "左文右图"),
    WordsTop1ImageBelow(2, "上文下1图"),
    WordsTop3ImageBelow(3, "上文下3图"),
    Unknown(255, "未知");

    private int mIndex;
    private String mType;
    private static List<TemplateType> allTypes;
    static {
        allTypes = new ArrayList<TemplateType>();
        allTypes.add(FullWords);
        allTypes.add(WordsLeftImageRight);
        allTypes.add(WordsTop1ImageBelow);
        allTypes.add(WordsTop3ImageBelow);
    }
    public static int size() {
        return allTypes.size();
    }
    private TemplateType(int index, String type) {
        mIndex = index;
        mType = type;
    }
    public int getIndex() {
        return mIndex;
    }

    public String getType() {
        return mType;
    }
    public static TemplateType toTemplateType(String strType) {
        for (TemplateType one : allTypes) {
            if (one.mType.equalsIgnoreCase(strType)) {
                return one;
            }
        }
        return Unknown;
    }
    public static TemplateType toTemplateType(int index) {
        for (TemplateType one : allTypes) {
            if (one.mIndex == index) {
                return one;
            }
        }
        return Unknown;
    }
    public String toString() {
        return mType;
    }
}
