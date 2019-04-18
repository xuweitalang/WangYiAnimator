package com.xuwei.wangyianimator.animator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/15
 * <p>
 * 描述 :VSYNC信号管理类(模拟VSYNC)
 */
public class VSYNCManager {
    private static final VSYNCManager outInstance = new VSYNCManager();

    public static VSYNCManager getInstance() {
        return outInstance;
    }

    private VSYNCManager() {
        new Thread(runnable).start();
    }

    private List<AnimationFrameCallback> list = new ArrayList<>();

    public void add(AnimationFrameCallback animationFrameCallback) {
        list.add(animationFrameCallback);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(16); //每隔16ms绘制一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (AnimationFrameCallback animationFrameCallback : list) {
                    animationFrameCallback.doAnimationFrame(System.currentTimeMillis());
                }
            }
        }
    };

    interface AnimationFrameCallback {
        boolean doAnimationFrame(long currentTime);
    }
}
