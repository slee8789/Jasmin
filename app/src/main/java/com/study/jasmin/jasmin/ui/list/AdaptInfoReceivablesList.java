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

public class AdaptInfoReceivablesList extends ArrayAdapter<Object>{
    private Context context;
    TextView name, date, reason, money;
    private ArrayList<Object> penaltyList;

    public AdaptInfoReceivablesList(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        this.context = context;
        this.penaltyList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Penalty listInfo = (Penalty)getItem(position);
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_receivables_info, null);
        }

        if (listInfo != null) {
            findViews(view);
            initViews(listInfo);
        }
        return view;
    }

    public void findViews(View view){
        name    = (TextView) view.findViewById(R.id.receivables_name);
        date    = (TextView) view.findViewById(R.id.receivables_date);
        reason  = (TextView) view.findViewById(R.id.receivables_reason);
        money   = (TextView) view.findViewById(R.id.receivables_money);
    }

    public void initViews(Penalty listInfo){
        name.setText(listInfo.getUser_name());
        date.setText(listInfo.getPenalty_date());
        reason.setText(listInfo.getPenalty_title());
        money.setText(Integer.toString(-listInfo.getPenalty_money()));
    }
}