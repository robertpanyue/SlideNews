package com.yuepan.slidenews.tin;

import com.yuepan.slidenews.profile.CountryEvent;
import com.yuepan.slidenews.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {
    //hold the reference
    private TinContract.View view;

    //link the model with presenter
    private TinContract.Model model;

    public TinPresenter() {
        this.model = new TinModel();
        //link the model with presenter
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CountryEvent countryEvent) {
        if (this.view != null) {
            this.model.fetchData(countryEvent.country);
        }

    }


    @Override
    public void onViewAttached(TinContract.View view) {
        //hold the reference
        this.view = view;
        //5.7 start to fetchData
        this.model.fetchData("us");
    }

    @Override
    public void onViewDetached() {
        //clear the reference
        this.view = null;
    }

    //implement the showNewsCard, return the data to the view
    @Override
    public void showNewsCard(List<News> newsList) {
        if (this.view != null) {
            view.showNewsCard(newsList);
        }
    }

    @Override
    public void saveFavoriteNews(News news) {
        model.saveFavoriteNews(news);
    }

    @Override
    public void onError() {
        if(this.view != null){
            view.onError();
        }
    }


}
