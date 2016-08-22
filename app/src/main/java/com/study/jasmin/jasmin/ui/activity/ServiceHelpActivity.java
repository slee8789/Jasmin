package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.QnA;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceHelpActivity extends AppCompatActivity {
    public static final String TAG = "Serviece";
    private ExpandableListView elvHelp;
    private JasminPreference mPref;
    private ArrayList<Object> qnaList;
    ArrayList<String> arrayGroup = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_help);
        mPref = JasminPreference.getInstance(this);
        qnaList= mPref.getListValue("qnaList");
        if(qnaList == null) {
            Log.d(TAG,"qnaList is empty!!!");
            return;
        }

        elvHelp = (ExpandableListView) this.findViewById(R.id.elv_help);
        elvHelp.setAdapter(new BaseExpandableAdapter(this, getArrayGroup(), getArrayChild()));
        elvHelp.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i <arrayGroup.size(); i++) {
                    if (!(i == groupPosition))
                        elvHelp.collapseGroup(i);
                }
            }
        });
    }

    private ArrayList<String> getArrayGroup(){

        for(int i=0; i<qnaList.size(); i++) {
            String question = ((QnA)qnaList.get(i)).getQna_question();
            arrayGroup.add((question == null || question == "") ? "" : question);
        }
        return arrayGroup;
    }

    private HashMap<String, ArrayList<String>> getArrayChild(){

        HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

        for(int i=0; i<arrayGroup.size(); i++)
        {
           ArrayList<String> arrList = new ArrayList<String>();
           arrList.add(((QnA)qnaList.get(i)).getQna_answer());
           arrayChild.put(arrayGroup.get(i), arrList);
        }
        return arrayChild;
    }
}
