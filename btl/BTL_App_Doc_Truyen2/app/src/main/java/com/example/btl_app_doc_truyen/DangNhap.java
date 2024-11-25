package com.example.btl_app_doc_truyen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_app_doc_truyen.database.DatabaseManager;
import com.example.btl_app_doc_truyen.model.Users;

public class DangNhap extends AppCompatActivity {
    //Khởi tạo 1 biến DatabaseManager ở đây
    DatabaseManager dbManager;
    SQLiteDatabase db;

    private EditText edtEmail, edtPass;
    private Button btnDangNhap, btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Khởi tạo 1 biến DatabaseManager ở đây
        dbManager = new DatabaseManager(this);
        dbManager.openDB();

        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);

        //Các su kien ở trang đăng nhập sẽ được gọi và thực hiện ở đây
        onClickBtnDangNhap();
        onClickBtnDangKy();
    }
    //Chức năng đăng nhập đưa user vào trnag trang chủ khi người dùng nhập đúng email và mật khẩu
    private void onClickBtnDangNhap(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPass.getText().toString();
                //Kiểm tra thông tin đăng nhập của người dùng đã được nhập đầy đủ hay chưa
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Kiểm tra thông tin đăng nhâpj của người dùng đã được nhập chính xác hay chưa
                if(dbManager.checkUserLogin(email, password)){
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    //Lấy thông tin đăng nhập của người dùng đổ vào biến user và đưa về trang chủ
                    Users user = dbManager.getUserByEmail(email);
                    Intent intentLogin = new Intent(DangNhap.this, TrangChu.class);

                    intentLogin.putExtra("userid", user.getUserID());
                    intentLogin.putExtra("username", user.getUserName());
                    intentLogin.putExtra("email", user.getEmail());
                    intentLogin.putExtra("password", user.getPassword());

                    startActivity(intentLogin);
                }
                else{
                    Toast.makeText(DangNhap.this, "Email hoặc Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void onClickBtnDangKy(){
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDangKy = new Intent(DangNhap.this, DangKy.class);
                startActivity(intentDangKy);
            }
        });
    }
}