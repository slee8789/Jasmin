package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;
import com.study.jasmin.jasmin.entity.AssignmentTitle;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.AssignmentAddDialog;
import com.study.jasmin.jasmin.ui.dialog.AssignmentCheckDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentList;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupManageAssignmentActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemClickListener{
    public static final String TAG = "GMAA";
    private Button btnAdd;
    private ListView listView;
    private View emptyView;
    private AdaptInfoAssignmentList adapt;
    private ArrayList<AssignmentTitle> assignmentTitle;
    private JasminPreference mPref;
    private AssignmentCheckDialog checkDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_assignment);
        assignmentTitle = new ArrayList<>();
        mPref = JasminPreference.getInstance(this);

        setAssignmentList();
        findViews();
        initViews();
    }

    public void findViews(){
        btnAdd = (Button)findViewById(R.id.btn_add);
        listView = (ListView)findViewById(R.id.lv_assignment);
        emptyView = findViewById(R.id.list_empty);
    }

    public void initViews(){
        adapt = new AdaptInfoAssignmentList(this, R.layout.list_assignment_info, assignmentTitle);
        listView.setAdapter(adapt);
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    public void setAssignmentList(){
        boolean bCheck;
        ArrayList<Object> objects = mPref.getListValue("assignmentList");

        for(int i=0; i<objects.size(); i++){
            Assignment assignment = (Assignment) objects.get(i);
            if(assignmentTitle.size()==0){                      //1.title.size() == 0
                assignmentTitle.add(getTitle(assignment));
            }else{                                                //2.title.size() > 0
                bCheck = false;
                for(int j=0; j<assignmentTitle.size(); j++){
                    if((assignment.getHomework_no() == assignmentTitle.get(j).getHomework_no())){ //2.1.title.homeworkNo 있음
                        bCheck = true;
                        assignmentTitle.get(j).addAssignmentList(assignment);
                        break;
                    }
                }
                if(bCheck == false){
                    assignmentTitle.add(getTitle(assignment));                                    // 2.2.title.homeworkNo 없음
                }
            }
        }
    }


    public AssignmentTitle getTitle(Assignment assignment){
        AssignmentTitle title =  new AssignmentTitle(assignment.getStudy_no(),assignment.getHomework_no(),
                assignment.getHomework_title(),assignment.getHomework_start_date(), assignment);

        return title;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                AssignmentAddDialog dialog = new AssignmentAddDialog(v.getContext());
                dialog.show();
                break;
            case R.id.btn_check_ok:
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.updateCheckAssignment(checkDialog.getUpdateQuery());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        checkDialog.closeDialog();
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_check_cancel:
                checkDialog.closeDialog();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AssignmentTitle assignmentTitle = (AssignmentTitle)parent.getItemAtPosition(position);
        ArrayList<Assignment> assignmentList = assignmentTitle.getAssignmentList();
        checkDialog = new AssignmentCheckDialog(view.getContext(),"과제체크", assignmentList);
        checkDialog.setOkOnClickListener(this);
        checkDialog.setCancelOnClickListener(this);
        checkDialog.show();
    }
}
