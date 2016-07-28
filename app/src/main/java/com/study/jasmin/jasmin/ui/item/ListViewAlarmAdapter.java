package com.study.jasmin.jasmin.ui.item;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class ListViewAlarmAdapter  extends ArrayAdapter implements View.OnClickListener{

    Drawable drRepeatTrue  = (ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));
    Drawable drRepeatFalse = (ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));
    Drawable drSettingTrue  = (ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));
    Drawable drSettingFalse = (ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));

    //버튼 클릭 이벤트를 위한 Listener 인터페이스 정의
    public interface  ListBtnClickListener{
        void onListBtnClick(int position);
    }

    int resourceId;
    private ListBtnClickListener listBtnClickListener;

    public ListViewAlarmAdapter(Context context, int resource, ArrayList<ListViewAlarmItem> listItem, ListBtnClickListener clickListener) {
        super(context, resource, listItem);
        this.resourceId = resource;
        this.listBtnClickListener = clickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position ;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        final TextView tvaampm      = (TextView) convertView.findViewById(R.id.tv_ampm);
        final TextView tvTime       = (TextView) convertView.findViewById(R.id.tv_time);
        final TextView tvDayMon     = (TextView) convertView.findViewById(R.id.tv_day_mon);
        final TextView tvDayTues    = (TextView) convertView.findViewById(R.id.tv_day_tues);
        final TextView tvDayWed     = (TextView) convertView.findViewById(R.id.tv_day_wed);
        final TextView tvDayThur    = (TextView) convertView.findViewById(R.id.tv_day_thur);
        final TextView tvDayFri     = (TextView) convertView.findViewById(R.id.tv_day_fri);
        final TextView tvDaySat     = (TextView) convertView.findViewById(R.id.tv_day_sat);
        final TextView tvDaySun     = (TextView) convertView.findViewById(R.id.tv_day_sun);
        final ImageView ivRepeat    = (ImageView) convertView.findViewById(R.id.iv_repeat);
        final ImageView ivSetting   = (ImageView) convertView.findViewById(R.id.iv_setting);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewAlarmItem listViewItem = (ListViewAlarmItem) getItem(position);

        // 아이템 내 각 위젯에 데이터 반영
        tvaampm.setText("am");
        tvTime.setText("10:00");

        //tvAmpm.setText(listViewItem.getStrAmpm());
        //tvTime.setText(listViewItem.getStrTime());
        ivRepeat.setImageDrawable(drRepeatTrue);
        ivSetting.setImageDrawable(drSettingTrue);

        //ivRepeat.setImageDrawable(listViewItem.getRepeat()==false? drRepeatFalse : drRepeatTrue);
        //ivSetting.setImageDrawable(listViewItem.getSetting()==false? drSettingFalse : drSettingTrue);

        //Day 반영
        TextView[] tvDays    = {tvDayMon, tvDayTues, tvDayWed, tvDayThur, tvDayFri, tvDaySat, tvDaySun};
        String[]   strDays   = {"월","화","수","목","금","토","일"};
        boolean[] bDays     = listViewItem.getArrDay();

        for(int i=0; i<7; i++){
            TextView tv = tvDays[i];
            tv.setText(strDays[i]);
            tv.setTextColor(Color.BLUE );
        }

        // button 클릭 시 함수
        //Button btnAdd = (Button) convertView.findViewById(R.id.btn_add);
        //btnAdd.setTag(position);
        //btnAdd.setOnClickListener(this);

        return convertView;
    }

    // button1이 눌려졌을 때 실행되는 onClick함수.
    public void onClick(View v) {
        // ListBtnClickListener(SettingAssignmentActivity)의 onListBtnClick() 함수 호출.
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)v.getTag()) ;
        }
    }
}

