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
import com.study.jasmin.jasmin.entity.Penalty;

import java.util.ArrayList;

public class AdaptInfoReceivablesList extends ArrayAdapter<Object> implements View.OnClickListener{
    private Context context;
    private ListBtnClickListener listBtnClickListener;

    public interface ListBtnClickListener{
        void onListBtnClick(ListInfoReceivables listInfoReceivables);
    }

    public AdaptInfoReceivablesList(Context context, int resource, ArrayList<Object> objects,ListBtnClickListener clickListener) {
        super(context, resource, objects);
        this.context = context;
        this.listBtnClickListener = clickListener ;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_receivables_info, null);
        }

        Penalty listInfo = (Penalty)getItem(position);

        if (listInfo != null) {
            TextView name = (TextView) view.findViewById(R.id.receivables_name);
            TextView date = (TextView) view.findViewById(R.id.receivables_date);
            TextView reason = (TextView) view.findViewById(R.id.receivables_reason);
            TextView money = (TextView) view.findViewById(R.id.receivables_money);
            ImageView cancle = (ImageView)view.findViewById(R.id.receivables_cancel);

            name.setText(listInfo.getUser_name());
            date.setText(listInfo.getPenalty_date());
            reason.setText(listInfo.getPenalty_title());
            money.setText(Integer.toString(-listInfo.getPenalty_money()));

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