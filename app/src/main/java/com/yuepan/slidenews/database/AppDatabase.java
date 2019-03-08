package com.yuepan.slidenews.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.yuepan.slidenews.retrofit.response.News;


@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
