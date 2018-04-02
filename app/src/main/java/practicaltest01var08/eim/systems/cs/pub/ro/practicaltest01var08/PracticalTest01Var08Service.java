package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var08Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String answer = intent.getStringExtra("answer");
        processingThread = new ProcessingThread(this, answer);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
