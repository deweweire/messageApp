package com.julien.deweweire.messagingapp.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

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
    public static List<String> getSMS(Context context, ContentResolver contentResolver, String uri){
        List<String> sms = new ArrayList<String>();
        Uri uriSMSURI = Uri.parse("content://sms/"+uri);
        Cursor cur =contentResolver.query(uriSMSURI, null, null, null, null);

        while (cur.moveToNext()) {
            String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndexOrThrow("body"));

            sms.add("Number: " + address + " .Message: " + body +" Date:"+  convertTimestampToString(cur.getLong(cur.getColumnIndexOrThrow("Date"))));

        }
        return sms;

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

