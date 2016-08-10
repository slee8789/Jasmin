package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoReceivablesList extends ArrayAdapter<ListInfoReceivables> implements View.OnClickListener{
    private ArrayList<ListInfoReceivables> arraySelectInfo;
    private Context context;
    private ListBtnClickListener listBtnClickListener;

    public interface ListBtnClickListener{
        void onListBtnClick(ListInfoReceivables listInfoReceivables);
    }

    public AdaptInfoReceivablesList(Context context, int resource, ArrayList<ListInfoReceivables> objects,ListBtnClickListener clickListener) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
        this.listBtnClickListener = clickListener ;

    }

    public ArrayList<ListInfoReceivables> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoReceivables> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_receivables_info, null);
        }
        final ListInfoReceivables listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView name = (TextView) view.findViewById(R.id.receivables_name);
            TextView date = (TextView) view.findViewById(R.id.receivables_date);
            TextView reason = (TextView) view.findViewById(R.id.receivables_reason);
            TextView money = (TextView) view.findViewById(R.id.receivables_money);
            ImageView cancle = (ImageView)view.findViewById(R.id.receivables_cancel);

            name.setText(listInfo.getName());
            date.setText(listInfo.getDate());
            reason.setText(listInfo.getReason());
            money.setText(listInfo.getMoney());

            cancle.setTag(listInfo);
            cancle.setOnClickListener(this);
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((ListInfoReceivables)v.getTag()) ;

        }

    }
}