package com.example.btl_app_doc_truyen.model;

public class Story {
    private int storyID;
    private String storyName;
    private String storyContent;
    private String storyImage;
    private int storyAuthor;

    public Story(int storyAuthor, String storyContent, String storyImage, String storyName) {
        this.storyAuthor = storyAuthor;
        this.storyContent = storyContent;
        this.storyImage = storyImage;
        this.storyName = storyName;
    }

    public Story(int storyID, String storyName, String storyContent, String storyImage, int storyAuthor) {
        this.storyID = storyID;
        this.storyName = storyName;
        this.storyContent = storyContent;
        this.storyImage = storyImage;
        this.storyAuthor = storyAuthor;
    }

    public int getStoryAuthor() {
        return storyAuthor;
    }

    public void setStoryAuthor(int storyAuthor) {
        this.storyAuthor = storyAuthor;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public String getStoryImage() {
        return storyImage;
    }

    public void setStoryImage(String storyImage) {
        this.storyImage = storyImage;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }
}
