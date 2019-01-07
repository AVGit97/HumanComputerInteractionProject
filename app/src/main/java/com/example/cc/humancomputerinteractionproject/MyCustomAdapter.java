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

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list;
    private final String button1_txt;
    private final Context context;
    private final String functionality;

    static final String PLAY = "play";
    static final String ADD = "add";

    MyCustomAdapter(ArrayList<String> list, String button1_txt, String functionality, Context context) {
        this.list = list;
        this.context = context;
        this.button1_txt = button1_txt;
        this.functionality = functionality;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        MyCustomAdapter.this.notifyDataSetChanged();
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
            view = inflater.inflate(R.layout.custom_layout, null);
        }

        //Handle TextView and display string from your list
        TextView item_txt = view.findViewById(R.id.item_txt);
        item_txt.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button item_rmv_btn = view.findViewById(R.id.item_rmv_btn);

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
                }

                MyCustomAdapter.this.notifyDataSetChanged();
            }
        });

        Button item_btn1 = view.findViewById(R.id.item_btn1);
        item_btn1.setText(button1_txt);

        View.OnClickListener onClickPlay = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout ll = (LinearLayout) v.getParent();
                RelativeLayout rl = (RelativeLayout) ll.getParent();
                TextView tv = (TextView) rl.getChildAt(0);

                String item_name = tv.getText().toString();

                Log.d("BTN_PLAY_PRESSED", item_name);

                Toast.makeText(context, ("Now " + button1_txt + "ing " + item_name + "..."), Toast.LENGTH_LONG).show();
            }
        };

        View.OnClickListener onClickAddToFavs = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Add to favourites list
                LinearLayout ll = (LinearLayout) v.getParent();
                RelativeLayout rl = (RelativeLayout) ll.getParent();
                TextView tv = (TextView) rl.getChildAt(0);

                String item_name = tv.getText().toString();

                Log.d("BTN_ADD_PRESSED", item_name);
            }
        };

        switch (functionality) {
            case "play":
                item_btn1.setOnClickListener(onClickPlay);
                break;
            case "add":
                item_btn1.setOnClickListener(onClickAddToFavs);
                break;
        }

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
