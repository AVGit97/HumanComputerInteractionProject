package com.example.cc.humancomputerinteractionproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainMenu extends AppCompatActivity {

    private boolean tvOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final FrameLayout main_menu = findViewById(R.id.main_menu);
        final FrameLayout tv_menu = findViewById(R.id.tv_menu);
        final FrameLayout screen_off = findViewById(R.id.screen_off);

        final Button power_btn = findViewById(R.id.power_button);
        power_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvOn) {

//                    if TV is turned ON, then turn it OFF
                    main_menu.setVisibility(View.GONE);
                    tv_menu.setVisibility(View.GONE);

                    screen_off.setVisibility(View.VISIBLE);

                } else {

//                    Else turn it ON
                    screen_off.setVisibility(View.GONE);

                    main_menu.setVisibility(View.VISIBLE);

                }
                tvOn = !tvOn;
            }
        });

        final Button tv_btn = findViewById(R.id.tv_button);
        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button tv_back_btn = findViewById(R.id.tv_back_button);
        tv_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button tv_main_btn = findViewById(R.id.tv_main_button);
        tv_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });
    }
}
