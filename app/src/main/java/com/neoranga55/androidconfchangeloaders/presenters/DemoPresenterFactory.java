package com.neoranga55.androidconfchangeloaders.presenters;

/**
 * Created by neoranga on 04/04/2016.
 */
public class DemoPresenterFactory implements PresenterFactory<DemoPresenter>{

    @Override
    public DemoPresenter create() {
        return new DemoPresenter();
    }
}
