package com.neoranga55.androidconfchangeloaders.presenters;

/**
 * Creates a Presenter object.
 * @param <T> presenter type
 */
public interface PresenterFactory<T extends Presenter> {
    T create();
}
