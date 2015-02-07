package com.example.rals.alarmnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyAlarmService extends Service {
    public MyAlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Toast.makeText(getApplicationContext(), "Servicio iniciado", Toast.LENGTH_SHORT).show();
        NotificationManager manager = (NotificationManager)this.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_action_alarms)
                .setContentTitle("Notification Service")
                .setContentText("Estanotificación nos ha llegado desde un servicio")
                .setTicker("Notification Service")
                .setAutoCancel(true);

        manager.notify(0, builder.build());
        return 0;
    }
}
