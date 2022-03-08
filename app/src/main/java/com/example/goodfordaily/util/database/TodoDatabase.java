package com.example.goodfordaily.util.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.goodfordaily.model.LoginModel;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.repository.login.LoginDao;
import com.example.goodfordaily.repository.todoList.TodoDao;

//Singleton
@Database(entities = {TodoModel.class, LoginModel.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
    private static TodoDatabase instance;

    //Room will generate code for this
    public abstract TodoDao todoDao();
    public abstract LoginDao loginDao();

    //"synchronized" to prevent creation of several instances at the same time
    public static synchronized TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class, "good_for_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}