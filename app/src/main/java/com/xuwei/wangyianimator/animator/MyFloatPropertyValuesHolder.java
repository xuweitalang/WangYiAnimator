package com.xuwei.wangyianimator.animator;

import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/15
 * <p>
 * 描述 :
 */
public class MyFloatPropertyValuesHolder {
    //属性名
    String mPropertyName;
    //设置动画值的类型：可能是float、int ...
    Class mValueType;
    MyKeyframeSet mKeyframes;
    Method mSetter = null;

    public MyFloatPropertyValuesHolder(String propertyName, float... values) {
        this.mPropertyName = propertyName;
        mValueType = float.class;
        mKeyframes = MyKeyframeSet.ofFloat(values);
    }

    public void setupSetter(WeakReference<View> target) {
        char firstLetter = Character.toUpperCase(mPropertyName.charAt(0)); //将属性名的第一个单词设置成大写
        String theRest = mPropertyName.substring(1); //获取除了属性名第一个单词的其他字符串
        String methodName = "set" + firstLetter + theRest; //获取方法名
        try {
            mSetter = View.class.getMethod(methodName, float.class); //利用反射
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setAnimatedValue(View view, float fraction) {
        Object value = mKeyframes.getValue(fraction);
        try {
            mSetter.invoke(view, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
