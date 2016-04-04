package com.neoranga55.androidconfchangeloaders.presenters;

/**
 * Created by neoranga on 28/03/2016.
 */
public class DemoPresenter implements DemoContract.UserActions<DemoContract.ViewActions> {

    private DemoContract.ViewActions mViewActions;
    private Thread mSlowTask;
    private boolean isLoading;
    private boolean isContentLoaded;
    private boolean isLoadCancelled;

    @Override
    public void onViewAttached(DemoContract.ViewActions viewActions) {
        mViewActions = viewActions;
        if (isLoading) {
            mViewActions.showLoading();
        }
        if (isContentLoaded) {
            mViewActions.showContentLoaded();
        }
        if (isLoadCancelled) {
            mViewActions.showCancelledRequest();
        }
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
        isLoading = true;
        isContentLoaded = false;
        isLoadCancelled = false;
        mViewActions.showLoading();
        mSlowTask = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    isLoading = false;
                    isContentLoaded = true;
                    mViewActions.showContentLoaded();
                    mSlowTask = null;
                } catch (InterruptedException ignored) {
                }
            }
        });
        mSlowTask.start();
    }

    @Override
    public void cancelButtonPressed() {
        isLoading = false;
        if (mSlowTask != null) {
            isLoadCancelled = true;
            isContentLoaded = false;
            mSlowTask.interrupt();
            mSlowTask = null;
            mViewActions.showCancelledRequest();
        }
    }
}
