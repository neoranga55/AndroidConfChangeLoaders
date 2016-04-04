package com.neoranga55.androidconfchangeloaders;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.neoranga55.androidconfchangeloaders.presenters.DemoContract;
import com.neoranga55.androidconfchangeloaders.presenters.DemoPresenter;
import com.neoranga55.androidconfchangeloaders.presenters.DemoPresenterFactory;
import com.neoranga55.androidconfchangeloaders.presenters.Presenter;
import com.neoranga55.androidconfchangeloaders.presenters.PresenterLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DemoContract.ViewActions {

    private static final String TAG = "MainActivity";
    private static final int LOADER_ID = 101;
    @Bind(R.id.tv_content) TextView mContentText;
    @Bind(R.id.tv_loading) TextView mLoadingText;
    @Bind(R.id.tv_request_cancelled) TextView mRequestCancelledText;
    private DemoContract.UserActions<DemoContract.ViewActions> mUserActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // LoaderCallbacks as an object, so no hint regarding Loader will be leak to the subclasses.
        getSupportLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<DemoPresenter>() {
            @Override
            public final Loader<DemoPresenter> onCreateLoader(int id, Bundle args) {
                Log.i(TAG, "onCreateLoader");
                // Create a loader that will call the DemoPresenter factory or return an existing
                // presenter when the activity's onStart() event is triggered
                return new PresenterLoader<>(MainActivity.this, new DemoPresenterFactory(), TAG);
            }

            @Override
            public final void onLoadFinished(Loader<DemoPresenter> loader, DemoPresenter presenter) {
                Log.i(TAG, "onLoadFinished");
                MainActivity.this.mUserActions = presenter;
//                onPresenterPrepared(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<DemoPresenter> loader) {
                Log.i(TAG, "onLoaderReset");
                MainActivity.this.mUserActions = null;
//                onPresenterDestroyed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart(); // This will call the Loader and synchronously execute onStartLoading()->onLoadFinished()
        // Also if it´s the first time the onCreateLoader() will be called before onStartLoading()
        Log.i(TAG, "onStart-" + TAG);
        mUserActions.onViewAttached(this); // Ready to use presenter
    }

    @Override
    protected void onStop() {
        mUserActions.onViewDetached();
        super.onStop();
        Log.i(TAG, "onStop-" + TAG);
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
