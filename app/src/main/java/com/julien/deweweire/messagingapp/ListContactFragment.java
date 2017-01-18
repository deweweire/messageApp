package com.julien.deweweire.messagingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by julien on 18-01-17.
 */

public class ListContactFragment extends android.support.v4.app.Fragment {
    private MessageAdapter messageAdapter;
    private ListView messagesList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.listcontactview, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        messagesList = (ListView) getView().findViewById(R.id.listMessages);
        messageAdapter = new MessageAdapter(this);
        messagesList.setAdapter(messageAdapter);
        populateMessageHistory();
    }
    private void populateMessageHistory(){
        String message="coucou ca va?";
        messageAdapter.addMessage(message, MessageAdapter.DIRECTION_INCOMING);

    }
}
