package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoMoneybookList extends ArrayAdapter<ListInfoMoneybook> {
    private ArrayList<ListInfoMoneybook> arraySelectInfo;
    private Context context;

    public interface onButtonClickListener {
        void onAddBtnClick(ListInfoMoneybook selectInfo);
        // void onSetNameText(SelectInfo selectInfo);
    }

    public AdaptInfoMoneybookList(Context context, int resource, ArrayList<ListInfoMoneybook> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoMoneybookList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    private onButtonClickListener adptCallback = null;


    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ArrayList<ListInfoMoneybook> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoMoneybook> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_moneybook_info, null);
        }
        final ListInfoMoneybook listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView name = (TextView) view.findViewById(R.id.moneybook_name);
            TextView date = (TextView) view.findViewById(R.id.moneybook_date);
            TextView reason = (TextView) view.findViewById(R.id.moneybook_reason);
            TextView money = (TextView) view.findViewById(R.id.moneybook_money);
            name.setText(listInfo.getName());
            date.setText(listInfo.getDate());
            reason.setText(listInfo.getReason());
            money.setText(listInfo.getMoney());
        }
        return view;
    }

}