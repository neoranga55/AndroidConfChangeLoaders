package com.neoranga55.androidconfchangeloaders.presenters;

/**
 * Created by neoranga on 03/04/2016.
 */
public interface Presenter <V>{
    void onViewAttached(V viewActions);
    void onViewDetached();
    void onDestroyed();
}
