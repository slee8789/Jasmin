package com.study.jasmin.jasmin.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;

public class HolderInfoGroupList extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView groupName;
    public ImageView groupPhoto;

    public HolderInfoGroupList(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        groupName = (TextView)itemView.findViewById(R.id.group_name);
        groupPhoto = (ImageView)itemView.findViewById(R.id.group_photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked group Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}