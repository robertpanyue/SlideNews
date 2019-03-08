package com.yuepan.slidenews.tin;

import android.database.sqlite.SQLiteConstraintException;

import com.yuepan.slidenews.TinApplication;
import com.yuepan.slidenews.database.AppDatabase;
import com.yuepan.slidenews.retrofit.NewsRequestApi;
import com.yuepan.slidenews.retrofit.RetrofitClient;
import com.yuepan.slidenews.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {

    //keep the reference of TinContract.Presenter
    private TinContract.Presenter presenter;

    private final NewsRequestApi newsRequestApi;

    private final AppDatabase db;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        db = TinApplication.getDataBase();
    }

    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        //assign the presenter
        this.presenter = presenter;
    }

    @Override
    public void fetchData(String country) {
        //make the request in the Model
        newsRequestApi.getNewsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    // pass the fetch data to the model
                    presenter.showNewsCard(baseResponse.articles);
                });
    }


    @Override
    public void saveFavoriteNews(News news) {
        Disposable disposable = Completable.fromAction(() -> db.newsDao().insertNews(news)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{
        }, error -> {
                    if (error instanceof SQLiteConstraintException) {
                        presenter.onError();
                    }
        });
    }
}

