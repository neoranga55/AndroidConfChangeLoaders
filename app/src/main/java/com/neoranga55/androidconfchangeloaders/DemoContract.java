package com.neoranga55.androidconfchangeloaders;

/**
 * Created by neoranga on 28/03/2016.
 */
public interface DemoContract {
    interface ViewActions {
        void showLoading();
        void showContentLoaded();
        void showCancelledRequest();
    }

    interface UserActions {
        void loadButtonPressed();
        void cancelButtonPressed();
    }
}
