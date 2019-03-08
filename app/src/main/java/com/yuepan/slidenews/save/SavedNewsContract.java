package com.yuepan.slidenews.save;

import com.yuepan.slidenews.mvp.MvpContract;
import com.yuepan.slidenews.retrofit.response.News;

import java.util.List;

//Create a new SavedNewsContract interface under save folder
public interface SavedNewsContract {

    interface View extends MvpContract.View<Presenter> {
        void loadSavedNews(List<News> newsList);
        boolean isViewEmpty();
    }

    interface Presenter extends  MvpContract.Presenter<View, Model> {
        void loadSavedNews(List<News> newsList);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData();
    }
}

