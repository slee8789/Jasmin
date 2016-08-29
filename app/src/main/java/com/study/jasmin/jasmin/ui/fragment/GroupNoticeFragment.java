package com.study.jasmin.jasmin.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Post;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupReplyActivity;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoNoticeList;
import com.study.jasmin.jasmin.util.JasminPreference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * A simple {@link Fragment} subclass.
 */
public class GroupNoticeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdaptInfoNoticeList.onButtonClickListener, Callback {
    public static final String TAG = "GroupNoticeFragment";
    private View noticeListHeader;
    private ImageView btnWrite;
    private ImageView btnFavorite;
    private TextView postCount;
    private TextView replyCount;
    private OnFragmentSelectedListener fragmentListener;
    private ListView noticeList;
    private AdaptInfoNoticeList adaptListInfo;
    private JasminPreference mPref;
    private ArrayList<Object> postList;
    private ProgressDialog progressDialog;

    public GroupNoticeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_notice, container, false);
        Log.d(TAG, "GroupNoticeFragment onCreateView");
        findViews(rootView);
        initViews();



        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        progressDialog = ProgressDialog.getInstance(getContext());
        mPref = JasminPreference.getInstance(getContext());
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.postList(mPref.getSelStudyNo());
        call.enqueue(this);
    }

    private void findViews(View rootView) {

        noticeList = (ListView) rootView.findViewById(R.id.list_notice);
        noticeListHeader = rootView.findViewById(R.id.include);
        postCount = (TextView) noticeListHeader.findViewById(R.id.notice_group_number);
        btnWrite = (ImageView) noticeListHeader.findViewById(R.id.notice_group_write);
        btnFavorite = (ImageView) noticeList.findViewById(R.id.notice_favorite);
        replyCount = (TextView) noticeList.findViewById(R.id.notice_reply_count);

    }

    private void initViews() {
        noticeList.setOnItemClickListener(this);
        btnWrite.setOnClickListener(this);
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, "Retro Status Code = " + response.code());
        String strResponse = response.body().toString();
        try {
            JSONObject jsObject = new JSONObject(strResponse);
            JSONArray postObj = jsObject.getJSONArray("postList");
            mPref.putJson("postList",postObj.toString());
            postList = mPref.getListValue("postList");
            adaptListInfo = new AdaptInfoNoticeList(getContext(), R.layout.list_notice_info, postList);
            adaptListInfo.setOnButtonClickListener(this);
            adaptListInfo.setArraySelectInfo(postList);
            noticeList.setAdapter(adaptListInfo);
            postCount.setText(Integer.toString(postList.size()));

        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
        progressDialog.cancel();
        progressDialog.dismiss();
    }



    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ResponseFail = " + call);
    }


    public interface OnFragmentSelectedListener {
        void onFragmentSelected(View v); // 글쓰기버튼용
        void onFragmentSelected(int position); // 리스트 아이템 클릭용
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "GroupNoticeFragment onAttach");
        super.onAttach(context);
        try {
            fragmentListener = (OnFragmentSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentSelectedListener");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notice_group_write:
                Log.d(TAG,"click notice_group_write");
                fragmentListener.onFragmentSelected(v);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "click onFavoriteState position : "+ position + ", id : " + id);
        fragmentListener.onFragmentSelected(position);
    }

    @Override
    public void onFavoriteState(int position, ImageView v, boolean favorite) {
        Log.d(TAG, "click onFavoriteState v : ");
        if(favorite) {
            v.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            v.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

    }

    @Override
    public void onAddReply(int position) {
        Log.d(TAG, "click onAddReply");
        getComment(((Post)(mPref.getListValue("postList")).get(position)).getPost_no(), position);
    }

    private void getComment(int postNo, int position) {
        final int pos = position;
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.commentList(postNo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "Retro Status Code = " + response.code());
                String strResponse = response.body().toString();
                try {
                    JSONObject jsObject = new JSONObject(strResponse);
                    JSONArray commentObj = jsObject.getJSONArray("result");
                    mPref.putJson("commentList",commentObj.toString());

                    Intent intent = new Intent(getContext(), GroupReplyActivity.class);
                    intent.putExtra("post",(Post)(mPref.getListValue("postList")).get(pos));
                    startActivity(intent);
                } catch (JSONException e) {
                    Log.d(TAG, "e : " + e);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "ResponseFail = " + call);
            }
        });
    }
}
