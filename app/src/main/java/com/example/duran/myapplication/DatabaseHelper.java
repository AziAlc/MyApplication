package com.example.duran.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.nfc.Tag;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static int DATABASE_VERSION =1;



    private static final String TAG = "DATAbaseHELPER";
    private static final String DB_NAME = "PAGE";

    private static final String COL1 = "NAME";

    private static final String COL2 = "PASS";


    public DatabaseHelper(Context context)
    { super (context, DB_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COL1 + " TEXT, " + COL2 + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == oldVersion +1) {
            db.execSQL("ALTER TABLE student_info ADD COLUMN country CARCHAR(30)");
            onCreate(db);
        }

    }

    public boolean insertData(String item, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, item2);



        Log.d(TAG, "addData: adding " + item +"," + item2 +" to " + DB_NAME);

        long result = db.insert(DB_NAME, null, contentValues);

        if (result == -1) { return false; }
        else { return true; }




        /*
        SQLiteStatement stmt = db.compileStatement("INSERT INTO data_info (name) " + "VALUES (?)");
        stmt.bindString(1, student.getName());
        stmt.bindString(2, student.getPass());
        stmt.execute();
        stmt.close();
        db.close();
        */
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DB_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }


}
