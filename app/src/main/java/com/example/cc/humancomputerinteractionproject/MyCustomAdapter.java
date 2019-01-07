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
import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list;
    private final Context context;

    MyCustomAdapter(ArrayList<String> list, Context context) {
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
                //do something
                RelativeLayout rl = (RelativeLayout) v.getParent();
                TextView tv = (TextView) rl.getChildAt(0);

                String channel_name = tv.getText().toString();

                Log.d("BTN_", channel_name);

                list.remove(getPosByString(channel_name));
                MyCustomAdapter.this.notifyDataSetChanged();
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
