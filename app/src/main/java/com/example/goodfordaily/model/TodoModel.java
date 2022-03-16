package com.example.goodfordaily.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class TodoModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String date;
    //Use this to change column name in SQL table
    //@ColumnInfo(name = "priority_column")
//    private int priority;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TodoModel(String description, String name, String date) {
        this.description = description;
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
