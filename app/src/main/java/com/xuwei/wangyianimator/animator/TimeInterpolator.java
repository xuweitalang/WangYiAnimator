package com.xuwei.wangyianimator.animator;

/**
 * @author :create by ${xuwei}
 * 时间 :2019/4/15
 * <p>
 * 描述 :差值器（控制动画执行速度）
 */
public interface TimeInterpolator {
    float getInterpolation(float input);
}
