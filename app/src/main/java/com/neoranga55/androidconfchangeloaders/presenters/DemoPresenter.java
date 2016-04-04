package com.neoranga55.androidconfchangeloaders.presenters;

import android.os.AsyncTask;

/**
 * Created by neoranga on 28/03/2016.
 */
public class DemoPresenter implements DemoContract.UserActions<DemoContract.ViewActions> {

    private DemoContract.ViewActions mViewActions;
    private Thread mSlowTask;

    @Override
    public void onViewAttached(DemoContract.ViewActions viewActions) {
        mViewActions = viewActions;
    }

    @Override
    public void onViewDetached() {
        mViewActions = null;
    }

    @Override
    public void onDestroyed() {
        // Nothing to clean up
    }

    @Override
    public void loadButtonPressed() {
        mViewActions.showLoading();
        mSlowTask = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    mViewActions.showContentLoaded();
                } catch (InterruptedException ignored) {
                }
            }
        });
        mSlowTask.start();
    }

    @Override
    public void cancelButtonPressed() {
        if (mSlowTask != null) {
            mSlowTask.interrupt();
        }
        mViewActions.showCancelledRequest();
    }
}
