/*
package com.study.jasmin.jasmin.ui.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

*/
/**
 * Created by leesc on 2016-08-01.
 *//*


public class NYTimesStoryRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<NYTimesStory,
        NYTimesStoryRecyclerViewAdapter.ViewHolder> {

    public NYTimesStoryRecyclerViewAdapter(
            Context context,
            RealmResults<NYTimesStory> realmResults,
            boolean automaticUpdate,
            boolean animateIdType) {
        super(context, realmResults, automaticUpdate, animateIdType);
    }

    public class ViewHolder extends RealmViewHolder {

        public TextView title;
        public TextView publishedDate;
        public ImageView image;
        public TextView storyAbstract;

        public ViewHolder(LinearLayout container) {
            super(container);
            this.title = (TextView) container.findViewById(R.id.title);
            this.publishedDate = (TextView) container.findViewById(R.id.date);
            this.image = (ImageView) container.findViewById(R.id.image);
            this.storyAbstract = (TextView) container.findViewById(R.id.story_abstract);
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.grid_item_view, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final NYTimesStory nyTimesStory = realmResults.get(position);
        viewHolder.title.setText(nyTimesStory.getTitle());
        viewHolder.publishedDate.setText(nyTimesStory.getPublishedDate());
        final RealmList<NYTimesMultimedium> multimedia = nyTimesStory.getMultimedia();
        if (multimedia != null && !multimedia.isEmpty()) {
            Glide.with(GridExampleActivity.this).load(
                    multimedia.get(0).getUrl()).into(viewHolder.image);
        } else {
            viewHolder.image.setImageResource(R.drawable.nytimes_logo);
        }
        viewHolder.storyAbstract.setText(nyTimesStory.getStoryAbstract());
    }
}*/
