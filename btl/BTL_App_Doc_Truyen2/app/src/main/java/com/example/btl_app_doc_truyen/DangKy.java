package com.example.btl_app_doc_truyen;

import android.content.Intent;
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

public class DangKy extends AppCompatActivity {
    //Khởi tạo 1 biến DatabaseManager ở đây
    DatabaseManager dbManager;

    private EditText edtUserName, edtEmail, edtPass, edtConfirmPass;
    private Button btnDangKy, btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Gọi biến DatabaseManager ở đây
        dbManager = new DatabaseManager(this);
        dbManager.openDB();

        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        //Các su kien ở trang đăng ký sẽ được gọi và thực hiện ở đây
        onClickbtnDangKy();
        onClickbtnDangNhap();
    }

    private void onClickbtnDangNhap(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDangNhap = new Intent(DangKy.this, DangNhap.class);
                startActivity(intentDangNhap);
                finish();
            }
        });
    }
    private void onClickbtnDangKy(){
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPass.getText().toString();
                String confirmPass = edtConfirmPass.getText().toString();

                //Kiểm tra thông tin đăng ký của người dùng đã được nhập đầy đủ hay chưa
                if (userName.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(DangKy.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Kiểm tra phần mật khẩu và xác nhận mật khẩu có trùng khớp hay chưa
                if (!password.equals(edtConfirmPass.getText().toString())){
                    Toast.makeText(DangKy.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Kiểm tra thông tin đăng ký của người dùng đã được nhập chính xác hay chưa
                if(dbManager.checkUserExist(email)){
                    Toast.makeText(DangKy.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    dbManager.insertUser(new Users(userName, email, password));
                    Intent intentDangNhap = new Intent(DangKy.this, DangNhap.class);
                    startActivity(intentDangNhap);
                }
            }
        });
    }
}