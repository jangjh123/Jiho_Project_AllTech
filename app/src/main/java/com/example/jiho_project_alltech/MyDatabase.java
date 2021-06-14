package com.example.jiho_project_alltech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DB.db";
    public static final String TABLE_NAME = "myTable";
    public static final String ID = "id";
    public static final String CONTENT = "content";

    public MyDatabase (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + "" + CONTENT + " TEXT )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + DATABASE_NAME);
        onCreate(db);
    }
    public void close () {
        if (this.sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }
    public boolean InsertMemo (Memo memo) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTENT, memo.getContent());
        long re = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return re != -1;
    }

    public ArrayList<Memo> RestoreAllMemo() {
        ArrayList<Memo> myMemo = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.moveToNext()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                String content = cursor.getString(cursor.getColumnIndex(CONTENT));
                Memo memo = new Memo (id, content);
                myMemo.add(memo);
            }
            while (cursor.moveToNext());
        }
        return myMemo;
    }
    public boolean deleteMemo (int itemId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME, ID + "=?" , new String[] { String.valueOf(itemId)});
        return result > 0;
    }
    public Memo getMemoById (int itemId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where " + ID + "=?", new String[] {String.valueOf(itemId)});
        if (cursor.moveToNext() && cursor != null) {
            String content = cursor.getString(cursor.getColumnIndex(CONTENT));
            Memo memo = new Memo (content);
            cursor.close();

            return memo;
        }
        return null;
    }


}
