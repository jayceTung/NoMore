package com.joker.nomore.Interactor.InteractorImpl;

import android.content.Context;

import com.joker.nomore.Interactor.MainInteractor;
import com.joker.nomore.R;
import com.joker.nomore.base.BaseFragment;
import com.joker.nomore.bean.NavigationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joker on 2015/10/19.
 */
public class MainInteractorImpl implements MainInteractor {

    @Override
    public List<BaseFragment> getFragment() {
        return null;
    }

    @Override
    public List<NavigationEntity> getNavigation(Context context) {
        List<NavigationEntity> list = new ArrayList<>();
        String[] nameStr = context.getResources().getStringArray(R.array.navigation_list_name);
        list.add(new NavigationEntity(nameStr[0], "", R.mipmap.ic_jokes));
        list.add(new NavigationEntity(nameStr[1], "", R.mipmap.ic_news));
        list.add(new NavigationEntity(nameStr[2], "", R.mipmap.ic_articles));
        list.add(new NavigationEntity(nameStr[3], "", R.mipmap.ic_weather));
        return list;
    }
}
