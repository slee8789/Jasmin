package com.study.jasmin.jasmin.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    int     post_no;             // 0. No.
    String  post_title;          // 1. 제목
    int     hits;                // 2. 조회수
    String  post_content;        // 3. 내용
    String  post_date;           // 4. 작성일
    String  user_name;         // 5. 작성자
    int     comment_count;       // 6. 댓글수
    String  post_file;           // 7. 파일
    int     post_type;           // 8. 게시글 타입
    boolean favorite;            // 9. 즐겨찾기

    public Post() {
    }

    public Post(int post_no,
                String post_title,
                int hits,
                String post_content,
                String post_date,
                String user_name,
                int comment_count,
                String post_file,
                int post_type,
                boolean favorite
    ) {
        this.post_no = post_no;
        this.post_title = post_title;
        this.hits = hits;
        this.post_content = post_content;
        this.post_date = post_date;
        this.user_name = user_name;
        this.comment_count = comment_count;
        this.post_file = post_file;
        this.post_type = post_type;
        this.favorite = favorite;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String post_writer) {
        this.user_name = user_name;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getPost_no() {
        return post_no;
    }

    public void setPost_no(int post_no) {
        this.post_no = post_no;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_file() {
        return post_file;
    }

    public void setPost_file(String post_file) {
        this.post_file = post_file;
    }

    public int getPost_type() {
        return post_type;
    }

    public void setPost_type(int post_type) {
        this.post_type = post_type;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }



    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
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
        dest.writeInt(hits);
        dest.writeString(post_content);
        dest.writeString(post_date);
        dest.writeString(user_name);
        dest.writeInt(comment_count);
        dest.writeString(post_file);
        dest.writeInt(post_type);

    }

    protected Post(Parcel in) {
        post_no = in.readInt();
        post_title = in.readString();
        hits = in.readInt();
        post_content = in.readString();
        post_date = in.readString();
        user_name = in.readString();
        comment_count = in.readInt();
        post_file = in.readString();
        post_type = in.readInt();
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_no=" + post_no +
                ", post_title='" + post_title + '\'' +
                ", hits=" + hits +
                ", post_content='" + post_content + '\'' +
                ", post_date='" + post_date + '\'' +
                ", post_writer='" + user_name + '\'' +
                ", comment_count=" + comment_count +
                ", post_file='" + post_file + '\'' +
                ", post_type=" + post_type +
                ", favorite=" + favorite +
                '}';
    }
}
