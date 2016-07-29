package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoGroupList extends ArrayAdapter<ListInfoGroup> implements View.OnClickListener{
    public static final String TAG = "AdaptInfoGroupList";
    private ArrayList<ListInfoGroup> arraySelectInfo;
    private Context context;
    private onButtonClickListener adptCallback = null;

    public interface onButtonClickListener {
        void onBtnWrite();
        void onBtnDelete();
    }

    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public AdaptInfoGroupList(Context context, int resource, ArrayList<ListInfoGroup> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public ArrayList<ListInfoGroup> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoGroup> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group_info, null);
        }
        final ListInfoGroup listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            ImageView cover = (ImageView) view.findViewById(R.id.group_cover);
            Button invite = (Button) view.findViewById(R.id.group_invite);

//            cover.setText(listInfo.getCover());
//            invite.setText(listInfo.getInvite());
            cover.setOnClickListener(this);
            invite.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_cover:
                Log.d(TAG, "click group_cover");
//                adptCallback.onBtnWrite();
                break;

            case R.id.group_invite:
                Log.d(TAG, "click group_invite");
//                adptCallback.onBtnDelete();
                break;
        }
    }



}