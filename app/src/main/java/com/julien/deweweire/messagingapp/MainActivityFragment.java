package com.julien.deweweire.messagingapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.julien.deweweire.messagingapp.Utils.SmsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
ListView SmsList;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
@Override
public void onViewCreated(View view, Bundle savedInstanceState){
    initLayout();
    if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.READ_SMS)
            != PackageManager.PERMISSION_GRANTED) {
        getPermissionToReadSMS();
    } else {
        getSms();
    }

}
    private void getSms() {
        List sms=new ArrayList<String>();
        sms=SmsUtils.getSMS(this.getContext(),getContext().getContentResolver(),"inbox");

        SmsList.setAdapter(new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_list_item_1,sms));
    }

    private void initLayout() {
        SmsList=(ListView)getView().findViewById(R.id.listView);
    }
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
private static final int READ_CONTACT=2;
    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this.getActivity(), "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }
    public void getPermissionToReadContact() {
        if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this.getActivity(), "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this.getActivity(), "Read SMS permission granted", Toast.LENGTH_SHORT).show();
                getPermissionToReadContact();

            } else {
                Toast.makeText(this.getActivity(), "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else if(requestCode == READ_CONTACT){
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this.getActivity(), "Read Contact permission granted", Toast.LENGTH_SHORT).show();
                getSms();

            } else {
                Toast.makeText(this.getActivity(), "Read Contact permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
