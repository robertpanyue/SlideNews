package com.yuepan.slidenews.save;

import android.annotation.SuppressLint;
import com.yuepan.slidenews.TinApplication;
import com.yuepan.slidenews.database.AppDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SavedNewsModel implements SavedNewsContract.Model {

    private SavedNewsContract.Presenter presenter;

    private final AppDatabase db;
    SavedNewsModel() {
        db = TinApplication.getDataBase();
    }


    @Override
    public void setPresenter(SavedNewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchData() {
        db.newsDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(presenter::loadSavedNews, error -> {
                    System.out.println("error");
                }, () -> {
                    System.out.println("complete");
                });
    }

}


