package com.example.malibupharmacy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "MalibuPharmacy.db";

    public DBHelper(Context context) {
        super(context, "MalibuPharmacy.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users (\n" + "  `id` int(10) primary key NOT NULL,\n" + "  `fname` text NOT NULL,\n" + "  `lname` text NOT NULL,\n" + "  `email` varchar(100) NOT NULL,\n" + "  `phone` varchar(15) NOT NULL,\n" + "  `level` varchar(15) NOT NULL,\n" + "  `password` varchar(100) NOT NULL\n" + ")");
        MyDB.execSQL("CREATE TABLE allergies (\n" +
                "  `id` integer primary key autoincrement NOT NULL,\n" +
                "  `description` varchar(100) NOT NULL,\n" +
                "  `user_id` varchar(10) NOT NULL,\n" +
                "  `date_added` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE prescriptions (\n" + "  `id` int(11) primary key NOT NULL,\n" + "  `image` int(11) NOT NULL,\n" + "  `text` int(11) NOT NULL,\n" + "  `date` varchar(15) NOT NULL\n" + ") ");
        MyDB.execSQL("CREATE TABLE test (\n" + "`id`  integer primary key autoincrement not null,\n" + "  `title` varchar(100) NOT NULL,\n" + "  `place` varchar(100) NOT NULL,\n" + "  `result` varchar(100),\n" + "  `image` varchar(100) ,\n" + "  `date` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE delivery (\n" +
                "  `id` integer primary key autoincrement not null,\n" +
                "  `location` varchar(100) NOT NULL, \n" +
                " `street` varchar(100) NOT NULL, \n" +
                " `additional_info` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE insurance_details (\n" +
                "  `id` integer primary key autoincrement not null,\n" +
                "  `principal_name` varchar(100) NOT NULL, \n" +
                " `member_no` varchar(100) NOT NULL, \n" +
                " `policy_no` varchar(100) NOT NULL, \n" +
                " `employer_no` varchar(100) NOT NULL, \n" +
                " `dependant` varchar(100) NOT NULL, \n" +
                " `insurance` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE underlying_conditions (\n" +
                "  `id` integer primary key autoincrement not null,\n" +
                "  `description` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE additional_information (\n" +
                "  `id` integer primary key autoincrement not null,\n" +
                "  `description` varchar(100) NOT NULL)");

        MyDB.execSQL("CREATE TABLE ocr_information (\n" +
                "  `id` integer primary key autoincrement not null,\n" +
                "  `description` varchar(100) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists allergies");
        MyDB.execSQL("drop Table if exists test");
        MyDB.execSQL("drop Table if exists prescriptions");
        MyDB.execSQL("drop Table if exists delivery");
        MyDB.execSQL("drop Table if exists insurance_details");
        MyDB.execSQL("drop Table if exists underlying_conditions");
        MyDB.execSQL("drop Table if exists additional_information");
        MyDB.execSQL("drop Table if exists ocr_information");

        onCreate(MyDB);
    }

    public Boolean saveUser(Integer id, String fname, String lname, String email, String phoneno, String pass) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();

            contentValues.put("id", id);
            contentValues.put("fname", fname);
            contentValues.put("lname", lname);
            contentValues.put("email", email);
            contentValues.put("phone", phoneno);
            contentValues.put("level", "customer");
            contentValues.put("password", pass);

            MyDB.insertOrThrow("users", null, contentValues);
            MyDB.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            MyDB.endTransaction();
        }
    }

    public Boolean checkUserData(String phoneno) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users WHERE phone = ?", new String[]{phoneno});
        try {
            if (cursor.getCount() > 0) return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (cursor != null) cursor.close();
        }
        return false;
    }

    public Integer getNumberOfClients(String phoneNo) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users", null);
        int numberOfClients = 0;

        try {
            numberOfClients = cursor.getCount();
            return numberOfClients;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return numberOfClients;
    }

    public Boolean confirmUserCredentials(String phoneNo, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users WHERE (phone = ? OR email= ? )  AND password=?", new String[]{phoneNo, password});

        try {
            if (cursor.getCount() > 0) return true;
        } catch (Exception e) {
            return false;
        } finally {
            cursor.close();
        }
        return false;
    }

    //for allergies
    public Integer getNumberOfAllergies() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from allergies", null);
        int numOfAllergies = 0;
        try {
            numOfAllergies = cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return numOfAllergies;
    }

    public Boolean saveAllergies(String description) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        MyDB.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("description", description);
            contentValues.put("user_id", "3");
            contentValues.put("date_added", "12-2-90");

            MyDB.insertOrThrow("allergies", null, contentValues);
            MyDB.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            MyDB.endTransaction();
        }
    }


    public String getAllergies() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from allergies", null);
        ArrayList<String> allergies = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    allergies.add(cursor.getString(cursor.getColumnIndex("description")));
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return String.join(", ", allergies);
    }

    //Tests
    public Boolean saveTest(String testTitle, String testDate, String testLocation) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", testTitle);
            contentValues.put("place", testLocation);
            contentValues.put("date", testDate);

            MyDB.insertOrThrow("test", null, contentValues);
            MyDB.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            MyDB.endTransaction();
        }
    }

    public HashMap<String , String> getTests() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from test", null);
        HashMap<String, String> tests = new HashMap<>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    tests.put("title" , cursor.getString(cursor.getColumnIndex("title")));
                    tests.put("place" , cursor.getString(cursor.getColumnIndex("place")));
                    tests.put("date" , cursor.getString(cursor.getColumnIndex("date")));
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return tests;
    }


    public boolean saveDeliveryInfo(String location, String street, String additionalInfo) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();

            values.put("location", location);
            values.put("street", street);
            values.put("additional_info", additionalInfo);

            db.insertOrThrow("delivery", null, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
        }
    }

    public HashMap<String , String> getDeliveryInfo() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from delivery", null);
        HashMap<String, String> deliveryInfo = new HashMap<>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    deliveryInfo.put("location" , cursor.getString(cursor.getColumnIndex("location")));
                    deliveryInfo.put("street" , cursor.getString(cursor.getColumnIndex("street")));
                    deliveryInfo.put("additional_info" , cursor.getString(cursor.getColumnIndex("additional_info")));
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return deliveryInfo;
    }


    public boolean saveInsuranceDetails(String principalName, String policyNo, String memberNo, String employerNo, String dependant, String insurance) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

            values.put("principal_name", principalName);
            values.put("policy_no", policyNo);
            values.put("member_no", memberNo);
            values.put("employer_no", employerNo);
            values.put("dependant", dependant);
            values.put("insurance", insurance);

            db.insertOrThrow("insurance_details", null, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
        }
    }

    public HashMap<String, String> getInsuranceInfo() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM insurance_details", null);

        HashMap<String, String> insuranceInfo = new HashMap<>();

        try {
            if (cursor.moveToFirst()) {
                insuranceInfo.put("principal_name", cursor.getString(cursor.getColumnIndex("principal_name")));
                insuranceInfo.put("policy_no", cursor.getString(cursor.getColumnIndex("policy_no")));
                insuranceInfo.put("member_no", cursor.getString(cursor.getColumnIndex("member_no")));
                insuranceInfo.put("employer_no", cursor.getString(cursor.getColumnIndex("employer_no")));
                insuranceInfo.put("dependant", cursor.getString(cursor.getColumnIndex("dependant")));
                insuranceInfo.put("insurance", cursor.getString(cursor.getColumnIndex("insurance")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return insuranceInfo;
    }

    public boolean saveUnderlyingCondition(String description) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("description", description);

            db.insertOrThrow("underlying_conditions", null, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
        }
    }

    public String getUnderlyingConditions() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM underlying_conditions", null);

        ArrayList <String> underlyingConditions = new ArrayList<>();

        try {
            if (cursor.moveToFirst()) {
               do{
                   underlyingConditions.add(cursor.getString(cursor.getColumnIndex("description")));
               }while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return String.join(",", underlyingConditions);
    }

    public boolean saveAdditionalInformation(String description) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("description", description);

            db.insertOrThrow("additional_information", null, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
        }
    }

    public String getAdditionalInformation() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM additional_information", null);

        ArrayList <String> additionalInformation = new ArrayList<>();

        try {
            if (cursor.moveToFirst()) {
                do{
                    additionalInformation.add(cursor.getString(cursor.getColumnIndex("description")));
                }while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return String.join(",", additionalInformation);
    }

    public boolean saveOcrInformation(String description) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("description", description);

            db.insertOrThrow("ocr_information", null, values);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
        }
    }

    public String getOcrInformation() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ocr_information", null);

        ArrayList <String> ocrInformation = new ArrayList<>();

        try {
            if (cursor.moveToFirst()) {
                do{
                    ocrInformation.add(cursor.getString(cursor.getColumnIndex("description")));
                }while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return String.join(",", ocrInformation);
    }
}

