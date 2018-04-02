package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

/**
 * Created by cristina on 4/2/2018.
 */

public class ProcessingThread extends Thread{
    private Context context;
    private boolean isRunning = true;

    private String a;
    private Random random = new Random();

    public ProcessingThread(Context context, String firstNr) {

        this.context = context;
        char let = firstNr.charAt(random.nextInt(firstNr.length()));
        String b = "";
        for (int i = 0; i < firstNr.length(); i++)
        {
            if (i == random.nextInt(firstNr.length()))
            {
                b += let;
            } else {
                b += "*";
            }
        }
        a = b;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while (isRunning) {
            Intent intent = new Intent();
            intent.setAction("a");
            intent.putExtra("message", this.a);
            context.sendBroadcast(intent);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
//            Log.e(Constants.TAG, interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
