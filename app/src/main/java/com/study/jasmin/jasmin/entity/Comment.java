package com.study.jasmin.jasmin.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {
//    int post_no;
    int comment_no;
    int user_no;
    String user_name;
    String comment_content;
    String comment_date;

    //서버에서 받아오지 않고 리스트에 추가할 때
    public Comment(int post_no,int user_no, String user_name, String comment_content, String comment_date) {
//        this.post_no = post_no;
        this.user_no = user_no;
        this.user_name = user_name;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

//    public int getPost_no() {
//        return post_no;
//    }

//    public void setPost_no(int post_no) {
//        this.post_no = post_no;
//    }

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    protected Comment(Parcel in) {
//        post_no = in.readInt();
        comment_no = in.readInt();
        comment_date = in.readString();
        comment_content = in.readString();
        user_no = in.readInt();
        user_name = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(post_no);
        dest.writeInt(comment_no);
        dest.writeString(comment_date);
        dest.writeString(comment_content);
        dest.writeString(user_name);
        dest.writeInt(user_no);
    }

    @Override
    public String toString() {
        return "Comment{" +
//                "post_no=" + post_no +
                ", comment_no=" + comment_no +
                ", user_no=" + user_no +
                ", user_name='" + user_name + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_date='" + comment_date + '\'' +
                '}';
    }
}
