package com.neoranga55.androidconfchangeloaders.presenters;

/**
 * Created by neoranga on 28/03/2016.
 */
public interface DemoContract {
    interface ViewActions {
        void showLoading();
        void showContentLoaded();
        void showCancelledRequest();
    }

    interface UserActions<ViewActions> extends Presenter<ViewActions> {
        void loadButtonPressed();
        void cancelButtonPressed();
    }
}
