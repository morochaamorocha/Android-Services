package com.example.rals.ejemploalarma;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText txtTiempo;
    private Button btnActivar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTiempo = (EditText)findViewById(R.id.editText);

        btnActivar = (Button)findViewById(R.id.button);
        btnActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = Integer.parseInt(txtTiempo.getText().toString());
                Toast.makeText(getApplicationContext(), "Alarma fijada en " + i + " segundos", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), VibrationPhone.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);

                //Definimos la alarma
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i*1000), pendingIntent);
            }
        });
    }



}
