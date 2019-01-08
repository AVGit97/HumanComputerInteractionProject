package com.example.cc.humancomputerinteractionproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MyCustomAdapterSort extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list;

    private final Context context;

    private String current_item;    // keeps the item that the users wants to move
    private boolean place = false;          // state of the sort button

    private String button_txt = "Change position";

    MyCustomAdapterSort(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            view = inflater.inflate(R.layout.custom_layout_sort, null);
        }

        //Handle TextView and display string from your list
        TextView item_txt = view.findViewById(R.id.sort_item_txt);
        item_txt.setText(list.get(position));

        //Handle buttons and add onClickListeners
        final Button sort_btn = view.findViewById(R.id.sort_btn);
        sort_btn.setText(button_txt);   // this will run every time notifyDataSetChanged() is called
        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rl = (RelativeLayout) v.getParent();
                TextView tv = (TextView) rl.getChildAt(0);

                String item_name = tv.getText().toString();

                if (place) {
                    Log.d("BTN_PLACE_PRESSED", item_name);

                    int i = getPosByString(item_name);
                    int j = getPosByString(current_item);

                    Log.d("PRINT_i_j", String.valueOf(i) + "_" + String.valueOf(j));
                    Collections.swap(list, i, j);   // list = pointer to all_list, we also change the all_list here

                    button_txt = "Change position";

                    MyCustomAdapterSort.this.notifyDataSetChanged();

                    place = false;

                    Toast.makeText(context, "Swapped " + current_item + " with " + item_name, Toast.LENGTH_LONG).show();
                } else {
                    Log.d("BTN_CHANGE_PRESSED", item_name);

                    current_item = item_name;

                    button_txt = "Place here";

                    MyCustomAdapterSort.this.notifyDataSetChanged();

                    place = true;

                    Toast.makeText(context, (current_item + " ready to be placed"), Toast.LENGTH_LONG).show();
                }
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
