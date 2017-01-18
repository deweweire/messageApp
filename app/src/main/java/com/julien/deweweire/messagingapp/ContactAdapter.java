package com.julien.deweweire.messagingapp;

/**
 * Created by julien on 18-01-17.
 */

import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 18-01-17.
 */


public class ContactAdapter extends BaseAdapter {

    public static final int DIRECTION_INCOMING = 0;
    public static final int DIRECTION_OUTGOING = 1;

    private List<Pair<String, Integer>> messages;
    private LayoutInflater layoutInflater;

    public ContactAdapter(Fragment activity) {
        layoutInflater = activity.getActivity().getLayoutInflater();
        messages = new ArrayList<Pair<String, Integer>>();
    }

    public void addContact(String message, int direction) {
        messages.add(new Pair(message, direction));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int i) {
        return messages.get(i).second;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        int direction = getItemViewType(i);

        //show message on left or right, depending on if
        //it's incoming or outgoing
        if (convertView == null) {
            int res = 0;
            if (direction == DIRECTION_INCOMING) {
                res = R.layout.contact_row;
            } else if (direction == DIRECTION_OUTGOING) {
                res = R.layout.contact_row;
            }
            convertView = layoutInflater.inflate(res, viewGroup, false);
        }

        String message = messages.get(i).first;


        TextView txtMessage = (TextView) convertView.findViewById(R.id.text);
        txtMessage.setText(message);
        ImageView ImageView=(ImageView) convertView.findViewById(R.id.image);
        ImageView.setImageResource(R.drawable.button_send);

        return convertView;
    }

}