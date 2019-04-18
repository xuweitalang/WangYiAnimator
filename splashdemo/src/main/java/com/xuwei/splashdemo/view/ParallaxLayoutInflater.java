package com.xuwei.splashdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.xuwei.splashdemo.R;

import java.lang.reflect.Constructor;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/16
 * <p>
 * 描述 :布局填充器
 */
public class ParallaxLayoutInflater extends LayoutInflater {
    private static final String TAG = "ParallaxLayoutInflater";
    private ParallaxFragment fragment;

    protected ParallaxLayoutInflater(Context context) {
        super(context);
    }

    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext, ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new ParallaxFactory(this));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this, newContext, fragment);
    }

    class ParallaxFactory implements Factory2 {
        private final String[] sClassPrefix = {"android.widget.", "android.view."}; //系统View所存在的包
        private int[] attrIds = {R.attr.a_in, R.attr.a_out, R.attr.x_in, R.attr.x_out, R.attr.y_in, R.attr.y_out};
        private LayoutInflater inflater;

        public ParallaxFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attributeSet) {
            Log.e(TAG, "onCreateView" + name); //name为布局中的控件名称
            View view;

            view = createMyView(name, context, attributeSet);
            if (view != null) {
                TypedArray a = context.obtainStyledAttributes(attributeSet, attrIds);
                if (a != null && a.length() > 0) {
                    //获取自定义属性的值
                    ParallaxViewTag tag = new ParallaxViewTag();
                    tag.alphaIn = a.getFloat(0, 0f);
                    tag.alphaOut = a.getFloat(1, 0f);
                    tag.xIn = a.getFloat(2, 0f);
                    tag.xOut = a.getFloat(3, 0f);
                    tag.yIn = a.getFloat(4, 0f);
                    tag.yOut = a.getFloat(5, 0f);
                    view.setTag(R.id.parallax_view_tag, tag);
                }
                fragment.getParallaxViews().add(view);
                a.recycle();
            }

            return view;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return null;
        }

        //通过反射创建View
        private View createMyView(String name, Context context, AttributeSet attributeSet) {

            if (name.contains(".")) {
                return reflectView(name, null, context, attributeSet);
            } else {
                for (String prefix : sClassPrefix) {
                    View view = reflectView(name, prefix, context, attributeSet);
//                获取系统空间的自定义属性

                    if (view != null) {
                        return view;
                    }
                }
            }
            return null;
        }

        private View reflectView(String name, String prefix, Context context,
                                 AttributeSet attrs) {
            try {
                //通过统的inflater创建视图，读取系统的属性
                return inflater.createView(name, prefix, attrs);
            } catch (Exception e) {
                return null;
            }
        }

    }
}
