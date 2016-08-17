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
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendance;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewAlarmAdapter  extends ArrayAdapter<ListViewAlarmItem> implements View.OnClickListener{

    private TextView tvTime;
    private TextView tvRepeat;
    private TextView tvContext;
    private TextView[] tvDays = new TextView[7]; // 0~6 : 월화수목금토일
    private ImageView ivSetting;
    private boolean bSetting;

    int resourceId;
    private ListBtnClickListener listBtnClickListener;

    public interface  ListBtnClickListener{
        void onListBtnClick(int position, ImageView iv, boolean bSet);
    }

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

        findView(convertView);
        initView(position);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        //final ListViewAlarmItem listViewItem = (ListViewAlarmItem) getItem(position);


        // button 클릭 시 함수
        //Button btnAdd = (Button) convertView.findViewById(R.id.btn_add);
        //btnAdd.setTag(position);
        //btnAdd.setOnClickListener(this);


        return convertView;
    }

    public void findView(View view){
        int idDays[] = {R.id.tv_day_mon, R.id.tv_day_tue, R.id.tv_day_wed, R.id.tv_day_thur,
                        R.id.tv_day_fri,R.id.tv_day_sat, R.id.tv_day_sun};

        tvTime          = (TextView) view.findViewById(R.id.tv_time);
        tvRepeat        = (TextView) view.findViewById(R.id.tv_repeat);
        tvContext       = (TextView)view.findViewById(R.id.tv_context);
        ivSetting       = (ImageView) view.findViewById(R.id.iv_setting);

        for(int i =0; i<7; i++){
            tvDays[i] = (TextView) view.findViewById(idDays[i]);
        }

    }

    public void initView(int position){
        ListViewAlarmItem list = (ListViewAlarmItem)getItem(position);

        String strDays[] = {"월","화","수","목","금","토","일"};
        boolean bDays[] = list.getArrDay();

        tvTime.setText(list.getTime());
        tvRepeat.setText(list.getRepeat()?"반복":"");
        tvContext.setText(list.getContext());
        bSetting = list.getSetting();
        ivSetting.setImageDrawable(bSetting?(ContextCompat.getDrawable(getContext(), R.drawable.ic_alarm_black_24dp))
                                            : (ContextCompat.getDrawable(getContext(), R.drawable.ic_alarm_off_black_24dp)));
        ivSetting.setTag(position);
        ivSetting.setOnClickListener(this);

        for(int i =0; i<7; i++){
            tvDays[i].setText(strDays[i]);
            tvDays[i].setTextColor(bDays[i]?Color.BLACK:Color.BLUE);
        }

    }

    public void onClick(View v) {
        if (this.listBtnClickListener != null) {
            bSetting = !bSetting;
            this.listBtnClickListener.onListBtnClick((int)v.getTag(),(ImageView)v, bSetting) ;
        }
     //   Toast.makeText(this.getContext(), "어뎁터 아이템 클릭 테스트", Toast.LENGTH_SHORT).show();
    }

}



