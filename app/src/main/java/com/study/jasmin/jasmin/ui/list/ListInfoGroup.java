package com.study.jasmin.jasmin.ui.list;

import android.widget.Button;
import android.widget.ImageView;

public class ListInfoGroup {
    private ImageView cover;
    private Button invite;

    public ListInfoGroup() {
    }

    public ImageView getCover() {
        return cover;
    }

    public void setCover(ImageView cover) {
        this.cover = cover;
    }

    public Button getInvite() {
        return invite;
    }

    public void setInvite(Button invite) {
        this.invite = invite;
    }

    public ListInfoGroup(ImageView cover, Button invite) {

        this.cover = cover;
        this.invite = invite;
    }
}




