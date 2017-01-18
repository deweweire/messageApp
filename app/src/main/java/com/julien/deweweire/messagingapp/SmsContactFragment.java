package com.julien.deweweire.messagingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.julien.deweweire.messagingapp.Model.SmsModel;
import com.julien.deweweire.messagingapp.Utils.SmsUtils;

import java.util.List;

/**
 * Created by julien on 18-01-17.
 */

public class SmsContactFragment extends android.support.v4.app.Fragment  {
    private MessageAdapter messageAdapter;
    private ListView messagesList;
    String number;
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            number = bundle.getString("number");
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.smsview, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        messagesList = (ListView) getView().findViewById(R.id.listMessages);
        messageAdapter = new MessageAdapter(this);
        messagesList.setAdapter(messageAdapter);
        populateMessageHistory();
    }
    private void populateMessageHistory(){
        List<SmsModel> sms= SmsUtils.getLastSMSForOneContact(this.getContext(),getContext().getContentResolver(),"inbox",number);
        for(int i=0;i<sms.size();i++)
        {
            int var;
            if(sms.get(i).mysms){
              var=MessageAdapter.DIRECTION_OUTGOING;
            }
            else
            {
                var= MessageAdapter.DIRECTION_INCOMING;
            }
            messageAdapter.addMessage(sms.get(i).body,var);
        }
      //  String message="coucou ca va?";

        // message="oui et toi ? ";
        //messageAdapter.addMessage(message, MessageAdapter.DIRECTION_OUTGOING);
    }


}
