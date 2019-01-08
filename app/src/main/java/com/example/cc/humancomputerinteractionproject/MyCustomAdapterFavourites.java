package com.example.cc.humancomputerinteractionproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyCustomAdapterFavourites extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list;
    private final Context context;

    private String button_play_txt;

    MyCustomAdapterFavourites(ArrayList<String> list, String button_play_txt, Context context) {
        this.list = list;
        this.context = context;
        this.button_play_txt = button_play_txt;
    }

    ArrayList<String> getList() {
        return list;
    }

    void setList(ArrayList<String> list) {
        this.list = list;
        MyCustomAdapterFavourites.this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            view = inflater.inflate(R.layout.custom_layout_favourites, null);
        }

        //Handle TextView and display string from your list
        TextView item_txt = view.findViewById(R.id.favs_item_txt);
        item_txt.setText(list.get(position));

        //Handle buttons and add onClickListeners

        Button item_play_btn = view.findViewById(R.id.favs_item_play);
        item_play_btn.setText(button_play_txt);
        item_play_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout ll = (LinearLayout) v.getParent();
                RelativeLayout rl = (RelativeLayout) ll.getParent();
                TextView tv = (TextView) rl.getChildAt(0);

                String item_name = tv.getText().toString();

                Log.d("BTN_PLAY_PRESSED", item_name);

                Toast.makeText(context, ("Now " + button_play_txt.toLowerCase() + "ing " + item_name + "..."), Toast.LENGTH_LONG).show();
            }
        });

        Button item_rmv_btn = view.findViewById(R.id.favs_item_rmv_btn);
        item_rmv_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout ll = (LinearLayout) v.getParent();
                RelativeLayout rl = (RelativeLayout) ll.getParent();
                TextView tv = (TextView) rl.getChildAt(0);

                String item_name = tv.getText().toString();

                Log.d("BTN_RMV_PRESSED", item_name);

                int pos = getPosByString(item_name);
                if (pos != -1) {
                    list.remove(pos);
                    Toast.makeText(context, (item_name + " removed from favourites list"), Toast.LENGTH_LONG).show();
                }

                MyCustomAdapterFavourites.this.notifyDataSetChanged();
            }
        });

        return view;
    }

    private int getPosByString(String s) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(s)) {
                return i;
            }
        }
        return -1;
    }

}
