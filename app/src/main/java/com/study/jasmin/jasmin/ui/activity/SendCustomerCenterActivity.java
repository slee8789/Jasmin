package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SendCustomerCenterActivity extends AppCompatActivity {
    ExpandableListView listView;

    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

    private String email;
    private String title;
    private String editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_customer_center);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            email = extras.getString("email");
            title = extras.getString("title");
            editText = extras.getString("editText");
        }

        listView = (ExpandableListView) this.findViewById(R.id.expandableListView);
        setArrayData();
        listView.setAdapter(new BaseExpandableAdapter(this, arrayGroup, arrayChild));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SendCustomerCenterActivity2.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
            }
        });
    }

    private void setArrayData() {
        arrayGroup.add(email + "  " + title);

        ArrayList<String> question1 = new ArrayList<String>();
        question1.add(editText);

        arrayChild.put(arrayGroup.get(0), question1);
    }

}
