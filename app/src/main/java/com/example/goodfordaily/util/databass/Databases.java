package com.example.goodfordaily.util.databass;
import android.provider.BaseColumns;


//https://github.com/yoondowon/InnerDatabaseSQLite/blob/master/app/src/main/java/com/example/user/innerdatabasesqlite/DbOpenHelper.java 보고 작성
public final class Databases {
    public static class CreateUserDB implements BaseColumns {

        public static final String USERID = "userId";
        public static final String PASSWORD = "password";
        public static final String _TABLENAME0 = "UserTable";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 + "("
                + _ID + " integer primary key autoincrement, "
                + USERID + " text not null , "
                + PASSWORD + " text not null );";
    }
}