package com.study.jasmin.jasmin.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {
    int post_no;
    String post_title;
    String post_date;
    String post_content;
    String post_file;
    int post_type;
    int hits;

    protected Comment(Parcel in) {
        post_no = in.readInt();
        post_title = in.readString();
        post_date = in.readString();
        post_content = in.readString();
        post_file = in.readString();
        post_type = in.readInt();
        hits = in.readInt();
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
        dest.writeInt(post_no);
        dest.writeString(post_title);
        dest.writeString(post_date);
        dest.writeString(post_content);
        dest.writeString(post_file);
        dest.writeInt(post_type);
        dest.writeInt(hits);
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_no=" + post_no +
                ", post_title='" + post_title + '\'' +
                ", post_date='" + post_date + '\'' +
                ", post_content='" + post_content + '\'' +
                ", post_file='" + post_file + '\'' +
                ", post_type=" + post_type +
                ", hits=" + hits +
                '}';
    }
}
