package com.example.recorderplayersampleapp.base.presenter;
import com.example.recorderplayersampleapp.base.view.BaseView;

/**
 * Base Presenter
 */
public interface BasePresenter {

    /**
     * On attach.
     *
     * @param view the view
     */
    void onAttach(BaseView view);

    /**
     * On detach.
     */
    void onDetach();

    /**
     * Delete local db.
     */
    void deleteLocalDb();
}
