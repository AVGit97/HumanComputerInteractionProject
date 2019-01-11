package com.example.cc.humancomputerinteractionproject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainMenu extends AppCompatActivity {

    private boolean tvOn = false;

    private int brightness_level;

    private int current_channel = 0;    // Position of the current tv channel
    private final int channel_num = 15;

    private int current_volume = 10;    // Position of the current radio station
    private final int max_volume = 50;

    private int tv_progress_i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final FrameLayout screen_off = findViewById(R.id.screen_off);

        final FrameLayout main_menu = findViewById(R.id.main_menu);

        final FrameLayout tv_menu = findViewById(R.id.tv_menu);
        final FrameLayout tv_favs_menu = findViewById(R.id.tv_favs_menu);
        final FrameLayout tv_all_menu = findViewById(R.id.tv_all_menu);
        final FrameLayout tv_sort_menu = findViewById(R.id.tv_sort_menu);
        final FrameLayout tv_search_menu = findViewById(R.id.tv_search_menu);

        final FrameLayout radio_menu = findViewById(R.id.radio_menu);
        final FrameLayout radio_favs_menu = findViewById(R.id.radio_favs_menu);
        final FrameLayout radio_all_menu = findViewById(R.id.radio_all_menu);
        final FrameLayout radio_sort_menu = findViewById(R.id.radio_sort_menu);

        final FrameLayout video_menu = findViewById(R.id.video_menu);

        final FrameLayout help_menu = findViewById(R.id.help_menu);

        final FrameLayout[] menus = {
                main_menu,
                tv_menu, tv_favs_menu, tv_all_menu, tv_sort_menu, tv_search_menu,
                radio_menu, radio_favs_menu, radio_all_menu, radio_sort_menu,
                video_menu,
                help_menu
        };

//        HANDLE POWER BUTTON

        final ImageButton power_btn = findViewById(R.id.power_button);
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

        final ImageButton tv_btn = findViewById(R.id.tv_button);
        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_main_btn = findViewById(R.id.tv_main_button);
        tv_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_back_btn = findViewById(R.id.tv_back_button);
        tv_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_main_btn.performClick();
            }
        });

        //___________________________________________________________________________________________

        final ImageButton tv_favs_btn = findViewById(R.id.tv_favs_button);
        tv_favs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                tv_favs_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_favs_main_btn = findViewById(R.id.tv_favs_main_button);
        tv_favs_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_favs_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_favs_back_btn = findViewById(R.id.tv_favs_back_button);
        tv_favs_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_favs_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

//        Add some favourite tv channels
        ArrayList<String> tv_favs_list = new ArrayList<>();
        for (int i = 0; i < channel_num / 2; i++) {
            tv_favs_list.add("TV" + i);
        }

        final ListView tv_favs_lv = findViewById(R.id.tv_favs_list);
        MyCustomAdapterFavourites tv_favs_lv_adapter = new MyCustomAdapterFavourites(tv_favs_list, "Watch", getApplicationContext());

        tv_favs_lv.setAdapter(tv_favs_lv_adapter);

        //___________________________________________________________________________________________

        final ImageButton tv_all_btn = findViewById(R.id.tv_all_button);
        tv_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                tv_all_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_all_main_btn = findViewById(R.id.tv_all_main_button);
        tv_all_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_all_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_all_back_btn = findViewById(R.id.tv_all_back_button);
        tv_all_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_all_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

