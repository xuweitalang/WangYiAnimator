package com.xuwei.splashdemo.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/16
 * <p>
 * 描述 :
 */
public class ParallaxFragment extends Fragment {
    //加载布局

    //此Fragment上所有需要实现差异化动画的View
    private List<View> parallaxViews = new ArrayList<>();

    public List<View> getParallaxViews() {
        return parallaxViews;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId");
        ParallaxLayoutInflater parallaxLayoutInflater = new ParallaxLayoutInflater(inflater, getActivity(), this);

        return parallaxLayoutInflater.inflate(layoutId, null);

    }

}
