package com.study.jasmin.jasmin.ui.list;

public class ListInfoNotice {
    private String no;          // 0. No.
    private String title;       // 1. 제목
    private String views;       // 2. 조회수
    private String content;     // 3. 내용
    private String date;        // 4. 작성일
    private String writer;      // 5. 작성자
    private String reply;       // 6. 댓글
    private boolean favorite;   // 7. 즐겨찾기


    public ListInfoNotice() {
    }

    public ListInfoNotice(String no, String title, String views, String content, String date, String writer, String reply, boolean favorite) {
        this.no = no;
        this.title = title;
        this.views = views;
        this.content = content;
        this.date = date;
        this.writer = writer;
        this.reply = reply;
        this.favorite = favorite;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}