//        Add all tv channels
        ArrayList<String> tv_all_list = new ArrayList<>();
        for (int i = 0; i < channel_num; i++) {
            tv_all_list.add("TV" + i);
        }

        final ListView tv_all_lv = findViewById(R.id.tv_all_list);
        MyCustomAdapterAll tv_all_lv_adapter = new MyCustomAdapterAll(tv_all_list, "Watch", tv_favs_lv_adapter, getApplicationContext());

        tv_all_lv.setAdapter(tv_all_lv_adapter);

        //___________________________________________________________________________________________

        final ImageButton tv_sort_btn = findViewById(R.id.tv_sort_button);
        tv_sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                tv_sort_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_sort_main_btn = findViewById(R.id.tv_sort_main_button);
        tv_sort_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sort_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_sort_back_btn = findViewById(R.id.tv_sort_back_button);
        tv_sort_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sort_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

        final ListView tv_sort_lv = findViewById(R.id.tv_sort_list);
        MyCustomAdapterSort tv_sort_lv_adapter = new MyCustomAdapterSort(tv_all_list, getApplicationContext());

        tv_sort_lv.setAdapter(tv_sort_lv_adapter);

        //___________________________________________________________________________________________

        final ImageButton tv_search_btn = findViewById(R.id.tv_search_button);
        tv_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_menu.setVisibility(View.GONE);

                tv_search_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_search_main_btn = findViewById(R.id.tv_search_main_button);
        tv_search_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_search_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton tv_search_back_btn = findViewById(R.id.tv_search_back_button);
        tv_search_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_search_menu.setVisibility(View.GONE);

                tv_menu.setVisibility(View.VISIBLE);
            }
        });

        final TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        String tabText;
        TabHost.TabSpec spec;

        // Tab 1
        tabText = "Βημα 1";
        spec = tabHost.newTabSpec(tabText);
        spec.setContent(R.id.tv_start_search_tab);
        spec.setIndicator(tabText);
        tabHost.addTab(spec);

        //Tab 2
        tabText = "Βημα 2";
        spec = tabHost.newTabSpec(tabText);
        spec.setContent(R.id.tv_search_tab);
        spec.setIndicator(tabText);
        tabHost.addTab(spec);

        //Tab 3
        tabText = "Βημα 3";
        spec = tabHost.newTabSpec(tabText);
        spec.setContent(R.id.tv_finish_search_tab);
        spec.setIndicator(tabText);
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildTabViewAt(0).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(2).setEnabled(false);

        final Button tv_start_search_btn = findViewById(R.id.tv_start_search_btn);
        tv_start_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabHost.setCurrentTab(1);

                final ProgressBar tv_progress_bar = findViewById(R.id.tv_progress_bar);
                final TextView tv_progress_txt = findViewById(R.id.tv_progress_txt);

                boolean fixed = false;
                if (fixed) {
                    tv_progress_i = 38;

                    tv_progress_bar.setProgress(tv_progress_i);
                    tv_progress_txt.setText(String.valueOf(tv_progress_i + "%"));
                } else {
                    tv_progress_bar.setProgress(tv_progress_i);

                    CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {
                        @Override
                        public void onTick(long l) {
                            Log.d("Log_tag", "Tick of Progress " + tv_progress_i + l);
                            tv_progress_i++;
                            tv_progress_bar.setProgress(tv_progress_i * 100/(10000/100));

                            tv_progress_txt.setText(String.valueOf(tv_progress_i + "%"));
                        }

                        @Override
                        public void onFinish() {
                            tabHost.setCurrentTab(2);
                            tv_progress_i++;
                            tv_progress_bar.setProgress(100);

                            tv_progress_i = 0;
                        }
                    };

                    countDownTimer.start();
                }
            }
        });

        final Button tv_search_cancel_btn = findViewById(R.id.tv_search_cancel_btn);
        tv_search_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO cancel search
            }
        });

        final Button tv_finish_search_btn = findViewById(R.id.tv_finish_search_btn);
        tv_finish_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabHost.setCurrentTab(0);

                tv_search_menu.setVisibility(View.GONE);

                tv_all_menu.setVisibility(View.VISIBLE);
            }
        });

        final TextView tv_finish_search_txt = findViewById(R.id.tv_finish_search_txt);
        tv_finish_search_txt.setText("Βρέθηαν " + channel_num + " κανάλια.");

        //___________________________________________________________________________________________

