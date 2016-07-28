package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoNoticeList extends ArrayAdapter<ListInfoNotice> {
    private ArrayList<ListInfoNotice> arraySelectInfo;
    private Context context;

    public interface onButtonClickListener {
        void onAddBtnClick(ListInfoNotice selectInfo);
        // void onSetNameText(SelectInfo selectInfo);
    }

    public AdaptInfoNoticeList(Context context, int resource, ArrayList<ListInfoNotice> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoNoticeList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    private onButtonClickListener adptCallback = null;


    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ArrayList<ListInfoNotice> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoNotice> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_notice_info, null);
            LinearLayout noticeLayout = (LinearLayout) view.findViewById(R.id.notice_layout);
            if (position % 2 == 0) {
                noticeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayWhite));
            }
        }
        final ListInfoNotice listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView no = (TextView) view.findViewById(R.id.notice_no);
            TextView title = (TextView) view.findViewById(R.id.notice_title);
            TextView date = (TextView) view.findViewById(R.id.notice_date);
            TextView writer = (TextView) view.findViewById(R.id.notice_writer);
            title.setText(listInfo.getTitle());
            no.setText(listInfo.getNo());
            date.setText(listInfo.getDate());
            writer.setText(listInfo.getWriter());
        }
        return view;
    }

}