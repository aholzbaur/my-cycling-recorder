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
        public RecorderHandler(Looper looper, Context context) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == RecorderService.MSG_ID_START_RECORDING) {
                this.sendEmptyMessage(RecorderService.MSG_ID_NEXT_RECORDING);
            } else if (msg.what == RecorderService.MSG_ID_STOP_RECORDING) {
                this.getLooper().quitSafely();
            } else if (msg.what == RecorderService.MSG_ID_NEXT_RECORDING) {
                Log.v(RecorderService.TAG, "next recording");
                this.sendEmptyMessageDelayed(RecorderService.MSG_ID_NEXT_RECORDING, 1000);
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
        this.recorderHandler.sendEmptyMessage(RecorderService.MSG_ID_START_RECORDING);

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        this.recorderHandler.sendEmptyMessage(RecorderService.MSG_ID_STOP_RECORDING);

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}