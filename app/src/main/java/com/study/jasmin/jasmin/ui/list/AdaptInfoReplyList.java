package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoReplyList extends ArrayAdapter<ListInfoReply> implements View.OnClickListener{
    private ArrayList<ListInfoReply> arraySelectInfo;
    private Context context;
    private onButtonClickListener adptCallback = null;

    public interface onButtonClickListener {
        void onBtnModify();
        void onBtnDelete();
    }

    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public AdaptInfoReplyList(Context context, int resource, ArrayList<ListInfoReply> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public ArrayList<ListInfoReply> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoReply> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_reply_info, null);
        }
        final ListInfoReply listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView id = (TextView) view.findViewById(R.id.reply_id);
            TextView content = (TextView) view.findViewById(R.id.reply_content);
            ImageView modify = (ImageView) view.findViewById(R.id.reply_modify);
            ImageView delete = (ImageView) view.findViewById(R.id.reply_delete);

            id.setText(listInfo.getId());
            content.setText(listInfo.getContent());
            modify.setOnClickListener(this);
            delete.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reply_modify:
                adptCallback.onBtnModify();
                break;

            case R.id.reply_delete:
                adptCallback.onBtnDelete();
                break;
        }
    }



}