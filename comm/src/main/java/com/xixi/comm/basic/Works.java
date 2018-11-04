package com.xixi.comm.basic;

public class Works {
    private Long publishTimeStamp;
    private String strPublishTimeStamp;
    private String authorName;
    private String authorId;
    private String authorType;
    private String commentsCounter;
    private String title;
    private String templateType;

    public String getWorksType() {
        return worksType;
    }

    public void setWorksType(String worksType) {
        this.worksType = worksType;
    }

    private String worksType;

    public Long getPublishTimeStamp() {
        return publishTimeStamp;
    }

    public void setPublishTimeStamp(Long publishTimeStamp) {
        this.publishTimeStamp = publishTimeStamp;
    }

    public String getStrPublishTimeStamp() {
        return strPublishTimeStamp;
    }

    public void setStrPublishTimeStamp(String strPublishTimeStamp) {
        this.strPublishTimeStamp = strPublishTimeStamp;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
    }

    public String getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(String commentsCounter) {
        this.commentsCounter = commentsCounter;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTemplateType() {
        return templateType;
    }
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
}
