package com.julien.deweweire.messagingapp.Model;

/**
 * Created by julien on 14-01-17.
 */

public class SmsModel {
    public String body;
    public String numero;
    public String date;
    public String bitmapURI;
    public boolean mysms=false;



    public void SmsModel(){
        this.body ="";
        this.numero="";
        this.date="";
        this.bitmapURI="";
        this.mysms=false;

    }
    public  SmsModel(String body,String numero,String date,String bitmapURI,boolean mysms){
        this.body =body;
        this.numero=numero;
        this.date=date;
        this.bitmapURI=bitmapURI;
        this.mysms=mysms;
    }

}
