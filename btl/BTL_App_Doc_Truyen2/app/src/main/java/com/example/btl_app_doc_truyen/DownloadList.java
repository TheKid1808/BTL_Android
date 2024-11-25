package com.example.btl_app_doc_truyen;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_app_doc_truyen.adapter.StoryAdapter;
import com.example.btl_app_doc_truyen.database.DatabaseManager;
import com.example.btl_app_doc_truyen.model.Story;

import java.util.ArrayList;

public class DownloadList extends AppCompatActivity {
    ListView lvDownloads;
    DatabaseManager dbManager;
    ArrayList<Story> downloadedStories;
    StoryAdapter storyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_list);
        lvDownloads = findViewById(R.id.lvDownloads);
        dbManager = new DatabaseManager(this);
        dbManager.openDB();
        downloadedStories = new ArrayList<>();
        dbManager.loadDownloads(downloadedStories);
        storyAdapter = new StoryAdapter(this, R.layout.list_story_item, downloadedStories);
        lvDownloads.setAdapter(storyAdapter);
    }
}