package com.study.jasmin.jasmin.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leesc on 2016-08-15.
 */
public class QnA implements Parcelable {

//Todo:    @SerializedName("qna_no")  변수명 변경하고 싶을 때 사용해보기 test 필요
    private int qna_no;
    private String qna_queastion;
    private String qna_answer;

    public QnA() {
    }

    public QnA(Parcel in) {
        qna_no = in.readInt();
        qna_queastion = in.readString();
        qna_answer = in.readString();
    }

    public QnA(int qna_no, String qna_queastion, String qna_answer) {
        this.qna_no = qna_no;
        this.qna_queastion = qna_queastion;
        this.qna_answer = qna_answer;
    }

    public int getQna_no() {
        return qna_no;
    }

    public void setQna_no(int qna_no) {
        this.qna_no = qna_no;
    }

    public String getQna_queastion() {
        return qna_queastion;
    }

    public void setQna_queastion(String qna_queastion) {
        this.qna_queastion = qna_queastion;
    }

    public String getQna_answer() {
        return qna_answer;
    }

    public void setQna_answer(String qna_answer) {
        this.qna_answer = qna_answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(qna_no);
        dest.writeString(qna_queastion);
        dest.writeString(qna_answer);
    }

    private void readFromParcel(Parcel in) {
        qna_no = in.readInt();
        qna_queastion = in.readString();
        qna_answer = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public QnA createFromParcel(Parcel in) {
            return new QnA(in);
        }

        @Override
        public QnA[] newArray(int size) {
            return new QnA[size];
        }

    };

}
