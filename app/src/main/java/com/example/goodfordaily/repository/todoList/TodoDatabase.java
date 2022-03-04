package com.example.goodfordaily.repository.todoList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.goodfordaily.model.TodoModel;
//Singleton
@Database(entities = TodoModel.class, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
    private static TodoDatabase instance;

    //Room will generate code for this
    public abstract TodoDao todoDao();

    //"synchronized" to prevent creation of several instances at the same time
    public static synchronized TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class, "todo_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}