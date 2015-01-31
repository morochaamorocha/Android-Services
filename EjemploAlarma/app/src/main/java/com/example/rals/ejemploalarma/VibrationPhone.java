package com.example.rals.ejemploalarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class VibrationPhone extends BroadcastReceiver {

    public VibrationPhone() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Vibration Mode activado porque el tiempo ha terminado", Toast.LENGTH_SHORT).show();

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
    }
}
