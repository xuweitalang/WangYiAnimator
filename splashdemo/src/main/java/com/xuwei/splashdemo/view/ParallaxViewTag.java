package com.xuwei.splashdemo.view;

/**
 * 视图动画播放时参数的控制
 */
public class ParallaxViewTag {
    protected float xIn;
    protected float xOut;
    protected float yIn;
    protected float yOut;
    protected float alphaIn; //视图进页面的透明度
    protected float alphaOut;//视图出页面的透明度


    @Override
    public String toString() {
        return "ParallaxViewTag [xIn=" + xIn + ", xOut="
                + xOut + ", yIn=" + yIn + ", yOut=" + yOut + ", alphaIn="
                + alphaIn + ", alphaOut=" + alphaOut + "]";
    }


}
