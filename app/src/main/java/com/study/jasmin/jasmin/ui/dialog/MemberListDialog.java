package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Member;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberListDialog extends Dialog implements View.OnClickListener, Callback{
    public static final String TAG = "GSetAlarmAddActivity";
    static private final int MEMBER_DIALOG_GRADE_SHARE = 1;
    static private final int MEMBER_DIALOG_GRADE_SHARE_DELETE = 4;
    static private final int MEMBER_DIALOG_GRADE_DELEGATE = 2;
    static private final int MEMBER_DIALOG_MEMBER_REMOVE = 3;

    private TextView    title, infoNull;
    private Button      ok, cancel;
    private RadioGroup  radioGroup;
    private int         memberDialogType = 0;
    private int         selUserNo = 0;
    private JasminPreference    mPref;

    public MemberListDialog(Context context, String strTitle,int nMemberDialogType) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_member_list);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);		// 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true);                  // 백키로 다이알로그 닫기
        this.memberDialogType = nMemberDialogType;
        mPref = JasminPreference.getInstance(context);

        findView();
        initView(strTitle);
        initRadioGroup(context);
    }

    private void findView(){
        title = (TextView) findViewById(R.id.tv_title);
        infoNull= (TextView) findViewById(R.id.tv_null);
        ok = (Button)findViewById(R.id.btn_ok);
        cancel = (Button)findViewById(R.id.btn_cancel);
        radioGroup = (RadioGroup)findViewById(R.id.rg_member);
    }

    private void  initView(String strTitle){
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        infoNull.setVisibility(View.INVISIBLE);
        title.setText(strTitle);
    }

    public void initRadioGroup(Context context){
        int                 myNo        = ((User)mPref.getObjectValue("userInfo")).getUser_no();
        ArrayList<Object>   members      = mPref.getListValue("memberList");
        Member              member       = null;

        if(members.size() < 2) {
            setInfoNull();
            return;
        }

        for(int i=0; i<members.size(); i++) {
            member = (Member)members.get(i);
            if (myNo != member.getUser_no()){
                switch (memberDialogType){
                    case MEMBER_DIALOG_GRADE_SHARE:
                        if(member.getUser_grade()==3)     addRadioButton(member,context);
                        break;
                    case MEMBER_DIALOG_GRADE_DELEGATE:
                        addRadioButton(member,context);
                        break;
                    case MEMBER_DIALOG_MEMBER_REMOVE:
                        addRadioButton(member,context);
                        break;
                    case MEMBER_DIALOG_GRADE_SHARE_DELETE:
                        if(member.getUser_grade()==2)       addRadioButton(member,context);
                        break;
                }
            }
        }

        if(radioGroup.getChildCount() < 1)  setInfoNull();

    }

    public void addRadioButton(Member member, Context context){
        RadioButton radio_btn = new RadioButton(context);
        radio_btn.setId(member.getUser_no());
        radio_btn.setText(member.getUser_name());
        radioGroup.addView(radio_btn);
    }

    public void setInfoNull(){
        infoNull.setVisibility(View.VISIBLE);
        infoNull.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
    }

    public void closeMemberDialog(){
        this.dismiss();
        this.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                this.closeMemberDialog();
                break;
            case R.id.btn_ok:
                int studyNo = JasminPreference.getInstance(getContext()).getSelStudyNo();
                selUserNo = (this.findViewById(radioGroup.getCheckedRadioButtonId())).getId();
                goGrant(studyNo,selUserNo);
                break;
        }
    }

    public void goGrant(int studyNo, int userNo){
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call=null;

        Toast.makeText(getContext(), "그룹번호 : "+Integer.toString(studyNo)+ " / 유저번호 : "+Integer.toString(userNo), Toast.LENGTH_SHORT).show();

        switch (memberDialogType){
            case MEMBER_DIALOG_GRADE_SHARE:
                call= service.gradeShare(studyNo, userNo);
                call.enqueue(this);
                break;
            case MEMBER_DIALOG_GRADE_DELEGATE:
                call = service.gradeDelegate(studyNo, userNo);
                call.enqueue(this);
                break;
            case MEMBER_DIALOG_MEMBER_REMOVE:
                call = service.removeMember(studyNo, userNo);
                call.enqueue(this);
                break;
            case MEMBER_DIALOG_GRADE_SHARE_DELETE:
                call = service.gradeDelete(studyNo,userNo);
                call.enqueue(this);
                break;
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, "Retro Status Code = " + response.code());
        try {
            String strToast     = "";
            String strTest      = response.body().toString();
            JSONObject jsObject = new JSONObject(strTest);
            if(jsObject.getString("result").equals("1")){
                strToast     = "성공";
                setMemberPref();
                //e160831 성공 시 업데이트 된 멤버 리스트 받아오기
            }else{
                strToast     = "실패";
            }
            Toast.makeText(getContext(),strToast, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
        closeMemberDialog();
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    //change MemberList in Preference
    public void setMemberPref(){
        ArrayList<Object>   members      = mPref.getListValue("memberList");

        for(int i=0; i<members.size(); i++){
            Member member = (Member)members.get(i);
            if(member.getUser_no() == selUserNo){
                switch (memberDialogType){
                    case MEMBER_DIALOG_GRADE_SHARE:
                        member.setUser_grade(2);
                        break;
                    case MEMBER_DIALOG_GRADE_DELEGATE:
                        member.setUser_grade(1);
                        break;
                    case MEMBER_DIALOG_MEMBER_REMOVE:
                        members.remove(member);
                        break;
                    case MEMBER_DIALOG_GRADE_SHARE_DELETE:
                        member.setUser_grade(3);
                        break;
                }
                Member arrMembers[] = new Member[members.size()];
                mPref.setMembers(members.toArray(arrMembers));
                return;
            }//end if
        }//end for
    }

}


