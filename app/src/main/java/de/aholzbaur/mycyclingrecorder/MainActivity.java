package de.aholzbaur.mycyclingrecorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

enum Status {
    STOPPED,
    PAUSED,
    RECORDING
}

public class MainActivity extends AppCompatActivity {
    TextView textBluetoothStatusValue = null;
    TextView textCurrentStatus = null;

    Button buttonStart = null;
    Button buttonPauseResume = null;
    Button buttonStop = null;

    Status currentStatus = Status.STOPPED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.initButtons();

        this.initText();
    }

    void initButtons() {
        this.buttonStart = (Button) this.findViewById(R.id.buttonStart);
        this.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        this.buttonPauseResume = (Button) this.findViewById(R.id.buttonPauseResume);
        this.buttonPauseResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseResumeRecording();
            }
        });

        this.buttonStop = (Button) this.findViewById(R.id.buttonStop);
        this.buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
    }

    void initText() {
        this.textBluetoothStatusValue = (TextView) this.findViewById(R.id.textBluetoothStatusValue);
        this.textCurrentStatus = (TextView) this.findViewById(R.id.textCurrentStatus);
    }

    void startRecording() {
        if (this.currentStatus.equals(Status.STOPPED)) {
            this.currentStatus = Status.RECORDING;
            this.textCurrentStatus.setText(R.string.current_status_recording);
        }
    }

    void pauseResumeRecording() {
        if (this.currentStatus.equals(Status.RECORDING)) {
            this.currentStatus = Status.PAUSED;
            this.textCurrentStatus.setText(R.string.current_status_paused);
            this.buttonPauseResume.setText(R.string.button_resume);
        } else if (this.currentStatus.equals(Status.PAUSED)) {
            this.currentStatus = Status.RECORDING;
            this.textCurrentStatus.setText(R.string.current_status_recording);
            this.buttonPauseResume.setText(R.string.button_pause);
        }
    }

    void stopRecording() {
        if (this.currentStatus.equals(Status.PAUSED) || this.currentStatus.equals(Status.RECORDING)) {
            this.currentStatus = Status.STOPPED;
            this.textCurrentStatus.setText(R.string.current_status_stopped);
        }
    }
}