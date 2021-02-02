package de.aholzbaur.mycyclingrecorder.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecorderService extends Service {
    protected final int MESSAGE_START_RECORDING = 1;

    private HandlerThread handlerThread = null;
    private Looper looper = null;
    private RecorderHandler recorderHandler = null;

    private class RecorderHandler extends Handler {
        private Context context = null;

        public RecorderHandler(Looper looper, Context context) {
            super(looper);
            this.context = context;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.arg1 == MESSAGE_START_RECORDING) {
                Toast.makeText(this.context, "Service starts recording", Toast.LENGTH_SHORT).show();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this.context, "Service stopped recording", Toast.LENGTH_SHORT).show();

                stopSelf();
            }
        }
    }

    @Override
    public void onCreate() {
        this.handlerThread = new HandlerThread("RecorderThread", Process.THREAD_PRIORITY_BACKGROUND);
        this.handlerThread.start();

        this.looper = this.handlerThread.getLooper();
        this.recorderHandler = new RecorderHandler(this.looper, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = this.recorderHandler.obtainMessage();
        msg.arg1 = MESSAGE_START_RECORDING;
        this.recorderHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
