package com.xuwei.wangyianimator.animator;

import android.animation.ObjectAnimator;
import android.telecom.TelecomManager;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/15
 * <p>
 * 描述 :
 */
public class MyObjectAnimator implements VSYNCManager.AnimationFrameCallback {
    private static final String TAG = "tuch";
    long mStartTime = -1;
    private long mDuration = 0;
    private WeakReference<View> target; //执行动画的View
    MyFloatPropertyValuesHolder myFloatPropertyValuesHolder;
    private TimeInterpolator interpolator;
    private int index;

    public void setmDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    public MyObjectAnimator(View view, String propertyName, float... values) {
        target = new WeakReference<>(view);
        myFloatPropertyValuesHolder = new MyFloatPropertyValuesHolder(propertyName, values);
    }

    public static MyObjectAnimator ofFloat(View target, String propertyName, float... values) {
        MyObjectAnimator anim = new MyObjectAnimator(target, propertyName, values);
        return anim;
    }

    //每隔16ms进行回调
    @Override
    public boolean doAnimationFrame(long currentTime) {
        float total = mDuration / 16; //每隔16ms渲染一次，total即为渲染次数
        //当前执行百分比
        float fraction = (index++) / total;
        if (interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        if (index >= total) {
            index = 0;
        }
        myFloatPropertyValuesHolder.setAnimatedValue(target.get(), fraction);
        return false;
    }

    public void start() {
        myFloatPropertyValuesHolder.setupSetter(target);
        mStartTime = System.currentTimeMillis();
        VSYNCManager.getInstance().add(this);
    }
}
