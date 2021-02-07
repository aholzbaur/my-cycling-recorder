package de.aholzbaur.mycyclingrecorder.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import de.aholzbaur.mycyclingrecorder.R;

public class RecordingActivity extends AppCompatActivity {
    private Button buttonPause = null;
    private View.OnClickListener buttonPauseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_recording);

        this.getItems();

        this.setItemListeners();
    }

    private void getItems() {
        this.buttonPause = this.findViewById(R.id.buttonPause);
    }

    private void setItemListeners() {
        this.buttonPause.setOnClickListener(this.buttonPauseListener);
    }
}