package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoReplyList;
import com.study.jasmin.jasmin.ui.list.ListInfoReply;

import java.util.ArrayList;

public class GroupReplyActivity extends AppCompatActivity implements View.OnClickListener, AdaptInfoReplyList.onButtonClickListener{
    public static final String TAG = "GroupReplyActivity";
    private View footer;
    private ImageView btnWrite;
    private ImageView btnDelete;
    private ImageView btnFooterBack;
    private ImageView btnFooterWrite;
    private ImageView btnFooterFavorite;
    private ImageView btnFooterReply;
    private ListView replyList;
    private ArrayList<ListInfoReply> arrayListInfo = new ArrayList<ListInfoReply>();
    private AdaptInfoReplyList adaptListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_reply);
        findViews();
        initViews();


        ArrayList<String> ReplyColumnTest0 = new ArrayList<String>();
        ReplyColumnTest0.add("id 0");
        ReplyColumnTest0.add("content 0");

        ArrayList<String> ReplyColumnTest1 = new ArrayList<String>();
        ReplyColumnTest1.add("id 1");
        ReplyColumnTest1.add("content 1");

        ArrayList<String> ReplyColumnTest2 = new ArrayList<String>();
        ReplyColumnTest2.add("id 2");
        ReplyColumnTest2.add("content 2");

        ArrayList<ArrayList<String>> ReplyRowTest = new ArrayList<ArrayList<String>>();
        ReplyRowTest.add(ReplyColumnTest0);
        ReplyRowTest.add(ReplyColumnTest1);
        ReplyRowTest.add(ReplyColumnTest2);

        adaptListInfo = new AdaptInfoReplyList(getApplicationContext(), R.layout.list_reply_info, arrayListInfo);
        adaptListInfo.setOnButtonClickListener(this);
        replyList.setAdapter(adaptListInfo);
        addSelectInfo(ReplyRowTest);
        adaptListInfo.setArraySelectInfo(arrayListInfo);
        replyList.setAdapter(adaptListInfo);

    }

    private void findViews() {
        replyList = (ListView)findViewById(R.id.list_reply);
//        btnWrite = (ImageView) replyList.findViewById(R.id.reply_write);
//        btnDelete = (ImageView) replyList.findViewById(R.id.reply_delete);
        footer = findViewById(R.id.include);
        btnFooterBack = (ImageView) footer.findViewById(R.id.btn_back);
        btnFooterWrite = (ImageView) footer.findViewById(R.id.btn_write);
        btnFooterFavorite = (ImageView) footer.findViewById(R.id.btn_favorite);
        btnFooterReply = (ImageView) footer.findViewById(R.id.btn_reply);
    }

    private void initViews() {
//        btnWrite.setOnClickListener(this);
//        btnDelete.setOnClickListener(this);
        btnFooterBack.setOnClickListener(this);
        btnFooterWrite.setOnClickListener(this);
        btnFooterFavorite.setOnClickListener(this);
        btnFooterReply.setOnClickListener(this);
    }

    public void addSelectInfo(ArrayList<ArrayList<String>> parseredList) {
        for (int i = 0; i < parseredList.size(); i++) {
            ListInfoReply selectInfo = new ListInfoReply();
            selectInfo.setId(parseredList.get(i).get(0));
            selectInfo.setContent(parseredList.get(i).get(1));

            arrayListInfo.add(selectInfo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_exit:
                Log.d(TAG, "click btn_exit");
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.reply_write:
                Log.d(TAG, "reply_write clicked");
                break;

            case R.id.reply_delete:
                Log.d(TAG, "reply_delete clicked");
                break;

            case R.id.btn_back:
                Log.d(TAG, "btn_back clicked");
                break;

            case R.id.btn_write:
                Log.d(TAG, "btn_write clicked");
                break;

            case R.id.btn_favorite:
                Log.d(TAG, "btn_favorite clicked");
                break;

            case R.id.btn_reply:
                Log.d(TAG, "btn_reply clicked");
//                startActivity(new Intent(this, GroupReplyActivity.class));
                break;
        }
    }

    @Override
    public void onBtnWrite() {
        Log.d(TAG, "onBtnWrite clicked");
    }

    @Override
    public void onBtnDelete() {
        Log.d(TAG, "onBtnDelete clicked");
    }
}
