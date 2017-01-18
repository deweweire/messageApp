package com.julien.deweweire.messagingapp.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.julien.deweweire.messagingapp.Model.SmsModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by julien on 12-01-17.
 */

public class SmsUtils {
    public SmsUtils(){

    }

    /****
     * Author:julien
     * @param context
     * @param contentResolver
     * @param uri sent => me  inbox => other
     * @return
     */
    public static List<SmsModel> getSMS(Context context, ContentResolver contentResolver, String uri){
        List<String> sms = new ArrayList<String>();
        List<SmsModel> list=new ArrayList<>();
        Uri uriSMSURI = Uri.parse("content://sms/"+uri);
        Cursor cur =contentResolver.query(uriSMSURI, null, null, null, null);
        while (cur.moveToNext()) {
            String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndexOrThrow("body"));
list.add(new SmsModel(body,address,convertTimestampToString(cur.getLong(cur.getColumnIndexOrThrow("Date"))),"",true));
            sms.add("Number: " + address + " .Message: " + body +" Date:"+  convertTimestampToString(cur.getLong(cur.getColumnIndexOrThrow("Date"))));

        }
        return list;

    }
    public static List<SmsModel> getLastSMSAllContact(Context context, ContentResolver contentResolver, String uri){
        List<String> sms = new ArrayList<String>();
        List<SmsModel> list=new ArrayList<>();
        Uri uriSMSURI = Uri.parse("content://sms/"+uri);
        Cursor cur =contentResolver.query(uriSMSURI, null, null, null, null);
        while (cur.moveToNext()) {

                int existing=0;
                for(int i=0;i<sms.size();i++){
                    if(sms.get(i).equalsIgnoreCase(cur.getString(cur.getColumnIndex("address")))){
                       existing=1;

                    }
                }
                if(existing==0)
                {
                    String address = cur.getString(cur.getColumnIndex("address"));
                    String body = cur.getString(cur.getColumnIndexOrThrow("body"));
                    int type = cur.getColumnIndex("type");
                    Boolean mysms=false;
                    if(cur.getString(type).equalsIgnoreCase("1")){
                        // sms received
                        mysms=false;
                    }
                    else if(cur.getString(type).equalsIgnoreCase("2")){
                        //sms sent
                        mysms=true;
                    }
                    list.add(new SmsModel(body,address,convertTimestampToString(cur.getLong(cur.getColumnIndexOrThrow("Date"))),"",mysms));
                    sms.add(address);
                }




        }
        return list;

    }
    public static List<SmsModel> getLastSMSForOneContact(Context context, ContentResolver contentResolver, String uri,String numero){
        List<String> sms = new ArrayList<String>();
        List<SmsModel> list=new ArrayList<>();
        Uri uriSMSURI = Uri.parse("content://sms/");
        Cursor cur =contentResolver.query(uriSMSURI, null, null, null, null);
        while (cur.moveToNext()) {

           if(numero.equalsIgnoreCase(cur.getString(cur.getColumnIndex("address")))) {
               String address = cur.getString(cur.getColumnIndex("address"));
               String body = cur.getString(cur.getColumnIndexOrThrow("body"));
               int type = cur.getColumnIndex("type");
               Boolean mysms=false;
               if(cur.getString(type).equalsIgnoreCase("1")){
                   // sms received
                   mysms=false;
               }
               else if(cur.getString(type).equalsIgnoreCase("2")){
                   //sms sent
                   mysms=true;
               }
               list.add(new SmsModel(body, address, convertTimestampToString(cur.getLong(cur.getColumnIndexOrThrow("Date"))), "",mysms));
               sms.add(address);
           }




        }
        return list;

    }

    public static String convertTimestampToString(Long Timestamp){
        Date dateObj = new Date(Timestamp);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return  df.format(dateObj);
    }
    public static String getContactName(Context context, String phoneNo) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNo));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return phoneNo;
        }
        String Name = phoneNo;
        if (cursor.moveToFirst()) {
            Name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return Name;
    }


}

