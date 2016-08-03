package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoNoticeList extends ArrayAdapter<ListInfoNotice> implements View.OnClickListener {
    public static final String TAG = "AdaptInfoNoticeList";
    private ArrayList<ListInfoNotice> arraySelectInfo;
    private Context context;
    private onButtonClickListener adptCallback = null;
    private ListInfoNotice listInfo = null;

    public interface onButtonClickListener {
        void onFavoriteState(ImageView v, boolean favorite);
        void onAddReply();

    }

    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
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
        }
        listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
//            TextView no = (TextView) view.findViewById(R.id.notice_no);
            TextView title = (TextView) view.findViewById(R.id.notice_title);
            TextView views = (TextView) view.findViewById(R.id.notice_views_count);
//            View content = (View) view.findViewById(R.id.notice_content);
            TextView date = (TextView) view.findViewById(R.id.notice_date);
            TextView writer = (TextView) view.findViewById(R.id.notice_writer);
            LinearLayout replyTxt = (LinearLayout) view.findViewById(R.id.notice_reply);
            replyTxt.setOnClickListener(this);
            TextView reply = (TextView) view.findViewById(R.id.notice_reply_count);
            ImageView favorite = (ImageView) view.findViewById(R.id.notice_favorite);
            favorite.setOnClickListener(this);

//            no.setText(listInfo.getNo());
            title.setText(listInfo.getTitle());
            views.setText(listInfo.getViews());
//            Todo: 컨텐츠 이미지포함 뷰 고려
            date.setText(listInfo.getDate());
            writer.setText(listInfo.getWriter());
            reply.setText(listInfo.getReply());

        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notice_reply:
                adptCallback.onAddReply();
                break;

            case R.id.notice_favorite:
                if(listInfo.getFavorite() == false) {
                    listInfo.setFavorite(true);
                } else listInfo.setFavorite(false);
                adptCallback.onFavoriteState((ImageView)v, listInfo.getFavorite());
                break;
        }
    }

}