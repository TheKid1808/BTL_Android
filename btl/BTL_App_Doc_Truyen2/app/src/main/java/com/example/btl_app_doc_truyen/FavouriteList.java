package com.example.btl_app_doc_truyen;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_app_doc_truyen.adapter.StoryAdapter;
import com.example.btl_app_doc_truyen.database.DatabaseManager;
import com.example.btl_app_doc_truyen.model.Story;

import java.util.ArrayList;

public class FavouriteList extends AppCompatActivity {
    ListView lvFavorites;
    DatabaseManager dbManager;
    ArrayList<Story> favoriteStories;
    StoryAdapter storyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        lvFavorites = findViewById(R.id.lvFavorites);
        dbManager = new DatabaseManager(this);
        dbManager.openDB();
        favoriteStories = new ArrayList<>();
        dbManager.loadFavorites(favoriteStories);
        storyAdapter = new StoryAdapter(this, R.layout.list_story_item, favoriteStories);
        lvFavorites.setAdapter(storyAdapter);
    }
}