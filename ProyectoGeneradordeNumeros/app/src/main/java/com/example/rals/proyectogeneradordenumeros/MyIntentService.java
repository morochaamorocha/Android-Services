package com.example.rals.proyectogeneradordenumeros;

import android.app.IntentService;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 *
 */
public class MyIntentService extends IntentService {

    public static final String ACTION_RECEIVE = "com.example.rals.proyectogeneradordenumeros.action.FOO";
    private int contador = 0;
    private boolean mifinal;


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        contador = Integer.parseInt(intent.getExtras().get("msg_ini_contador").toString());
        mifinal = false;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        while (!mifinal){
            Intent intent1 = new Intent(ACTION_RECEIVE);
            intent1.putExtra("valor_ctd", contador);
            sendBroadcast(intent1);
            contador++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        mifinal=false;
        super.onDestroy();
    }
}
