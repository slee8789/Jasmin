package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.entity.Member;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupManageAttendanceActivity;
import com.study.jasmin.jasmin.ui.activity.MainActivity;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceCheckList;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceList;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendanceCheck;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import com.study.jasmin.jasmin.util.JasminUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by swan on 2016-08-03.
 */
public class AttendanceCheckDialog extends Dialog implements View.OnClickListener, Callback, AdaptInfoAttendanceCheckList.RadioClickListener{

    private final String TAG = "AttendanceCheckDialog";
    private Button btnOk;
    private ListView listView;
    private AdaptInfoAttendanceCheckList adapter;
    private ArrayList<Attendance> attendanceList;
    private TextView tvDate;
    private boolean bNew; //true : 새로추가 , false : 수정하기
    private ProgressDialog progress;
    private int selId;
    JasminUtil jUtil;


    public AttendanceCheckDialog(Context context, ArrayList<Attendance> attendanceList) {
        super(context);
        if(attendanceList==null){
            bNew= true;
            this.attendanceList = getBasicAttendanceList();
        }else{
            bNew=false;
            this.attendanceList = attendanceList;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_attendance_check);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);        // 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true);                   // 백키로 다이알로그 닫기
        progress = new ProgressDialog(getContext());

        findViews();
        initViews();
    }

    public void findViews() {
        listView = (ListView) findViewById(R.id.lv_attendance_check);
        btnOk = (Button) findViewById(R.id.btn_ok);
        tvDate = (TextView)findViewById(R.id.tv_date);
    }

    public void initViews() {
        jUtil    = new JasminUtil();
        adapter  = new AdaptInfoAttendanceCheckList(this.getContext(), R.layout.list_attendance_check_info, attendanceList,this);
        listView.setAdapter(adapter);
        btnOk.setOnClickListener(this);
        tvDate.setText("날짜 : "+ attendanceList.get(0).getAttendance_date());
    }

    public  ArrayList<Attendance>  getBasicAttendanceList(){
        ArrayList<Attendance> tempList = new ArrayList<Attendance>();
        JasminPreference pref = JasminPreference.getInstance(getContext());
        ArrayList<Object> memberList = pref.getListValue("memberList");
        for(int i=0; i<memberList.size(); i++) {
            Member mem = (Member)memberList.get(i);
            tempList.add(new Attendance(0, mem.getUser_no(), mem.getUser_name(),
                                         pref.getSelStudyNo(), jUtil.getTodayYYYY_MM_DD(), "출석","출석"));
        }
        return tempList;
    }

    @Override
    public void onClick(View v) {
        selId = v.getId();

        switch (selId){
            case R.id.btn_ok:
                String strJson = getJson();
                if(attendanceList == null || strJson.equals("")){
                    Log.d(TAG ,">>>>>>>>>>>>>>attendanceList is null or nothing changed!!! ");
                }else{
                    progress.show();
                    RestClient.RestService service = RestClient.getClient();
                    Call<JsonObject> call = bNew ? service.insertAttendance(getJson()) : service.updateAttendance(getJson());
                    call.enqueue(this);
                }
                this.dismiss();
                break;
        }
    }

    public String getJson(){
        String                strJson   =   "";

        if(bNew){
            for(int i=0; i<attendanceList.size(); i++){
                strJson = strJson + ((Attendance)listView.getItemAtPosition(i)).getJsonInsert() + ",";
            }
        }
        else{
            for(int i=0; i<attendanceList.size(); i++){
                if(attendanceList.get(i).checkUpdate()) {
                    attendanceList.get(i).setStudy_no(JasminPreference.getInstance(getContext()).getSelStudyNo());
                    strJson = strJson + ((Attendance) listView.getItemAtPosition(i)).getJsonUpdate() + ",";
                }
            }
        }
        strJson = (strJson.length()>0)? "[" + strJson.substring(0, strJson.length() - 1) + "]":"";
        return strJson;
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, "Retro Status Code = " + response.code());
        try {
            String strToast = "";
            String strTest = response.body().toString();
            JSONObject jsObject = new JSONObject(strTest);

            if(selId==0){
                Gson gson = new GsonBuilder().create();
                JSONArray attendObj = jsObject.getJSONArray("result");
                Attendance[] attendanceArr = gson.fromJson(attendObj.toString(), Attendance[].class);
                ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
                Collections.addAll(attendanceList, attendanceArr);
                Intent intent = new Intent(getContext(),GroupManageAttendanceActivity.class);
                intent.putParcelableArrayListExtra("attendanceList", attendanceList);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //Log.d(TAG,">>>>>>>attendanceList"+attendanceList.toString());startA
                this.dismiss();
                this.cancel();
                getContext().startActivity(intent);
            }else {
                if (jsObject.getString("result").equals("1") && selId == R.id.btn_ok) {
                    strToast = (bNew) ? "출석이 추가되었습니다." : "출석이 수정되었습니다.";
                    selId=0;
                    goList();
                }else{
                    strToast = "실패했습니다.";
                }
            }
            Toast.makeText(getContext(),strToast, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
    }

    public void goList(){
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.attendanceList(JasminPreference.getInstance(getContext()).getSelStudyNo());
        call.enqueue(this);
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ResponseFail = " + call);
    }


    //라디오 버튼 클릭
    @Override
    public void onRadioChange(RadioGroup group, int checkedId, int position){
/*
    if((listView.getItemAtPosition(position))==null){
        Log.d(TAG, ">>>>>>>>>>>>>>>>>> 다이얼로그에서 이벤트 들어옴 하지만position 이 널... : ");
    }else {
        Log.d(TAG, ">>>>>>>>>>>>>>>>>> 다이얼로그에서 이벤트 들어옴 onRadioChange : "
                + (listView.getItemAtPosition(position)).toString());
    }



        RadioButton selOption = (RadioButton)findViewById(checkedId);
        Spinner sp = (Spinner)findViewById((int)selOption.getTag());
        Log.d(TAG, ">>>>>>>>>>>>>>>>>> SpinnerID"+Integer.toString(position)+" : "+Integer.toString(sp.getId()));
        switch (checkedId) {
            case R.id.rb_option1:
                Log.d(TAG, ">>>>>>>>>>>>>>>>>> 1");
                ((Attendance)listView.getItemAtPosition(position)).setAttendanceStateNew("출석");
                sp.setVisibility(View.INVISIBLE);
                break;
            case  R.id.rb_option2:
                Log.d(TAG, ">>>>>>>>>>>>>>>>>> 2");
                ((Attendance)listView.getItemAtPosition(position)).setAttendanceStateNew("지각");
                sp.setVisibility(View.VISIBLE);
                break;
            case  R.id.rb_option3:
                Log.d(TAG, ">>>>>>>>>>>>>>>>>> 3");
                ((Attendance)listView.getItemAtPosition(position)).setAttendanceStateNew("결석");
                break;
        }

        Log.d(TAG, ">>>>>>>>>>>>>>>>>> 다이얼로그에서 이벤트 들어옴 onRadioChange : "
                + (listView.getItemAtPosition(position)).toString());
*/
    }

}

