package com.example.rals.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class MiIntentService extends IntentService {

    public static final String ACTION_PROGRESO = "com.example.rals.intentservice.action.PROGRESO";
    public static final String ACTION_FIN = "com.example.rals.intentservice.action.FIN";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.rals.intentservice.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.rals.intentservice.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    public static void startActionProgreso(Context context, int param1) {
        Intent intent = new Intent(context, MiIntentService.class);
        intent.setAction(ACTION_PROGRESO);
        intent.putExtra(EXTRA_PARAM1, param1);
        context.startService(intent);
    }



    public MiIntentService() {
        super("MiIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Servicio iniciado.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int iter = intent.getIntExtra(EXTRA_PARAM1, 0);

            for (int i = 1; i <= iter; i++){

                tareaLarga();

                Intent it = new Intent();
                it.setAction(ACTION_PROGRESO);
                it.putExtra(EXTRA_PARAM2, i*10);
                sendBroadcast(it);
            }

            Intent itFin = new Intent();
            itFin.setAction(ACTION_FIN);
            sendBroadcast(itFin);
            stopSelf();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "El servicio ha finalizado", Toast.LENGTH_SHORT).show();
    }

    private void tareaLarga(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
