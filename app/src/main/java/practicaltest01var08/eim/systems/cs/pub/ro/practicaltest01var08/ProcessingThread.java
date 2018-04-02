package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

/**
 * Created by student on 02.04.2018.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();
    private String answer;

    public ProcessingThread(Context context, String answer) {
        this.context = context;
        this.answer = answer;
    }

    @Override
    public void run() {
        Log.d("[Service]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[Service]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        int len = this.answer.length();
        int pos = random.nextInt(len);
        String hint = "";
        for (int i = 0; i < len; i++) {
            if (i == pos) {
                hint += String.valueOf(answer.charAt(pos));
            } else {
                hint += "*";
            }
        }

        intent.setAction("hint");
        intent.putExtra("message", hint);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
