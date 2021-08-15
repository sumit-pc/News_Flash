package com.sumit.newsapp.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sumit.newsapp.model.NewsDetails;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertUser(NewsDetails loginUser);

    @Query("SELECT * FROM news")
    List<NewsDetails> getAllNews();

    @Delete
    void delete(NewsDetails task);

}
