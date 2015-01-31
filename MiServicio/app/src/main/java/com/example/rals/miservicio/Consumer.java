package com.example.rals.miservicio;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Consumer extends Activity {

    private Button btnActualizar;
    private ListView lista;
    private ArrayList<String> values;
    private MiService miService;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);


        lista = (ListView)findViewById(R.id.listView);
        values = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, values);
        lista.setAdapter(adapter);

        btnActualizar = (Button)findViewById(R.id.btnRefrescar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showServiceData();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Solo deja hacer el bindService en este punto, de lo contrario no se enlaza al servicio
        doBindService();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MiService.MiBinder binder = (MiService.MiBinder)service;
            miService = binder.getService();
            Toast.makeText(getApplicationContext(),"Conectado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            miService = null;
        }
    };

    private void doBindService(){
        bindService(new Intent(getApplicationContext(), MiService.class), serviceConnection, BIND_AUTO_CREATE);
    }

    private void showServiceData(){
        if (miService != null){
            values.clear();
            values.addAll(miService.getLista());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        unbindService(serviceConnection);
    }
}
