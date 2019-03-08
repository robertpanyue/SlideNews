package com.yuepan.slidenews.save;

import com.yuepan.slidenews.retrofit.response.News;
import java.util.List;

public class SavedNewsPresenter implements SavedNewsContract.Presenter {
    private final SavedNewsContract.Model model;
    private SavedNewsContract.View view;

    public SavedNewsPresenter() {
        model = new SavedNewsModel();
        model.setPresenter(this);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(SavedNewsContract.View view) {
        this.view = view;
        if (view.isViewEmpty()) {
            this.model.fetchData();
        }
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void loadSavedNews(List<News> newsList) {
        if (view != null) {
            view.loadSavedNews(newsList);
        }
    }
}
