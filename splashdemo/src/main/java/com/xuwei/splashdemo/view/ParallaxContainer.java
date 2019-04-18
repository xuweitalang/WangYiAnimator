package com.xuwei.splashdemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;
import com.xuwei.splashdemo.MainActivity;
import com.xuwei.splashdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/16
 * <p>
 * 描述 :
 */
public class ParallaxContainer extends FrameLayout implements BassBoost.OnParameterChangeListener {
    private ParallaxPagerAdapter adapter;
    private ArrayList<ParallaxFragment> fragments;
    private ImageView iv_man;

    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }

    public ParallaxContainer(Context context) {
        super(context);
    }

    public ParallaxContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUp(int... childIds) {
        //根据布局文件数组，初始化所有的fragment，每个布局文件对应一个fragment
        fragments = new ArrayList<ParallaxFragment>();
        for (int i = 0; i < childIds.length; i++) {
            ParallaxFragment fragment = new ParallaxFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", childIds[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        //实例化适配器
        MainActivity activity = (MainActivity) getContext();
        //实例化ViewPager
        ViewPager vp = new ViewPager(getContext());
        vp.setId(R.id.parallax_pager);
        vp.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        adapter = new ParallaxPagerAdapter(activity.getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        addView(vp, 0); // 将ViewPager添加大自定义容器中

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //根据用户滑动的距离显示动画
            //position:索引；positionOffsetPixels：滑动的距离；positionOffset：滑动剩下的距离
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //position是接下来的位置
                //获取到进来和出去的fragment
                ParallaxFragment inFragment = null;
                ParallaxFragment outFragment = null;

                int containerWidth = getWidth(); //获取容器宽度

                //由于第0个或者最后一个没有进去或出去的fragment，所以需要try catch
                try {
                    outFragment = fragments.get(position - 1);
                } catch (Exception e) {
                }
                try {
                    inFragment = fragments.get(position);
                } catch (Exception e) {
                }

                if (outFragment != null) {
                    List<View> outViews = outFragment.getParallaxViews();
                    if (outViews != null) {
                        for (View view : outViews) {
                            //获取标签，从标签上获取所有的动画参数
                            ParallaxViewTag viewTag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                            if (viewTag == null) {
                                continue;
                            }
                            //执行动画
                            ViewHelper.setTranslationX(view, (containerWidth - positionOffsetPixels) * viewTag.xIn);
                            ViewHelper.setTranslationY(view, (containerWidth - positionOffsetPixels) * viewTag.yIn);
                        }
                    }
                }

                if (inFragment != null) {
                    List<View> inViews = inFragment.getParallaxViews();
                    if (inViews != null) {
                        for (View view : inViews) {
                            //获取标签，从标签上获取所有的动画参数
                            ParallaxViewTag viewTag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                            if (viewTag == null) {
                                continue;
                            }
                            //执行动画
                            ViewHelper.setTranslationX(view, (0 - positionOffsetPixels) * viewTag.xOut);
                            ViewHelper.setTranslationY(view, (0 - positionOffsetPixels) * viewTag.yOut);
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int i) {
                if (i == adapter.getCount() - 1) {
                    iv_man.setVisibility(INVISIBLE);
                } else {
                    iv_man.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                AnimationDrawable animation = (AnimationDrawable) iv_man.getBackground();
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        animation.start();
                        break;

                    case ViewPager.SCROLL_STATE_IDLE:
                        animation.stop();
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onParameterChange(BassBoost effect, int status, int param, short value) {

    }
}
