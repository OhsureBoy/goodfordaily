package com.example.goodfordaily.util.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.goodfordaily.util.databass.Databases;

public class DbOpenHelper  {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public DbOpenHelper(Context context){
        this.mCtx= context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx,DATABASE_NAME,null,DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void Create(){
        mDBHelper.onCreate(mDB);
    }

    public void Close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String userid, String password){
        ContentValues values = new ContentValues();
        values.put(Databases.CreateUserDB.USERID, userid);
        values.put(Databases.CreateUserDB.PASSWORD, password);

        return mDB.insert(Databases.CreateUserDB._TABLENAME0, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String userid, String password){
        ContentValues values = new ContentValues();
        values.put(Databases.CreateUserDB.USERID, userid);
        values.put(Databases.CreateUserDB.PASSWORD, password);

        return mDB.update(Databases.CreateUserDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(Databases.CreateUserDB._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(Databases.CreateUserDB._TABLENAME0, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(){
        return mDB.query(Databases.CreateUserDB._TABLENAME0, null, null, null, null, null, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }


    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Databases.CreateUserDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ Databases.CreateUserDB._TABLENAME0);
            onCreate(db);
        }

    }
}
