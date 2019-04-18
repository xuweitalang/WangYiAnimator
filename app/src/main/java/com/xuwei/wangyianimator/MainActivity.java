package com.xuwei.wangyianimator;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xuwei.wangyianimator.animator.LineInterpolator;
import com.xuwei.wangyianimator.animator.MyObjectAnimator;

public class MainActivity extends AppCompatActivity {

    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);

    }

    public void scale(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button, "scaleX", 1f, 2f);

        MyObjectAnimator myObjectAnimator = new MyObjectAnimator(button, "scaleX", 1f, 2f);
        myObjectAnimator.setmDuration(3000);
        myObjectAnimator.setInterpolator(new LineInterpolator());
        myObjectAnimator.start();
    }
}
