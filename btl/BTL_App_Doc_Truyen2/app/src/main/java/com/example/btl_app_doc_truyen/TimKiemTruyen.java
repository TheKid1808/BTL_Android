package com.example.btl_app_doc_truyen;

import static com.example.btl_app_doc_truyen.database.DatabaseHelper.TABLE_STORY;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.btl_app_doc_truyen.adapter.StoryAdapter;
import com.example.btl_app_doc_truyen.database.DatabaseManager;
import com.example.btl_app_doc_truyen.model.Story;

import java.util.ArrayList;

public class TimKiemTruyen extends AppCompatActivity {
    Toolbar toolbarSearch;
    EditText edtSearch;
    ListView lvSearchResult;
    DatabaseManager dbManager;
    StoryAdapter storyAdapter;
    ArrayList<Story> searchResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem_truyen);

        AnhXa();
        ActionBar();
        dbManager = new DatabaseManager(this);
        dbManager.openDB();
        searchResultList = new ArrayList<>();

        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            String keyword = edtSearch.getText().toString().trim();
            if (!keyword.isEmpty()) {
                searchStories(keyword);
            } else {
                Toast.makeText(TimKiemTruyen.this, "Vui lòng nhập từ khóa để tìm kiếm", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Chuyển hướng sang trang chi tiết truyện
                Intent intentStoryDetail = new Intent(TimKiemTruyen.this, StoryDetail.class);
                Story selectedStory = searchResultList.get(position);
                intentStoryDetail.putExtra("storyid", selectedStory.getStoryID());
                intentStoryDetail.putExtra("storyname", selectedStory.getStoryName());
                intentStoryDetail.putExtra("storyimage", selectedStory.getStoryImage());
                intentStoryDetail.putExtra("storyauthor", selectedStory.getStoryAuthor());
                intentStoryDetail.putExtra("storycontent", selectedStory.getStoryContent());
                startActivity(intentStoryDetail);
            }
        });
    }

    private void AnhXa() {
        toolbarSearch = findViewById(R.id.toolbarSearch);
        edtSearch = findViewById(R.id.edtSearch);
        lvSearchResult = findViewById(R.id.lvSearchResult);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSearch.setNavigationOnClickListener(v -> finish());
    }

    private void searchStories(String keyword) {
        searchResultList.clear();
        dbManager.searchStories(keyword, searchResultList);
        if (searchResultList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy kết quả nào phù hợp", Toast.LENGTH_SHORT).show();
        } else {
            storyAdapter = new StoryAdapter(TimKiemTruyen.this, R.layout.list_story_item, searchResultList);
            lvSearchResult.setAdapter(storyAdapter);
        }
    }
}