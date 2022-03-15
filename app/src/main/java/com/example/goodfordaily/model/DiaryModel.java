package com.example.goodfordaily.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary_table")
public class DiaryModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String date;

    //Use this to change column name in SQL table
    //@ColumnInfo(name = "priority_column")
//    private int priority;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiaryModel(String description, String name,String date) {
        this.description = description;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
