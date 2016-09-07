package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Study;

import java.util.ArrayList;

public class AdaptInfoGroupList  extends RecyclerView.Adapter<HolderInfoGroupList> {

    private ArrayList<Object> studyList;
    private Context context;

    public AdaptInfoGroupList(Context context, ArrayList<Object> studyList) {
        this.studyList = studyList;
        this.context = context;
    }

    @Override
    public HolderInfoGroupList onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        HolderInfoGroupList rcv = new HolderInfoGroupList(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(HolderInfoGroupList holder, int position) {
        holder.groupName.setText(((Study)studyList.get(position)).getStudy_name());
        holder.groupPhoto.loadUrl("http://54.201.72.195:8081/examples/"+
                                 ((Study)studyList.get(position)).getStudy_no()+
                                  "/cover.jpg");
    }

    @Override
    public int getItemCount() {
        if(this.studyList == null){
            return 0;
        }
        return this.studyList.size();
    }
}