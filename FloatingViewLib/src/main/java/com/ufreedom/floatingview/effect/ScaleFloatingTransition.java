/*
 * Copyright (C) 2015 UFreedom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.ufreedom.floatingview.effect;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import com.ufreedom.floatingview.transition.YumFloating;
import com.ufreedom.floatingview.transition.FloatingTransition;
import com.ufreedom.floatingview.spring.SimpleReboundListener;
import com.ufreedom.floatingview.spring.SpringHelper;


/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class ScaleFloatingTransition implements FloatingTransition {

    private long duration;
    private double bounciness;
    private double speed;

    public ScaleFloatingTransition() {
        duration = 1000;
        bounciness = 10;
        speed = 15;
    }

    public ScaleFloatingTransition(long duration) {
        this.duration = duration;
        bounciness = 10;
        speed = 15;
    }

    public ScaleFloatingTransition(long duration, double bounciness, double speed) {
        this.duration = duration;
        this.bounciness = bounciness;
        this.speed = speed;
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        
        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0.0f);
        alphaAnimator.setDuration(duration);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });
        alphaAnimator.start();

        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,bounciness, speed)
                .reboundListener(new SimpleReboundListener(){
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);
    }
    
}
