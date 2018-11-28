package com.xixi.comm.home;

import com.xixi.comm.basic.Works;

public class ArticleWorks extends Works {
    private String articleType;
    private String image1URL;
    private String image2URL;
    private String image3URL;
    private String videoURL;

    public String getImage1URL() {
        return image1URL;
    }

    public void setImage1URL(String image1URL) {
        this.image1URL = image1URL;
    }

    public String getImage2URL() {
        return image2URL;
    }

    public void setImage2URL(String image2URL) {
        this.image2URL = image2URL;
    }

    public String getImage3URL() {
        return image3URL;
    }

    public void setImage3URL(String image3URL) {
        this.image3URL = image3URL;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
