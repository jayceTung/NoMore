package com.joker.nomore.view;

import com.joker.nomore.base.BaseFragment;
import com.joker.nomore.bean.NavigationEntity;

import java.util.List;

/**
 * Created by Joker on 2015/10/16.
 */
public interface MainView {
    void initializedView(List<BaseFragment> fragments, List<NavigationEntity> navigationList);
}
