package com.julien.deweweire.messagingapp.Model;

/**
 * Created by julien on 14-01-17.
 */

public class SmsModel {
    private String address;
    public String numero;
    public String date;
    public void SmsModel(){
        this.address="";
        this.numero="";
        this.date="";

    }
    public void SmsModel(String address,String numero,String date){
        this.address=address;
        this.numero=numero;
        this.date=date;
    }

}
