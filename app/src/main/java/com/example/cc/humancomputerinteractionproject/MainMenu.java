package com.example.cc.humancomputerinteractionproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenu extends AppCompatActivity {

    private boolean tvOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final FrameLayout screen_off = findViewById(R.id.screen_off);

        final FrameLayout main_menu = findViewById(R.id.main_menu);

        final FrameLayout tv_menu = findViewById(R.id.tv_menu);
        final FrameLayout tv_favs_menu = findViewById(R.id.tv_favs_menu);

        final FrameLayout radio_menu = findViewById(R.id.radio_menu);
        final FrameLayout video_menu = findViewById(R.id.video_menu);

        final FrameLayout[] menus = {main_menu, tv_menu, tv_favs_menu, radio_menu, video_menu};

//        HANDLE POWER BUTTON
        final Button power_btn = findViewById(R.id.power_button);
        power_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvOn) {

//                    if TV is turned ON, then turn it OFF => hide all menus
                    for (FrameLayout menu : menus) {
                        menu.setVisibility(View.GONE);
                    }

                    screen_off.setVisibility(View.VISIBLE);

                } else {

//                    Else turn it ON
                    screen_off.setVisibility(View.GONE);

                    main_menu.setVisibility(View.VISIBLE);

                }
                tvOn = !tvOn;
            }
        });

//        HANDLE TV MENU
        final Button tv_btn = findViewById(R.id.tv_button);
        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
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

        final Button tv_back_btn = findViewById(R.id.tv_back_button);
        tv_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_main_btn.performClick();
            }
        });

        final Button tv_fav_btn = findViewById(R.id.tv_fav_button);
        tv_fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                tv_favs_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button tv_favs_main_btn = findViewById(R.id.tv_favs_main_button);
        tv_favs_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_favs_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button tv_favs_back_btn = findViewById(R.id.tv_favs_back_button);
        tv_favs_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_favs_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

        final ListView tv_favs_lv = findViewById(R.id.tv_favs_list);

        ArrayList<String> tv_favs_list = new ArrayList<>(Arrays.asList("Channel0", "Channel1", "Channel2", "Channel3", "Channel4", "Channel5", "Channel6", "Channel7"));
        tv_favs_lv.setAdapter(new MyCustomAdapter(tv_favs_list, getApplicationContext()));

//        HANDLE RADIO MENU
        final Button radio_btn = findViewById(R.id.radio_button);
        radio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                radio_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button radio_main_btn = findViewById(R.id.radio_main_button);
        radio_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button radio_back_btn = findViewById(R.id.radio_back_button);
        radio_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_main_btn.performClick();
            }
        });

//        HANDLE VIDEO MENU
        final Button video_btn = findViewById(R.id.video_button);
        video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                video_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button video_main_btn = findViewById(R.id.video_main_button);
        video_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button video_back_btn = findViewById(R.id.video_back_button);
        video_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_main_btn.performClick();
            }
        });
    }
}
