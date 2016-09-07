package com.study.jasmin.jasmin.entity;

import java.util.ArrayList;

public class AssignmentTitle {
    private int study_no;
    private int homework_no;
    private int submit_yes;
    private int submit_no;
    private String title;
    private String date;
    private ArrayList<Assignment> assignmentList;

    public AssignmentTitle(int study_no, int homework_no, String title, String date, Assignment assignmentList) {
        this.study_no = study_no;
        this.homework_no = homework_no;
        this.submit_yes = 0;
        this.submit_no = 0;
        this.title = title;
        this.date = date;
        addAssignmentList(assignmentList);
    }

    public int getHomework_no() {
        return homework_no;
    }

    public void setHomework_no(int homework_no) {
        this.homework_no = homework_no;
    }

    public int getStudy_no() {
        return study_no;
    }

    public void setStudy_no(int study_no) {
        this.study_no = study_no;
    }

    public int getSubmit_yes() {
        return submit_yes;
    }

    public void setSubmit_yes(int submit_yes) {
        this.submit_yes = submit_yes;
    }

    public int getSubmit_no() {
        return submit_no;
    }

    public void setSubmit_no(int submit_no) {
        this.submit_no = submit_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public void addAssignmentList(Assignment assignment){
        if(assignmentList == null){
            assignmentList = new ArrayList<Assignment>();
        }

        assignmentList.add(assignment);

        if(assignment.getHomework_state().equals("제출")){
            submit_yes++;
        }else if(assignment.getHomework_state().equals("미제출")){
            submit_no++;
        }
    }

    @Override
    public String toString() {
        return "AssignmentTitle{" +
                "study_no=" + study_no +
                ", submit_yes=" + submit_yes +
                ", submit_no=" + submit_no +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", assignmentList=" + assignmentList +
                '}';
    }
}
