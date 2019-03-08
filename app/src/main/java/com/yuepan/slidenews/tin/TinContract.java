package com.yuepan.slidenews.tin;

import com.yuepan.slidenews.mvp.MvpContract;
import com.yuepan.slidenews.retrofit.response.News;

import java.util.List;

public interface TinContract {


    interface View extends MvpContract.View<Presenter> {
        void showNewsCard(List<News> newsList);
        void onError();
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void showNewsCard(List<News> newsList);
        void saveFavoriteNews(News news);
        void onError();

    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData(String country);
        void saveFavoriteNews(News news);
    }
}

