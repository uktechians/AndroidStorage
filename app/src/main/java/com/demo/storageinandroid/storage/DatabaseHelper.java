package com.demo.storageinandroid.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.storageinandroid.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DBName = "User.db";
    public static final  String TableName = "USerInfo";
    public static final String user_name ="user_name";
    public static final String user_age ="user_age";
    public static final String user_phone ="user_phone";
    public static final String user_address ="user_address";
    public static final String user_email ="user_email";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DBName, null ,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE "+ TableName+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+user_name+" TEXT,"+
                user_age+" TEXT,"+user_phone+" TEXT,"+user_address+" TEXT,"+user_email+" TEXT"+")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TableName);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        return result!=-1;
    }

    public boolean update(ContentValues cv, int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int result = sqLiteDatabase.update(TableName, cv, "ID = ?",new String[]{String.valueOf(id)});
        return result!=0;
    }

    public boolean delete(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int result= sqLiteDatabase.delete(TableName, "ID = ?", new String[]{String.valueOf(id)});
        return result!=0;
    }

    public List<User> fetchList(){
        List<User> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+TableName, null);
        if (cursor== null){
            return list;
        }
        else {
            while (cursor.moveToNext()){
                User user = new User();
                user.setID(cursor.getString(0));
                user.setU_name(cursor.getString(1));
                user.setU_age(cursor.getString(2));
                user.setU_phone(cursor.getString(3));
                user.setU_address(cursor.getString(4));
                user.setU_email(cursor.getString(5));
                list.add(user);
            }
            return list;
        }
    }
}
