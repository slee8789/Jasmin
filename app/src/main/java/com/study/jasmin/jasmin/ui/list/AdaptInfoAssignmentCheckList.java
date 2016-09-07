package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptInfoAssignmentCheckList extends ArrayAdapter<Assignment> {
    private final String TAG = "AdaptInfoAssignmentCheckList";
    private int resourceId;

    private TextView tvName;
    private RadioGroup radioGroup;

    public AdaptInfoAssignmentCheckList(Context context, int resource, ArrayList<Assignment> list) {
        super(context, resource, list);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId, parent, false);
        }

        findViews(convertView);
        initViews(position);

        return convertView;
    }

    public void findViews(View view){
        tvName = (TextView) view.findViewById(R.id.tv_name);
        radioGroup = (RadioGroup)view.findViewById(R.id.rg_status);
    }

    public void initViews(int position){
        Assignment assignment = getItem(position);
        tvName.setText(assignment.getUser_name());
        radioGroup.check((assignment.getHomework_state().equals("제출"))?R.id.rb_option1:R.id.rb_option2);
    }
}