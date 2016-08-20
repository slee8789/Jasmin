package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Study;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupMainActivity;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolderInfoGroupList extends RecyclerView.ViewHolder implements View.OnClickListener, Callback{

    public TextView groupName;
    public ImageView groupPhoto;
    private Context context;
    private ArrayList<Object> studyList;
    private ProgressDialog Progress;

    public HolderInfoGroupList(View itemView) {
        super(itemView);
        itemView.getClass();
        itemView.setOnClickListener(this);
        context = itemView.getContext();
        Progress = new ProgressDialog(context);
        groupName = (TextView)itemView.findViewById(R.id.group_name);
        groupPhoto = (ImageView)itemView.findViewById(R.id.group_photo);
        studyList = JasminPreference.getInstance(context).getListValue("studyList");
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked group Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        Progress.show();
        RestClient.RestService service = RestClient.getClient();
        Log.d("test","studyNo :  " + ((Study)studyList.get(getAdapterPosition())).getStudy_no());
        Call<JsonObject> call = service.gotoStudy(((Study)studyList.get(getAdapterPosition())).getStudy_no());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d("test", "Retro Status Code = " + response.code());
        try {
            String strTest = response.body().toString();

            JSONObject jsObject = new JSONObject(strTest);
            JSONArray studyObj = jsObject.getJSONArray("studyInfo");
            JSONArray memberObj = jsObject.getJSONArray("memberList");

            JasminPreference.getInstance(context).putJson("studyList",studyObj.toString());
            JasminPreference.getInstance(context).putJson("memberList",memberObj.toString());

//            Log.d("test",((Study)studyList.get(0)).getStudy_useDeposit()+"testtest");
//            Log.d("test",((Study)studyList.get(1)).getStudy_useDeposit()+"testtest");
            Intent intent = new Intent(context, GroupMainActivity.class);
            context.startActivity(intent);

            Progress.cancel();
            Progress.dismiss();

        } catch (JSONException e) {
            Log.d("test", "e : " + e);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d("test", "ResponseFail = " + call);
    }
}