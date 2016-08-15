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

    private ExpandableListView elvHelp;
    private  ArrayList<String> arrayGroup = new ArrayList<String>();
    private JasminPreference mPref;
    private ArrayList<QnA> qnaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_help);
        mPref = new JasminPreference(this);

        ArrayList<QnA> dfdf = (ArrayList<QnA>)mPref.getJSONValue("qnaList","null");
        Log.d("ss","qnaList : " + dfdf.get(0).getQna_answer());
        elvHelp = (ExpandableListView) this.findViewById(R.id.elv_help);
        elvHelp.setAdapter(new BaseExpandableAdapter(this, getArrayGroup(), getArrayChild()));
        // 그룹이 열릴 경우 이벤트
        elvHelp.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                            // 한 그룹을 클릭하면 나머지 그룹들은 닫힌다.
                for (int i = 0; i < arrayGroup.size(); i++) {
                    if (!(i == groupPosition))
                        elvHelp.collapseGroup(i);
                }
            }
        });

    }

    private ArrayList<String> getArrayGroup(){
        arrayGroup.add("Q1.질문");
        arrayGroup.add("Q2.질문");
        arrayGroup.add("Q3.질문");
        arrayGroup.add("Q4.질문");
        arrayGroup.add("Q5.질문");

        return arrayGroup;
    }

    private HashMap<String, ArrayList<String>> getArrayChild(){

        HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

        for(int i=0; i<arrayGroup.size(); i++)
        {
           ArrayList<String> arrList = new ArrayList<String>();
           arrList.add("A.답변입니다.");
           arrayChild.put(arrayGroup.get(i), arrList);
        }
        return arrayChild;
    }
}
