package com.julien.deweweire.messagingapp.Model;

import java.util.List;

/**
 * Created by julien on 14-01-17.
 */

public class SmsList {
    List<SmsModel> ListSms;
    public void SmsList(){

    }
    public void SmsList(List<SmsModel>list){
        this.ListSms=list;
    }
    public String getContactOptimization(List<SmsModel>list){
        return "";
    }
    public String getContactOptimization(String phoneNumber){
        return "";
    }

}
