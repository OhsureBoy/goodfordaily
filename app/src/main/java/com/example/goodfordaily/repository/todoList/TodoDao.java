package com.example.goodfordaily.repository.todoList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.goodfordaily.model.TodoModel;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface TodoDao {

    @Insert
    Completable insert(TodoModel task);

    @Update
    Completable update(TodoModel task);

    @Delete
    Completable delete(TodoModel task);

    @Query("DELETE FROM todo_table")
    Completable deleteAllTasks();

    @Query("SELECT * FROM todo_table ORDER by priority DESC")
    LiveData<List<TodoModel>> getAllTasks();
}
