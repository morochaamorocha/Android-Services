package com.example.rals.intentservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button btnEjecutar;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar)findViewById(R.id.progressBar);
        bar.setMax(100);

        btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        btnEjecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiIntentService.startActionProgreso(getApplicationContext(), 10);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(MiIntentService.ACTION_PROGRESO);
        filter.addAction(MiIntentService.ACTION_FIN);
        ProgressReceiver rcv = new ProgressReceiver();
        registerReceiver(rcv, filter);
    }


   public class ProgressReceiver extends BroadcastReceiver{

       @Override
       public void onReceive(Context context, Intent intent) {
           if (intent != null){
               if (intent.getAction().equals(MiIntentService.ACTION_PROGRESO)){
                   int prog = intent.getIntExtra(MiIntentService.EXTRA_PARAM2, 0);
                   bar.setProgress(prog);
               }else if (intent.getAction().equals(MiIntentService.ACTION_FIN)){
                   Toast.makeText(context, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
               }
           }
       }
   }
}
