package com.example.btl_app_doc_truyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_app_doc_truyen.database.DatabaseManager;
import com.squareup.picasso.Picasso;

public class StoryDetail extends AppCompatActivity {

    ImageView imgStoryImage;
    TextView txtStoryName;
    TextView txtStoryID;
    TextView txtStoryAuthor;
    Button btnRead;
    ImageButton btnFavorite, btnDownload;

    //khoi tao cac bien de lay du lieu cua story ben intent gui ve
    int storyid;
    int storyauthor;
    String storyname;
    String storyimage;
    String storycontent;

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //mo Database
        dbManager = new DatabaseManager(this);
        dbManager.openDB();
        //Lay thong tin cua story ben trang chu
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            storyid = bundle.getInt("storyid");
            storyname = bundle.getString("storyname");
            storyimage = bundle.getString("storyimage");
            storyauthor = bundle.getInt("storyauthor");
            storycontent = bundle.getString("storycontent");
        }
        //
        AnhXa();
        LoadStoryDetail();
        ReadStory();
    }


    private void AnhXa(){
        imgStoryImage = (ImageView) findViewById(R.id.imgStoryImage);
        txtStoryName = (TextView) findViewById(R.id.textStoryName);
        txtStoryID = (TextView) findViewById(R.id.txtStoryID);
        txtStoryAuthor = (TextView) findViewById(R.id.txtStoryAuthor);

        btnRead = (Button) findViewById(R.id.btnRead);
    }

    private void LoadStoryDetail(){
            txtStoryName.setText("Tên Truyện: " + storyname);
            txtStoryID.setText("ID:  " + storyid);
            Picasso.get().load(storyimage).into(imgStoryImage);
            txtStoryAuthor.setText("Tác giả: " + dbManager.getAuthorName(storyauthor));
    }

    private void ReadStory(){
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStoryContent = new Intent(StoryDetail.this, StoryContent.class);

                intentStoryContent.putExtra("storyID", storyid);
                intentStoryContent.putExtra("storyName", storyname);
                intentStoryContent.putExtra("storyContent", storycontent);

                startActivity(intentStoryContent);
            }
        });
    }
}