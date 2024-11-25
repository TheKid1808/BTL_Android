package com.example.btl_app_doc_truyen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Tạo cơ sở dữ liệu cho app đọc truyện
    public static final String DATABASE_NAME = "AppDocTruyen.db";
    public static final int DATABASE_VERSION = 1;
    //Tạo bảng User và các trường dữ liêuj để quản lý thông tin người dùng
    public static final String TABLE_USER = "TBL_User";
    public static final String UserID = "userID";
    public static final String UserName = "userName";
    public static final String Email = "email";
    public static final String Password = "password";
    //Tạo bảng Story và các trường dữ liệu để quản lý thông tin truyện
    public static final String TABLE_STORY = "TBL_Story";
    public static final String StoryID = "storyID";
    public static final String StoryName = "storyName";
    public static final String StoryContent = "storyContent";
    public static final String StoryImage = "storyImage";
    public static final String StoryAuthor = "storyAuthor";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Xây dựng câu lệnh để tạo bảng User
        String CreateTBL_User = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
                                        UserID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        UserName +" TEXT," +
                                        Email +" TEXT UNIQUE," +
                                        Password +" TEXT)";
        db.execSQL(CreateTBL_User);

        String InsertTBL_User = "INSERT INTO " + TABLE_USER + " ("
                + UserName + ", " + Email + ", " + Password + ") VALUES "
                + "('ducanh', 'ducanh@gmail.com', '123456')";
        db.execSQL(InsertTBL_User);
        //Xây dựng câu lệnh để tạo bảng Story
        String CreateTBL_Story = "CREATE TABLE IF NOT EXISTS " + TABLE_STORY + " (" +
                StoryID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StoryName + " TEXT, " +
                StoryContent + " TEXT, " +
                StoryImage + " TEXT, " +
                StoryAuthor + " INTEGER, " +
                "FOREIGN KEY (" + StoryAuthor + ") REFERENCES " + TABLE_USER + "(" + UserID + "))";

        String InsertTBL_Story =
                "INSERT INTO " + TABLE_STORY + " ("
                        + StoryName + ", " + StoryContent + ", " + StoryImage + ", " + StoryAuthor + ") VALUES " +
                        "('Rùa và Thỏ', " +
                        "'Phần 1:\n\n" +
                        "Ngày xửa ngày xưa, có một con Rùa và một con Thỏ sống trong một khu rừng xinh đẹp và yên tĩnh. Ngày ngày chúng vui chơi với nhau như hai người bạn thân. Một hôm, Thỏ và Rùa cãi nhau xem ai nhanh hơn. Rồi chúng quyết định giải quyết việc tranh luận bằng một cuộc thi chạy đua. Thỏ và Rùa đồng ý lộ trình và bắt đầu cuộc đua. Thỏ xuất phát nhanh như tên bắn và chạy thục mạng rất nhanh, khi thấy rằng mình đã khá xa Rùa, Thỏ nghĩ nên nghỉ cho đỡ mệt dưới một bóng cây xum xuê lá bên vệ đường. Vì quá tự tin vào khả năng giành chiến thắng của mình, Thỏ ngồi dưới bóng cây và nhanh chóng ngủ thiếp đi. Rùa chạy mãi rồi cũng đến nơi, thấy Thỏ đang ngủ ngon giấc Rùa từ từ vượt qua Thỏ và về đích trước Thỏ. Khi Thỏ thức dậy thì rùa đã đến đích và trở thành người chiến thắng. Lúc này Thỏ biết mình đã thua cuộc vì quá tự tin vào khả năng của mình, còn Rùa chiến thắng vì kiên trì bám đuổi mục tiêu và làm việc hết sức trong khả năng của mình, cộng với một chút may mắn và giành chiến thắng.\n\n" +
                        "Ý nghĩa câu chuyện phần 1: truyện giáo dục đức tính kiên trì, siêng năng, nhẫn nại. Những người nhanh nhẹn nhưng cẩu thả trong suy nghĩ cuối cùng cũng sẽ bị đánh bại bởi người kiên nhẫn, siêng năng dù họ chậm hơn rất nhiều.\n\n" +
                        "Phần 2:\n\n" +
                        "Thỏ vô cùng thất vọng vì để thua Rùa, nó nhận ra rằng nó thua chính vì quá tự tin, bất cẩn và thiếu kỷ luật. Nếu nó không xem mọi thứ quá dễ dàng và chắc thắng thì rùa không thể có cơ hội hạ được nó. Vì thế, Thỏ quyết định thách thức Rùa bằng một cuộc đua mới. Rùa đồng ý. Lần này, Thỏ chạy với tất cả sức lực của nó và chạy một mạch về đích. Nó bỏ xa Rùa đến mấy dặm đường.\n\n" +
                        "Ý nghĩa câu chuyện phần 2: Biết sai và sửa sai là một đức tính tốt, đó chính là lý do giúp anh chàng thỏ giành được chiến thắng ở cuộc đua thứ 2. Mẹ hãy giải thích cho bé hiểu rằng trong công việc hàng ngày giữa một người chậm, cẩn thận và đáng tin cậy với một người nhanh nhẹn, đáng tin cậy, chắc chắn người nhanh nhẹn sẽ được trọng dụng hơn nhiều và họ sẽ tiến xa hơn trong học tập, cũng như trong cuộc sống. Cha mẹ hãy giúp bé hiểu rõ thông điệp chậm và chắc là điều tốt, nhưng nhanh và đáng tin cậy sẽ tốt hơn rất nhiều.', " +
                        "'https://toplist.vn/images/800px/rua-va-tho-230179.jpg', 1), " +
                        "('Củ cải trắng', " +
                        "'Ngày xửa ngày xưa, trong một ngôi làng nhỏ, có một gia đình nọ trồng được một củ cải trắng khổng lồ. Ông lão muốn nhổ củ cải lên, nhưng dù ông kéo mạnh đến đâu, củ cải vẫn không nhúc nhích. Ông gọi bà lão đến giúp. Hai người cùng nhau kéo, nhưng củ cải vẫn nằm yên. Bà lão gọi cháu gái đến giúp. Ba người cùng nhau kéo, nhưng cũng không được. Cháu gái gọi chú chó nhỏ đến. Bốn người cùng kéo, nhưng củ cải vẫn không chịu lên. Chú chó gọi mèo, mèo lại gọi chuột. Cuối cùng, với sức mạnh của cả ông lão, bà lão, cháu gái, chó, mèo và chuột, củ cải khổng lồ đã bật lên khỏi mặt đất.\n\n" +
                        "Ý nghĩa: Câu chuyện dạy trẻ bài học về sức mạnh của sự đoàn kết. Dù lớn hay nhỏ, mỗi người đều có vai trò quan trọng trong một tập thể. Chỉ cần cùng nhau cố gắng, mọi việc khó khăn đều có thể vượt qua.', " +
                        "'https://toplist.vn/images/800px/cu-cai-trang-230181.jpg', 1);";
        db.execSQL(CreateTBL_Story);
        db.execSQL(InsertTBL_Story);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        onCreate(db);
    }
}
