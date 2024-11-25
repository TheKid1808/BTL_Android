package com.example.btl_app_doc_truyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.btl_app_doc_truyen.adapter.StoryAdapter;
import com.example.btl_app_doc_truyen.database.DatabaseManager;
import com.example.btl_app_doc_truyen.model.Story;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {
    Toolbar toolbarHomePage;
    ViewFlipper viewFlipper;
    ListView lvStory;
    Button btnLogout;
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    TextView txtUserName, txtEmail, txtUerID;

    DatabaseManager dbManager;
    StoryAdapter storyAdapter;
    ArrayList<Story> StoryArrayList = new ArrayList<>();

    int userid;
    String username;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbManager = new DatabaseManager(this);
        dbManager.openDB();

        Intent intentGetUser = getIntent();
        userid = intentGetUser.getIntExtra("userid", 0);
        username = intentGetUser.getStringExtra("username");
        email = intentGetUser.getStringExtra("email");
        password = intentGetUser.getStringExtra("password");


        AnhXa();
        ActionBar();
        ActionViewFlipper();
        LoadStory();
        LoadUser();
        ToStoryDetail();
        LogOut();
    }
    //tạo thanh actionbar
    private void ActionBar(){
        setSupportActionBar(toolbarHomePage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //tao icon cho tool bar
        toolbarHomePage.setNavigationIcon(R.drawable.ic_user);
        toolbarHomePage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
    //Chay quang cao
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
    private void AnhXa(){
        toolbarHomePage = findViewById(R.id.toolbarHomePage);
        viewFlipper = findViewById(R.id.viewFlipper);
        lvStory = findViewById(R.id.lvStory);
        btnLogout = findViewById(R.id.item_logout);
        drawerlayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        txtUserName = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtUserEmail);
        txtUerID = findViewById(R.id.txtUserID);
    }

    private void LoadStory(){
        dbManager.loadDataToStoryListView(StoryArrayList);
        storyAdapter = new StoryAdapter(TrangChu.this, R.layout.list_story_item, StoryArrayList);
        storyAdapter.notifyDataSetChanged();
        lvStory.setAdapter(storyAdapter);
    }
    private void ToStoryDetail() {
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentStoryDetail = new Intent(TrangChu.this, StoryDetail.class);

                intentStoryDetail.putExtra("storyid", StoryArrayList.get(position).getStoryID());
                intentStoryDetail.putExtra("storyname", StoryArrayList.get(position).getStoryName());
                intentStoryDetail.putExtra("storyimage", StoryArrayList.get(position).getStoryImage());
                intentStoryDetail.putExtra("storyauthor", StoryArrayList.get(position).getStoryAuthor());
                intentStoryDetail.putExtra("storycontent", StoryArrayList.get(position).getStoryContent());

                startActivity(intentStoryDetail);
            }
        });
    }

    private void LogOut(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogOut = new Intent(TrangChu.this, DangNhap.class);
                startActivity(intentLogOut);
                finish();
            }
        });
    }

    private void LoadUser(){
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            userid = bundle.getInt("userid");
            username = bundle.getString("username");
            email = bundle.getString("email");
            password = bundle.getString("password");

            txtUserName.setText("Tên người dùng: " + username);
            txtUerID.setText(String.valueOf("ID: " + userid));
            txtEmail.setText("Email: " + email);
            btnLogout.setText("Đăng xuất");
        }
        else{
            txtUserName.setText("Tên người dùng: (không rõ)");
            txtUerID.setText("ID: (không rõ)");
            txtEmail.setText("Email: (không rõ)");
            btnLogout.setText("Đăng nhập");
        }
    }


    //Tạo option meny

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSearch){
            Intent intent = new Intent(getApplicationContext(), TimKiemTruyen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}