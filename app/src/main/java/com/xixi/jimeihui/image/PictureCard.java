package com.xixi.jimeihui.image;

import java.io.Serializable;

public class PictureCard implements Serializable {
  private String avatarUrl; //明显头像的Url
  private String name;  //明显的名字
  private int imgHeight;  //头像图片的高度

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getImgHeight() {
    return imgHeight;
  }

  public void setImgHeight(int imgHeight) {
    this.imgHeight = imgHeight;
  }
}
