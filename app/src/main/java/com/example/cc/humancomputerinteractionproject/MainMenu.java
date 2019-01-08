package com.example.cc.humancomputerinteractionproject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainMenu extends AppCompatActivity {

    private boolean tvOn = false;

    private int brightness_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final FrameLayout screen_off = findViewById(R.id.screen_off);

        final FrameLayout main_menu = findViewById(R.id.main_menu);

        final FrameLayout tv_menu = findViewById(R.id.tv_menu);
        final FrameLayout tv_favs_menu = findViewById(R.id.tv_favs_menu);
        final FrameLayout tv_all_menu = findViewById(R.id.tv_all_menu);

        final FrameLayout radio_menu = findViewById(R.id.radio_menu);
        final FrameLayout radio_favs_menu = findViewById(R.id.radio_favs_menu);
        final FrameLayout radio_all_menu = findViewById(R.id.radio_all_menu);

        final FrameLayout video_menu = findViewById(R.id.video_menu);

        final FrameLayout help_menu = findViewById(R.id.help_menu);

        final FrameLayout[] menus = {
                main_menu,
                tv_menu, tv_favs_menu, tv_all_menu,
                radio_menu, radio_favs_menu, radio_all_menu,
                video_menu,
                help_menu
        };

//        HANDLE POWER BUTTON

        final Button power_btn = findViewById(R.id.power_button);
        power_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvOn) {

//                    If TV is turned ON, then turn it OFF => hide all menus
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

                Toast.makeText(getApplicationContext(), ("TV " + (tvOn ? "On" : "Off")), Toast.LENGTH_SHORT).show();
            }
        });

        //___________________________________________________________________________________________

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

        //___________________________________________________________________________________________

        final Button tv_favs_btn = findViewById(R.id.tv_favs_button);
        tv_favs_btn.setOnClickListener(new View.OnClickListener() {
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

//        Add some favourite tv channels
        ArrayList<String> tv_favs_list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tv_favs_list.add("TV" + i);
        }

        final ListView tv_favs_lv = findViewById(R.id.tv_favs_list);
        MyCustomAdapterFavourites tv_favs_lv_adapter = new MyCustomAdapterFavourites(tv_favs_list, "Watch", getApplicationContext());

        tv_favs_lv.setAdapter(tv_favs_lv_adapter);

        //___________________________________________________________________________________________

        final Button tv_all_btn = findViewById(R.id.tv_all_button);
        tv_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                tv_all_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button tv_all_main_btn = findViewById(R.id.tv_all_main_button);
        tv_all_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_all_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button tv_all_back_btn = findViewById(R.id.tv_all_back_button);
        tv_all_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_all_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

//        Add all tv channels
        ArrayList<String> tv_all_list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            tv_all_list.add("TV" + i);
        }

        final ListView tv_all_lv = findViewById(R.id.tv_all_list);
        MyCustomAdapterAll tv_all_lv_adapter = new MyCustomAdapterAll(tv_all_list, "Watch", tv_favs_lv_adapter, getApplicationContext());

        tv_all_lv.setAdapter(tv_all_lv_adapter);

        //___________________________________________________________________________________________

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

        //___________________________________________________________________________________________

        final Button radio_fav_btn = findViewById(R.id.radio_fav_button);
        radio_fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                radio_favs_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button radio_favs_main_btn = findViewById(R.id.radio_favs_main_button);
        radio_favs_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_favs_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button radio_favs_back_btn = findViewById(R.id.radio_favs_back_button);
        radio_favs_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_favs_menu.setVisibility(View.GONE);

                radio_menu.setVisibility(View.VISIBLE);
            }
        });

//        Add some favourite radio stations
        ArrayList<String> radio_favs_list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            radio_favs_list.add("Radio" + i);
        }

        final ListView radio_favs_lv = findViewById(R.id.radio_favs_list);
        MyCustomAdapterFavourites radio_favs_lv_adapter = (new MyCustomAdapterFavourites(radio_favs_list, "Listen", getApplicationContext()));

        radio_favs_lv.setAdapter(radio_favs_lv_adapter);

        //___________________________________________________________________________________________

        final Button radio_all_btn = findViewById(R.id.radio_all_button);
        radio_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                radio_all_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button radio_all_main_btn = findViewById(R.id.radio_all_main_button);
        radio_all_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_all_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button radio_all_back_btn = findViewById(R.id.radio_all_back_button);
        radio_all_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_all_menu.setVisibility(View.GONE);

                radio_menu.setVisibility(View.VISIBLE);
            }
        });

//        Add all radio stations
        ArrayList<String> radio_all_list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            radio_all_list.add("Radio" + i);
        }

        final ListView radio_all_lv = findViewById(R.id.radio_all_list);
        MyCustomAdapterAll radio_all_lv_adapter = new MyCustomAdapterAll(radio_all_list, "Listen", radio_favs_lv_adapter, getApplicationContext());

        radio_all_lv.setAdapter(radio_all_lv_adapter);

        //___________________________________________________________________________________________

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

        //___________________________________________________________________________________________

//        HANDLE HELP MENU
        final Button help_btn = findViewById(R.id.help_button);
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Hide all menus
                for (FrameLayout menu : menus) {
                    menu.setVisibility(View.GONE);
                }

                help_menu.setVisibility(View.VISIBLE);

                if (!tvOn) {
                    tvOn = true;
                    Toast.makeText(getApplicationContext(), "TV On", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button help_main_btn = findViewById(R.id.help_main_button);
        help_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final Button help_back_btn = findViewById(R.id.help_back_button);
        help_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help_main_btn.performClick();
            }
        });

        //___________________________________________________________________________________________

    }

//        HANDLE BRIGHTNESS POP UP
    public void ShowPopup(View view) {
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.brightness_layout);

        final SeekBar brightness_bar = myDialog.findViewById(R.id.brightness_bar);
        brightness_bar.setProgress(brightness_level);

        final Button brightness_ok_btn = myDialog.findViewById(R.id.brightness_ok_btn);
        brightness_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brightness_level = brightness_bar.getProgress();
                myDialog.dismiss();
            }
        });

        final Button brightness_cancel_btn = myDialog.findViewById(R.id.brightness_cancel_btn);
        brightness_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().setLayout(600, 250);
        myDialog.show();
    }
}
