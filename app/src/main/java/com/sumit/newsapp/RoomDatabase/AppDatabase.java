package com.sumit.newsapp.RoomDatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sumit.newsapp.model.NewsDetails;

@Database(entities = {NewsDetails.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
