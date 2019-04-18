package com.xuwei.splashdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.xuwei.splashdemo.view.ParallaxContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParallaxContainer container = findViewById(R.id.parallax_container);
        container.setUp(R.layout.view_intro_1, R.layout.view_intro_2, R.layout.view_intro_3,
                R.layout.view_intro_4, R.layout.view_intro_5, R.layout.view_intro_6, R.layout.view_intro_7,
                R.layout.view_login);

        ImageView iv_man = findViewById(R.id.iv_man);
        iv_man.setBackgroundResource(R.drawable.man_run);
        container.setIv_man(iv_man);
    }
}
