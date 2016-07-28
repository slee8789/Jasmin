package com.study.jasmin.jasmin.ui.list;

import android.widget.ImageView;

public class ListInfoReply {
    private String id;
    private String content;
    private ImageView write;
    private ImageView delete;

    public ListInfoReply() {
    }

    public ListInfoReply(String id, String content, ImageView write, ImageView delete) {
        this.id = id;
        this.content = content;
        this.write = write;
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImageView getWrite() {
        return write;
    }

    public void setWrite(ImageView write) {
        this.write = write;
    }

    public ImageView getDelete() {
        return delete;
    }

    public void setDelete(ImageView delete) {
        this.delete = delete;
    }
}




