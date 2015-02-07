package com.example.rals.proyectogeneradordenumeros;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{

    private TextView lblNum, lblNumPrimo;
    private Button btnStartService, btnStopService;
    private  MyReceiver myReceiver;
    private int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblNum = (TextView)findViewById(R.id.lblNumero);
        lblNumPrimo = (TextView)findViewById(R.id.lblNumPrimo);
        myReceiver = new MyReceiver(lblNum, lblNumPrimo);

        btnStartService = (Button)findViewById(R.id.btnStartService);
        btnStartService.setOnClickListener(this);

        btnStopService = (Button)findViewById(R.id.btbStopService);
        btnStopService.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnStartService:
                startServ();
                break;
            case R.id.btbStopService:
                stopServ();
                break;
        }
    }

    private void startServ(){
        cnt = 0;
        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
        intent.putExtra("msg_ini_contador", cnt);
        IntentFilter filter = new IntentFilter(MyIntentService.ACTION_RECEIVE);
        this.registerReceiver(myReceiver, filter);
        startService(intent);
        Toast.makeText(getApplicationContext(),"Servicio Lanzado", Toast.LENGTH_SHORT).show();
    }

    private void stopServ(){
        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
        this.unregisterReceiver(myReceiver);
        if (isMyServiceRunning(MyIntentService.class)){
            stopService(intent);
            Toast.makeText(getApplicationContext(), "Servicio Detenido", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "El servicio ya estaba detenido", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass){
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if (serviceClass.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}
