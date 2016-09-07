package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Comment;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

public class AdaptInfoReplyList extends ArrayAdapter<Object> {
    private ArrayList<Object> arraySelectInfo;
    private Context context;
    private onButtonClickListener adptCallback = null;

    public interface onButtonClickListener {
        void onBtnModify(int position, String content);
        void onBtnDelete(int position);
    }

    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public AdaptInfoReplyList(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public ArrayList<Object> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<Object> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        final int pos = position;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_reply_info, null);
        }
        final Comment listInfo = (Comment)arraySelectInfo.get(position);
        int userNo = ((User) JasminPreference.getInstance(getContext()).getObjectValue("userInfo")).getUser_no();

        if (listInfo != null) {
            TextView id = (TextView) view.findViewById(R.id.reply_id);
            TextView time = (TextView) view.findViewById(R.id.reply_time);
            final TextView content = (TextView) view.findViewById(R.id.reply_content);

            ImageView modify = (ImageView) view.findViewById(R.id.reply_modify);
            ImageView delete = (ImageView) view.findViewById(R.id.reply_delete);

            if(userNo == listInfo.getUser_no()) {
                modify.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);

            } else {
                modify.setVisibility(View.INVISIBLE);
                delete.setVisibility(View.INVISIBLE);
            }

            id.setText(listInfo.getUser_name());
            time.setText(listInfo.getComment_date());
            content.setText(listInfo.getComment_content());
            modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adptCallback.onBtnModify(pos,content.getText().toString());
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adptCallback.onBtnDelete(pos);
                }
            });
        }
        return view;
    }




}