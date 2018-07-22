package com.example.ashwin.khabar.Fragments;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashwin.khabar.Models.NewsItem;
import com.example.ashwin.khabar.Helpers.HelperFunctions;
import com.example.ashwin.khabar.R;

import java.util.ArrayList;


public class Technology extends Fragment
{
    private static final String JSON_URL = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=ae072e92b280480c99dc61a7d1f1c9b0";
    ArrayList<NewsItem> newsItems;
    RecyclerView recyclerView;
    View rootView;

    public Technology()
    {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        newsItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment,container,false);
        recyclerView = rootView.findViewById(R.id.top_news_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SwipeRefreshLayout refreshLayout = rootView.findViewById(R.id.refresh_id);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                HelperFunctions.loadData(rootView,JSON_URL,newsItems,recyclerView,getActivity().getApplicationContext());
                refreshLayout.setRefreshing(false);
            }
        });
        HelperFunctions.loadData(rootView,JSON_URL,newsItems,recyclerView,getActivity().getApplicationContext());
        return rootView;
    }

}
