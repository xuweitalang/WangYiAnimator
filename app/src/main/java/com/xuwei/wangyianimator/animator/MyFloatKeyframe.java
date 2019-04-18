package com.xuwei.wangyianimator.animator;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/15
 * <p>
 * 描述 :关键帧：保存着某一时刻的具体状态
 */
public class MyFloatKeyframe {
    float mFraction; //当前帧百分比（0到1范围）
    Class mValueType;
    float mValue; //关键帧的值

    public MyFloatKeyframe(float fraction, float value) {
        this.mFraction = fraction;
        this.mValue = value;
        mValueType = float.class;
    }

    public float getmFraction() {
        return mFraction;
    }

    public void setmFraction(float mFraction) {
        this.mFraction = mFraction;
    }

    public float getmValue() {
        return mValue;
    }

    public void setmValue(float mValue) {
        this.mValue = mValue;
    }
}
