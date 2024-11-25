package com.example.btl_app_doc_truyen;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_app_doc_truyen.database.DatabaseManager;

public class StoryContent extends AppCompatActivity {

    TextView txtStoryName, txtStoryContent;
    int storyID;
    String storyname;
    String storycontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story_content);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            storyID = bundle.getInt("storyID");
            storyname = bundle.getString("storyName");
            storycontent = bundle.getString("storyContent");
        }

        //
        AnhXa();
        LoadData();
    }

    private void AnhXa() {
        txtStoryName = findViewById(R.id.txtStoryName);
        txtStoryContent = findViewById(R.id.txtStoryContent);
    }
    private void LoadData() {;
        txtStoryName.setText(storyname);
        txtStoryContent.setText(storycontent);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_display_story, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFontChange) {
            if(txtStoryName.getTypeface().equals(Typeface.SANS_SERIF)){
                txtStoryName.setTypeface(Typeface.MONOSPACE);
                txtStoryContent.setTypeface(Typeface.MONOSPACE);
            }
            else{
                txtStoryName.setTypeface(Typeface.SANS_SERIF);
                txtStoryContent.setTypeface(Typeface.SANS_SERIF);
            }
            Toast.makeText(this, "Đã thay đổi font chữ", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemFontSize) {
            if(pxToSp(txtStoryName.getTextSize()) == 32 && pxToSp(txtStoryContent.getTextSize()) == 24){
                txtStoryName.setTextSize(24);
                txtStoryContent.setTextSize(16);
            }else{
                txtStoryName.setTextSize(32);
                txtStoryContent.setTextSize(24);
            }
            Toast.makeText(this, "Đã thay đổi cỡ chữ", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    //chuyen tu px sang sp
    private float pxToSp(float px) {
        return px / getResources().getDisplayMetrics().scaledDensity;
    }
}