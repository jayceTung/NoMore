package com.joker.nomore.presenter.presenterImpl;

import android.content.Context;

import com.joker.nomore.Interactor.InteractorImpl.MainInteractorImpl;
import com.joker.nomore.Interactor.MainInteractor;
import com.joker.nomore.presenter.Ipresenter;
import com.joker.nomore.view.MainView;

/**
 * Created by Joker on 2015/10/16.
 */
public class MainPresenter implements Ipresenter {
    private Context mContext;
    private MainView mMainView;
    private MainInteractor mainInteractor;

    public MainPresenter(Context mContext, MainView mMainView) {
        if (mMainView == null) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        this.mContext = mContext;
        this.mMainView = mMainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void initialized() {
        mMainView.initializedView(mainInteractor.getFragment(), mainInteractor.getNavigation(mContext));
    }
}
