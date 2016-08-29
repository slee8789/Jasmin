package com.study.jasmin.jasmin.ui.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.AssignmentAddDialog;
import com.study.jasmin.jasmin.ui.dialog.AssignmentCheckDialog;

import java.util.ArrayList;

public class ListViewBtnAdapter extends ArrayAdapter implements View.OnClickListener{

    int resourceId;
    private ListBtnClickListener listBtnClickListener;

    //버튼 클릭 이벤트를 위한 Listener 인터페이스 정의
    public interface  ListBtnClickListener{
        void onListBtnClick(int position);
    }

    public ListViewBtnAdapter(Context context, int resource, ArrayList<ListViewAssignment> list, ListBtnClickListener clickListener) {
        super(context, resource, list);
        this.resourceId = resource;
        this.listBtnClickListener = clickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position ;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId(ListBiewAssignment)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listviewAssignment*/, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        final TextView tvDate   = (TextView) convertView.findViewById(R.id.tv_date);
        final TextView tvTitle  = (TextView) convertView.findViewById(R.id.tv_title);
        final TextView tvStatus = (TextView) convertView.findViewById(R.id.tv_status);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewAssignment listViewAssignment = (ListViewAssignment) getItem(position);

        // 아이템 내 각 위젯에 데이터 반영
        tvDate.setText(listViewAssignment.getDate());
        tvTitle.setText(listViewAssignment.getTitle());
        tvStatus.setText(listViewAssignment.getStatus());

        // button1 클릭 시 함수
        Button btnDelete = (Button) convertView.findViewById(R.id.btn_delete);
        btnDelete.setTag(position);
        btnDelete.setOnClickListener(this);

        return convertView;
    }

    // button1이 눌려졌을 때 실행되는 onClick함수.
    @Override
    public void onClick(View v) {
        // ListBtnClickListener(SettingAssignmentActivity)의 onListBtnClick() 함수 호출.
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)v.getTag()) ;
        }
    }

}
