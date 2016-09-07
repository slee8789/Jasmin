package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.MoneyEct;
import com.study.jasmin.jasmin.entity.Penalty;
import com.study.jasmin.jasmin.entity.Study;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

public class AdaptInfoMoneyEctList extends ArrayAdapter<Object> {
    private Context context;
    TextView name, date, reason, money, inout;

    public AdaptInfoMoneyEctList(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MoneyEct listInfo = (MoneyEct)getItem(position);
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_moneyect_info, null);
        }

        findViews(view);
        initViews(listInfo);

        return view;
    }

    public void findViews(View view){
        name    = (TextView) view.findViewById(R.id.money_ect_name);
        date    = (TextView) view.findViewById(R.id.money_ect_date);
        reason  = (TextView) view.findViewById(R.id.money_ect_reason);
        money   = (TextView) view.findViewById(R.id.money_ect_money);
        inout   = (TextView) view.findViewById(R.id.money_ect_inout);
    }

    public void initViews(MoneyEct listInfo){
        name.setText("공동");
        date.setText(listInfo.getInout_date());
        reason.setText(listInfo.getInout_reason());
        money.setText(Integer.toString(listInfo.getInout_money()));
        inout.setText((listInfo.getInout_money()>0)?"입금":"출금");
    }
}
