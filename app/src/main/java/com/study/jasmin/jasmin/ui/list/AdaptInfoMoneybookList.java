package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.MoneyBook;

import java.util.ArrayList;

public class AdaptInfoMoneybookList extends ArrayAdapter<Object> {
    private Context     context;
    private TextView    name, date, reason, money;

    public AdaptInfoMoneybookList(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_moneybook_info, null);
        }

        Object listInfo = getItem(position);

        if (listInfo != null) {
            findViews(view);
            initViews(listInfo);
        }
        return view;
    }

    public  void findViews(View view){
        name    = (TextView) view.findViewById(R.id.moneybook_name);
        date    = (TextView) view.findViewById(R.id.moneybook_date);
        reason  = (TextView) view.findViewById(R.id.moneybook_reason);
        money   = (TextView) view.findViewById(R.id.moneybook_money);
    }

    public void initViews(Object listInfo){
        name.setText(((MoneyBook)listInfo).getName());
        date.setText(((MoneyBook)listInfo).getDate());
        reason.setText(((MoneyBook)listInfo).getContent());
        money.setText(Integer.toString(((MoneyBook)listInfo).getMoney())+"원");
    }
}