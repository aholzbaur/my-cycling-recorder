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
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecorderService extends Service {
    private static final String TAG = RecorderService.class.getSimpleName();

    private static final int MSG_ID_START_RECORDING = 1;
    private static final int MSG_ID_STOP_RECORDING = 2;
    private static final int MSG_ID_NEXT_RECORDING = 3;

    private HandlerThread handlerThread = null;
    private Looper looper = null;
    private RecorderHandler recorderHandler = null;

    private class RecorderHandler extends Handler {
        private Context context = null;
        private int count = 0;

        public RecorderHandler(Looper looper, Context context) {
            super(looper);
            this.context = context;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == RecorderService.MSG_ID_START_RECORDING) {
                Log.w(RecorderService.TAG, "start recording");
                this.sendEmptyMessageDelayed(RecorderService.MSG_ID_NEXT_RECORDING, 1000);
            } else if (msg.what == RecorderService.MSG_ID_STOP_RECORDING) {
                Log.w(RecorderService.TAG, "stop recording");
                stopSelf();
            } else if (msg.what == RecorderService.MSG_ID_NEXT_RECORDING) {
                Log.w(RecorderService.TAG, "next recording");
                if (this.count > 10) {
                    this.sendEmptyMessageDelayed(RecorderService.MSG_ID_STOP_RECORDING, 1000);
                } else {
                    this.count++;
                    this.sendEmptyMessageDelayed(RecorderService.MSG_ID_NEXT_RECORDING, 1000);
                }
            }
        }
    }

    @Override
    public void onCreate() {
        this.handlerThread = new HandlerThread(RecorderService.TAG, Process.THREAD_PRIORITY_BACKGROUND);
        this.handlerThread.start();

        this.looper = this.handlerThread.getLooper();
        this.recorderHandler = new RecorderHandler(this.looper, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "StartCommand", Toast.LENGTH_SHORT).show();

        this.recorderHandler.sendEmptyMessage(RecorderService.MSG_ID_START_RECORDING);

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}