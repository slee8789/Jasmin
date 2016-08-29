package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Post;

import java.util.ArrayList;

public class AdaptInfoNoticeList extends ArrayAdapter<Object>  {
    public static final String TAG = "AdaptInfoNoticeList";
    private ArrayList<Object> arraySelectInfo;
    private Context context;
    private onButtonClickListener adptCallback = null;
    private Post listInfo = null;
    public interface onButtonClickListener {
        void onFavoriteState(int position, ImageView v, boolean favorite);

        void onAddReply(int position);

    }

    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public AdaptInfoNoticeList(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoNoticeList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    public ArrayList<Object> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<Object> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_notice_info, null);
        }



        listInfo = (Post) arraySelectInfo.get(position);
        Log.d(TAG, "listInfo : " + listInfo.toString());
        if (listInfo != null) {
//            TextView no = (TextView) view.findViewById(R.id.notice_no);
            TextView title = (TextView) view.findViewById(R.id.notice_title);
            TextView views = (TextView) view.findViewById(R.id.notice_views_count);
            TextView content = (TextView) view.findViewById(R.id.notice_content);
            TextView date = (TextView) view.findViewById(R.id.notice_date);
            TextView writer = (TextView) view.findViewById(R.id.notice_writer);
            LinearLayout replyTxt = (LinearLayout) view.findViewById(R.id.notice_reply);
            replyTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adptCallback.onAddReply(position);
                }
            });
            TextView reply = (TextView) view.findViewById(R.id.notice_reply_count);
            ImageView favorite = (ImageView) view.findViewById(R.id.notice_favorite);
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listInfo.isFavorite() == false) {
                        listInfo.setFavorite(true);
                    } else listInfo.setFavorite(false);
                    adptCallback.onFavoriteState(position, (ImageView) v, listInfo.isFavorite());
                }
            });

//            no.setText(listInfo.getNo());
            title.setText(listInfo.getPost_title());
            views.setText(Integer.toString(listInfo.getHits()));
            content.setText(listInfo.getPost_content());
            date.setText(listInfo.getPost_date());
            writer.setText(listInfo.getUser_name());
            reply.setText(Integer.toString(listInfo.getComment_count()));

        }
        return view;
    }


}