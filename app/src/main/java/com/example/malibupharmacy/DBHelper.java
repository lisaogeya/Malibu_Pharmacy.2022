package com.example.malibupharmacy;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "MalibuPharmacy.db";

    public DBHelper(Context context) {
        super(context, "MalibuPharmacy.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users (\n" +
                "  `id` int(10) primary key NOT NULL,\n" +
                "  `fname` text NOT NULL,\n" +
                "  `lname` text NOT NULL,\n" +
                "  `email` varchar(100) NOT NULL,\n" +
                "  `phone` varchar(15) NOT NULL,\n" +
                "  `level` varchar(15) NOT NULL,\n" +
                "  `password` varchar(100) NOT NULL\n" +
                ")");
        MyDB.execSQL("CREATE TABLE allergies (\n" +
                "  `id` varchar(10) primary key autoincrement NOT NULL,\n" +
                "  `description` varchar(100) NOT NULL,\n" +
                "  `user_id` varchar(10) NOT NULL,\n" +
                "  `date_added` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE prescriptions (\n" +
                "  `id` int(11) primary key NOT NULL,\n" +
                "  `image` int(11) NOT NULL,\n" +
                "  `text` int(11) NOT NULL,\n" +
                "  `date` varchar(15) NOT NULL\n" +
                ") ");
        MyDB.execSQL("CREATE TABLE test (\n" +
                "  `id` int(10) NOT NULL,\n" +
                "  `title` varchar(100) NOT NULL,\n" +
                "  `place` varchar(100) NOT NULL,\n" +
                "  `result` varchar(100) NOT NULL,\n" +
                "  `image` varchar(100) NOT NULL,\n" +
                "  `date` varchar(100) NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists allergies");
        MyDB.execSQL("drop Table if exists test");
        MyDB.execSQL("drop Table if exists prescriptions");



    }
    public  Boolean saveUser(Integer id, String fname, String lname, String email, String phoneno, String pass){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("phone", phoneno);
        contentValues.put("level", "customer");
        contentValues.put("password", pass);


        long result = MyDB.insert("users",null, contentValues);
        return result != -1;
    }
    public Boolean checkuserdata(String phoneno){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users WHERE phone = ?", new String[] {phoneno});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Integer getnumberofclients(String phoneno){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phone!=?", new String[] {phoneno});
        int numOfUsers= cursor.getCount();
        return numOfUsers;

    }
    public Boolean confirmusercredentials(String phoneno, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users WHERE (phone = ? OR email= ? )  AND password=?", new String[] {phoneno,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //for allergies
    public Integer getNumberOfAllergies(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor acursor = MyDB.rawQuery("Select * from allergies", null);
        int numOfAllergies= acursor.getCount();
        return ++numOfAllergies;

    }
    public  Boolean saveAllergies(Integer id, String description) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("description", description);
        contentValues.put("user_id", "3");
        contentValues.put("date_added", "12-2-90");
        //contentValues.put("user_id", user_id);

        long result = MyDB.insert("allergies", null, contentValues);
        return result != -1;

    }
    public Cursor getAdata () {
        SQLiteDatabase MyDB = this.getWritableDatabase();
       Cursor cursor = MyDB.rawQuery("Select * from allergies",null);
       return cursor;
    }
//Tests
    public Boolean saveTest(String testTitle, String testDate,String testLocation){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", testTitle);
        contentValues.put("place", testLocation);
        contentValues.put("date", testDate);

        long result = MyDB.insert("test", null, contentValues);
        return result != -1;

    }
}
