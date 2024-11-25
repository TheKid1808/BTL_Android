package com.example.btl_app_doc_truyen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.btl_app_doc_truyen.model.Story;
import com.example.btl_app_doc_truyen.model.Users;

import java.util.ArrayList;

public class DatabaseManager {
    //Database Manager thực hiện những cây lệnh sql đã được viết ở bên Database Helper
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Context context;
    //Ở context nào hay chính xác là được gọi ở activity naò thì sẽ được thực hiện ở activity đó
    public DatabaseManager(Context context) {
        this.context = context;
    }
    //Đóng mở CSDL
    public DatabaseManager openDB() {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void closeDB() {
        dbHelper.close();
    }
    //Các câu lệnh truy vấn dữ liệu đến bảng user
    //Chức năng thêm 1 User mới vào bảng User
    public void insertUser(Users user){
        ContentValues values = new ContentValues();
        try {
            values.put(DatabaseHelper.UserName, user.getUserName());
            values.put(DatabaseHelper.Email, user.getEmail());
            values.put(DatabaseHelper.Password, user.getPassword());
        }catch (Exception e){
            Toast.makeText(context, "Lỗi: " + e.toString(), Toast.LENGTH_SHORT);
        }
        if(db.insert(DatabaseHelper.TABLE_USER, null, values) == -1){
            Toast.makeText(context, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT);
        }
        else {
            Toast.makeText(context, "Tạo tài khoản thành công", Toast.LENGTH_SHORT);
        }
    }
    //Chức năng thay đổi thông tin cuar một user
    public void updateUser (@NonNull Users user){
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.UserName, user.getUserName());
        values.put(DatabaseHelper.Email, user.getEmail());
        values.put(DatabaseHelper.Password, user.getPassword());

        db.update(DatabaseHelper.TABLE_USER, values, DatabaseHelper.UserID + "=?", new String[]{String.valueOf(user.getUserID())});
    }
    //Chức năng xóa tài khoản của User
    public void deleteUser (int userID){
        try{
            db.delete(DatabaseHelper.TABLE_USER, DatabaseHelper.UserID + "=?", new String[]{String.valueOf(userID)});
        }catch (Exception e){
            Toast.makeText(context, "Xóa thất bại " + e.toString(), Toast.LENGTH_SHORT);
        }
    }
    //Các chức năng được thực hiện bên người dùng bao gồm:
    //Kiểm tra thông tin đăng nhập của người dùng
    public boolean checkUserLogin(String email, String password){
        Cursor cursor = null;
        try{
            cursor = db.query(DatabaseHelper.TABLE_USER,
                    new String[]{   DatabaseHelper.UserID,
                                    DatabaseHelper.UserName,
                                    DatabaseHelper.Email,
                                    DatabaseHelper.Password},
                    "email=? and password=?",
                    new String[]{email, password},
                    null,
                    null,
                    null);
            return cursor != null && cursor.moveToFirst();
        }finally {
            if (cursor.isClosed() == false && cursor != null){
                cursor.close();
            }
        }
    }
    //Lấy thông tin đăng nhập của người dùng
    public Users getUserByEmail(String email){
        Cursor cursor = null;
        try{
            cursor = db.query(DatabaseHelper.TABLE_USER,
                    new String[]{   DatabaseHelper.UserID,
                                    DatabaseHelper.UserName,
                                    DatabaseHelper.Email,
                                    DatabaseHelper.Password},
                    dbHelper.Email + "=?",
                    new String[]{email},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            Users user = new Users(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            return user;
        }finally {
            if (cursor.isClosed() == false && cursor != null){
                cursor.close();
            }
        }
    }
    //Kiểm tra thông tin đăng ký của người dùng
    public boolean checkUserExist(String email){
        Cursor cursor = null;
        try{
            cursor = db.query(DatabaseHelper.TABLE_USER,
                    new String[]{   DatabaseHelper.UserID,
                                    DatabaseHelper.UserName,
                                    DatabaseHelper.Email,
                                    DatabaseHelper.Password},
                    "email=?",
                    new String[]{email},
                    null,
                    null,
                    null);
            return cursor != null && cursor.moveToFirst();
        }finally {
            if (cursor.isClosed() == false && cursor != null){
                cursor.close();
            }
        }
    }
    //Các chức năng bên phía Story bao gồm
    //Lấy truyện đổ vào ListView bên trang chủ
    public void loadDataToStoryListView(ArrayList<Story> StoryArrayList){
        Cursor cursor = db.query(dbHelper.TABLE_STORY, null, null, null, null, null, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            int StoryID = cursor.getInt(0);
            String StoryName = cursor.getString(1);
            String StoryContent = cursor.getString(2);
            String StoryImage = cursor.getString(3);
            int StoryAuthor = cursor.getInt(4);

            StoryArrayList.add(new Story(StoryID, StoryName, StoryContent, StoryImage, StoryAuthor));
            cursor.moveToNext();
        }
        cursor.close();
    }
    //Lấy tên tác giả bằng id tác giả
    public String getAuthorName(int AuthorID){
        Cursor cursor = db.query(dbHelper.TABLE_USER,
                new String[]{   DatabaseHelper.UserID,
                                DatabaseHelper.UserName,
                                DatabaseHelper.Email,
                                DatabaseHelper.Password},
                "userid=?",
                new String[]{String.valueOf(AuthorID)},
                null,
                null,
                null);
        cursor.moveToFirst();
        String AuthorName = cursor.getString(1);
        cursor.close();
        return AuthorName;
    }


    //Các chức năng của Duy Anh
    //Chức năng tìm kiếm Truyện
    public void searchStories(String keyword, ArrayList<Story> searchResultList) {
        Cursor cursor = db.query(dbHelper.TABLE_STORY, null, dbHelper.StoryName + " LIKE ?", new String[]{"%" + keyword + "%"}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int storyID = cursor.getInt(0);
                String storyName = cursor.getString(1);
                String storyContent = cursor.getString(2);
                String storyImage = cursor.getString(3);
                int storyAuthor = cursor.getInt(4);
                searchResultList.add(new Story(storyID, storyName, storyContent, storyImage, storyAuthor));
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    public void addStoryToFavorites(int storyID) {
        ContentValues values = new ContentValues();
        values.put("storyID", storyID);
        db.insert("TBL_Favorites", null, values);
    }

    public void addStoryToDownloads(int storyID) {
        ContentValues values = new ContentValues();
        values.put("storyID", storyID);
        db.insert("TBL_Downloads", null, values);
    }

    public void loadFavorites(ArrayList<Story> favoriteStories) {
        Cursor cursor = db.query("TBL_Favorites", null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int storyIDIndex = cursor.getColumnIndex("storyID");
                if (storyIDIndex != -1) {
                    int storyID = cursor.getInt(storyIDIndex);
                    // Lấy chi tiết truyện từ ID
                    Story story = getStoryById(storyID);
                    if (story != null) {
                        favoriteStories.add(story);
                    }
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    public void loadDownloads(ArrayList<Story> downloadedStories) {
        Cursor cursor = db.query("TBL_Downloads", null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int storyIDIndex = cursor.getColumnIndex("storyID");
                if (storyIDIndex != -1) {
                    int storyID = cursor.getInt(storyIDIndex);
                    // Lấy chi tiết truyện từ ID
                    Story story = getStoryById(storyID);
                    if (story != null) {
                        downloadedStories.add(story);
                    }
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    public Story getStoryById(int storyID) {
        Cursor cursor = db.query(dbHelper.TABLE_STORY, null, "storyID=?", new String[]{String.valueOf(storyID)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("storyID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("storyName"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("storyContent"));
            String image = cursor.getString(cursor.getColumnIndexOrThrow("storyImage"));
            int author = cursor.getInt(cursor.getColumnIndexOrThrow("storyAuthor"));
            cursor.close();
            return new Story(id, name, content, image, author);
        }
        return null;
    }
}