//        HANDLE RADIO MENU
        final ImageButton radio_btn = findViewById(R.id.radio_button);
        radio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                radio_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_main_btn = findViewById(R.id.radio_main_button);
        radio_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_back_btn = findViewById(R.id.radio_back_button);
        radio_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_main_btn.performClick();
            }
        });

        //___________________________________________________________________________________________

        final ImageButton radio_fav_btn = findViewById(R.id.radio_favs_button);
        radio_fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                radio_favs_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_favs_main_btn = findViewById(R.id.radio_favs_main_button);
        radio_favs_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_favs_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_favs_back_btn = findViewById(R.id.radio_favs_back_button);
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

        final ImageButton radio_all_btn = findViewById(R.id.radio_all_button);
        radio_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                radio_all_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_all_main_btn = findViewById(R.id.radio_all_main_button);
        radio_all_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_all_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_all_back_btn = findViewById(R.id.radio_all_back_button);
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

        final ImageButton radio_sort_btn = findViewById(R.id.radio_sort_button);
        radio_sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_menu.setVisibility(View.GONE);

                radio_sort_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_sort_main_btn = findViewById(R.id.radio_sort_main_button);
        radio_sort_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_sort_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_sort_back_btn = findViewById(R.id.radio_sort_back_button);
        radio_sort_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_sort_menu.setVisibility(View.GONE);

                radio_menu.setVisibility(View.VISIBLE);
            }
        });

        final ListView radio_sort_lv = findViewById(R.id.radio_sort_list);
        MyCustomAdapterSort radio_sort_lv_adapter = new MyCustomAdapterSort(radio_all_list, getApplicationContext());

        radio_sort_lv.setAdapter(radio_sort_lv_adapter);

        //___________________________________________________________________________________________

//        HANDLE VIDEO MENU
        final ImageButton video_btn = findViewById(R.id.video_button);
        video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_menu.setVisibility(View.GONE);

                video_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton video_main_btn = findViewById(R.id.video_main_button);
        video_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton video_back_btn = findViewById(R.id.video_back_button);
        video_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_main_btn.performClick();
            }
        });

        //___________________________________________________________________________________________

//        HANDLE HELP MENU
        final ImageButton help_btn = findViewById(R.id.help_button);
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

        final ImageButton help_main_btn = findViewById(R.id.help_main_button);
        help_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help_menu.setVisibility(View.GONE);

                main_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton help_back_btn = findViewById(R.id.help_back_button);
        help_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help_main_btn.performClick();
            }
        });

        //___________________________________________________________________________________________

        final ImageButton channel_loss_btn = findViewById(R.id.channel_loss_button);
        channel_loss_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help_menu.setVisibility(View.GONE);

                tv_search_menu.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton radio_loss_btn = findViewById(R.id.radio_loss_button);
        radio_loss_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect user to radio_search_menu
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

//        HANDLE VOLUME BUTTONS
    public void increaseVolume(View view) {
        if (current_volume < max_volume) {
            current_volume++;
        }
        displayVolume();
    }

    public void decreaseVolume(View view) {
        if (current_volume > 0) {
            current_volume--;
        }
        displayVolume();
    }

    public void displayVolume() {
        Toast.makeText(getApplicationContext(),"Volume set to " + current_volume, Toast.LENGTH_SHORT).show();
    }

//        HANDLE CHANNEL BUTTONS
    public void nextChannel(View view) {
        if (current_channel < channel_num) {
            current_channel++;
        } else {
            current_channel = 0;
        }
        displayChannel();
    }

    public void previousChannel(View view) {
        if (current_channel > 0) {
            current_channel--;
        } else {
            current_channel = channel_num;
        }
        displayChannel();
    }

    private void displayChannel() {
        Toast.makeText(getApplicationContext(),"Channel set to " + current_channel, Toast.LENGTH_SHORT).show();
    }

}
