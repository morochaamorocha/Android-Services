package com.example.rals.proyectogeneradordenumeros;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyReceiver extends BroadcastReceiver {

    private TextView lblNum, lblNumPrimo;

    public MyReceiver(TextView t1, TextView t2) {
        this.lblNum = t1;
        this.lblNumPrimo = t2;
        lblNum.setText(" ");
        lblNumPrimo.setText(" ");
    }

    private boolean esPrimo(int n){
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String s = intent.getExtras().get("valor_ctd").toString();
        lblNum.setText(s);
        if (esPrimo(Integer.parseInt(s))){
            lblNumPrimo.setText(s);
        }
    }
}
