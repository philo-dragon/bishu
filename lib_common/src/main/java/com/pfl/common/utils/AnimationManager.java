package com.pfl.common.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by Administrator on 2018\6\1 0001.
 */

public class AnimationManager {

    /**
     * 旋转动画
     *
     * @param view
     */
    public static void rotateAnim(View view,int duration) {
        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(duration);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
        view.setAnimation(rotate);
    }

    /**
     * 取消动画
     *
     * @param view
     */
    public static void cancelAnim(View view) {
        Animation animation = view.getAnimation();
        if (animation != null) {
            view.clearAnimation();
        }
    }

}
