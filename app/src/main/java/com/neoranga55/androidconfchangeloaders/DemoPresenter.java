package com.neoranga55.androidconfchangeloaders;

/**
 * Created by neoranga on 28/03/2016.
 */
public class DemoPresenter implements DemoContract.UserActions {

    private DemoContract.ViewActions viewActions;

    public DemoPresenter(final DemoContract.ViewActions viewActions) {
        setViewActions(viewActions);
    }

    public void setViewActions(final DemoContract.ViewActions viewActions) {
        this.viewActions = viewActions;
    }

    @Override
    public void loadButtonPressed() {
        viewActions.showLoading();
        new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    viewActions.showContentLoaded();
                } catch (InterruptedException ignored) {
                }
            }
        }.run();
    }

    @Override
    public void cancelButtonPressed() {
        viewActions.showCancelledRequest();
    }
}
