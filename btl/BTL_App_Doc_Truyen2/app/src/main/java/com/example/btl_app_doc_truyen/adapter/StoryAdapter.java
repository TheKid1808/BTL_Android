package com.example.btl_app_doc_truyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl_app_doc_truyen.R;
import com.example.btl_app_doc_truyen.model.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryAdapter extends ArrayAdapter<Story> {
    Context context;
    int layout;
    ArrayList<Story> storyArrayList;

    public StoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Story> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.storyArrayList = objects;
    }

    @Override
    public int getCount() {
        return storyArrayList.size();
    }

    @Override
    public Story getItem(int position) {
        return storyArrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;

        if(currentView == null){
            currentView = LayoutInflater.from(context).inflate(layout, parent, false);
        }

        Story story = getItem(position);
        ImageView imgStoryImage = currentView.findViewById(R.id.imgStoryImage);
        TextView txtStoryName = currentView.findViewById(R.id.txtStoryName);

        assert story != null;

        Picasso.get().load(story.getStoryImage()).into(imgStoryImage);
        txtStoryName.setText(story.getStoryName());


        return currentView;
    }
}
