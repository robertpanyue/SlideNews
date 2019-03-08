package com.yuepan.slidenews.save.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuepan.slidenews.R;
import com.yuepan.slidenews.common.BaseViewModel;
import com.yuepan.slidenews.common.TinBasicFragment;
import com.yuepan.slidenews.common.Util;
import com.yuepan.slidenews.common.ViewModelAdapter;
import com.yuepan.slidenews.retrofit.response.News;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedNewsDetailedFragment extends TinBasicFragment {

    private static final String NEWS = "news";
    private ViewModelAdapter viewModelAdapter;
    public static SavedNewsDetailedFragment newInstance(News news) {

        Bundle args = new Bundle();
        args.putSerializable(NEWS, news);
        SavedNewsDetailedFragment fragment = new SavedNewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    //call view model
    private void loadNews(News news) {
        List<BaseViewModel> viewModels = new LinkedList<>();
        if (!Util.isStringEmpty(news.title)) {
            viewModels.add(new TitleViewModel(news.title));
        }
        if (!Util.isStringEmpty(news.author) || !Util.isStringEmpty(news.time)) {
            viewModels.add(new AuthorViewModel(news.author, news.time));
        }
        if (!Util.isStringEmpty((news.image))) {
            viewModels.add(new ImageViewModel(news.image));
        }
        if (!Util.isStringEmpty(news.description)) {
            viewModels.add(new DescriptionViewModel(news.description));
        }
        if (!Util.isStringEmpty(news.url)) {
            viewModels.add(new ReadmoreViewModel(news.url, tinFragmentManager));
        }

        viewModelAdapter.addViewModels(viewModels);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNews((News) getArguments().getSerializable(NEWS));
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
