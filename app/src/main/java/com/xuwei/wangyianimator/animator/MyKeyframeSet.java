package com.xuwei.wangyianimator.animator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/15
 * <p>
 * 描述 :动画帧的管理类
 */
public class MyKeyframeSet {
    TypeEvaluator mEvalutor; //类型估值器
    MyFloatKeyframe mFirstKeyframe; //第一个关键帧
    List<MyFloatKeyframe> mKeyframes; //关键帧集合


    public MyKeyframeSet(MyFloatKeyframe... keyframes) {
        mKeyframes = Arrays.asList(keyframes);
        mFirstKeyframe = keyframes[0];
        mEvalutor = new FloatEvaluator();
    }

    public static MyKeyframeSet ofFloat(float[] values) {
        int numKeyframes = values.length;
        MyFloatKeyframe keyframes[] = new MyFloatKeyframe[numKeyframes];
        keyframes[0] = new MyFloatKeyframe(0, values[0]); //第0帧，表示最原始状态
        //生成关键帧
        for (int i = 1; i < numKeyframes; i++) {
            keyframes[i] = new MyFloatKeyframe((float) i / (numKeyframes - 1), values[i]);
        }
        return new MyKeyframeSet(keyframes);
    }

    public Object getValue(float fraction) {
        MyFloatKeyframe prevKeyframe = mFirstKeyframe;
        for (int i = 1; i < mKeyframes.size(); i++) {
            MyFloatKeyframe nextKeyframe = mKeyframes.get(i);
            if (fraction < nextKeyframe.getmFraction()) {
                //关键帧和关键帧之间才会执行此代码，不会跳过关键帧
                return mEvalutor.evaluate(fraction, prevKeyframe.getmValue(), nextKeyframe.getmValue());
            }
            prevKeyframe = nextKeyframe;
        }
        return null;
    }
}
