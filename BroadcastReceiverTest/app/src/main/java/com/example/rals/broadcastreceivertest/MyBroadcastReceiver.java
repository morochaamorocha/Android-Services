package com.example.rals.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle =intent.getExtras();

        if (bundle != null){
            String estado = bundle.getString(TelephonyManager.EXTRA_STATE);
            String numero = "0";
            if (estado.equals(TelephonyManager.CALL_STATE_RINGING)){
                numero = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            }

            Log.i("ESTADO DE LA LLAMADA --->", estado);
            Log.i("NÚMERO DE LA LLAMADA --->", numero);

        }
    }
}
