package com.study.jasmin.jasmin.ui.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

/**
 * Created by swan on 2016-07-24.
 */
public class ListViewAuthoritySharingAdapter  extends ArrayAdapter implements View.OnClickListener{

    //버튼 클릭 이벤트를 위한 Listener 인터페이스 정의
    public interface  ListBtnClickListener{
        void onListBtnClick(int position);
    }

    int resourceId;
    private ListBtnClickListener listBtnClickListener;

    public ListViewAuthoritySharingAdapter(Context context, int resource, ArrayList<ListViewMember> list) {
        super(context, resource, list);

        this.resourceId = resource;
        //this.listBtnClickListener = clickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position ;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId(ListBiewAssignment)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        final TextView tvName   = (TextView) convertView.findViewById(R.id.textView1);
        final ImageView dwGrade  = (ImageView) convertView.findViewById(R.id.imageView1);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewMember listViewMember = (ListViewMember) getItem(position);

        // 아이템 내 각 위젯에 데이터 반영
        tvName.setText(listViewMember.getName());
        dwGrade.setImageDrawable(listViewMember.getDwGrade());


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
