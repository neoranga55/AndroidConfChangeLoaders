package com.neoranga55.androidconfchangeloaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.neoranga55.androidconfchangeloaders.presenters.DemoContract;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DemoContract.ViewActions {

    @Bind(R.id.tv_content) TextView mContentText;
    @Bind(R.id.tv_loading) TextView mLoadingText;
    @Bind(R.id.tv_request_cancelled) TextView mRequestCancelledText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void showLoading() {
        mContentText.setVisibility(View.GONE);
        mRequestCancelledText.setVisibility(View.GONE);
        mLoadingText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentLoaded() {
        mLoadingText.setVisibility(View.GONE);
        mRequestCancelledText.setVisibility(View.GONE);
        mContentText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCancelledRequest() {
        mContentText.setVisibility(View.GONE);
        mLoadingText.setVisibility(View.GONE);
        mRequestCancelledText.setVisibility(View.VISIBLE);
    }
}
