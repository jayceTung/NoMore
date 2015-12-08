package com.joker.nomore.Interactor;

import android.content.Context;

import com.joker.nomore.base.BaseFragment;
import com.joker.nomore.bean.NavigationEntity;

import java.util.List;

/**
 * Created by Joker on 2015/10/19.
 */
public interface MainInteractor {

    List<BaseFragment> getFragment();

    List<NavigationEntity> getNavigation(Context context);
}
