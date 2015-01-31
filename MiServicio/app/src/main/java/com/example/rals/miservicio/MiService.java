package com.example.rals.miservicio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MiService extends Service {

    private Timer timer;
    private final IBinder binder = new MiBinder();
    private ArrayList<String> lista;
    String[] array = {"blanco", "azul", "verde"};
    private int pos;

    private static final long UODATE_INTERVAL = 5000;

    public MiService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        pos = 0;
        timer = new Timer();

        lista = new ArrayList<>();
        pollForUpdates();

    }

    private void pollForUpdates(){

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (lista.size() >= array.length){
                    lista.remove(0);
                }
                lista.add(array[pos]);
                pos++;
                if (pos >= array.length){
                    pos = 0;
                }
            }
        }, 0, UODATE_INTERVAL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MiBinder extends Binder{
        MiService getService(){
            return MiService.this;
        }
    }
}
