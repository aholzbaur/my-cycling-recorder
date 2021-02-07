package de.aholzbaur.mycyclingrecorder.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.aholzbaur.mycyclingrecorder.R;

public class RecordingActivity extends AppCompatActivity {
    private TextView textDurationValue = null;
    private TextView textLocationLatitudeValue = null;
    private TextView textLocationLongitudeValue = null;
    private TextView textLocationAccuracyValue = null;
    private TextView textDistanceValue = null;
    private TextView textSpeedValue = null;
    private TextView textCadenceValue = null;

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
        this.textDurationValue = (TextView) this.findViewById(R.id.textDurationValue);
        this.textLocationLatitudeValue = (TextView) this.findViewById(R.id.textLocationLatitudeValue);
        this.textLocationLongitudeValue = (TextView) this.findViewById(R.id.textLocationLongitudeValue);
        this.textLocationAccuracyValue = (TextView) this.findViewById(R.id.textLocationAccuracyValue);
        this.textDistanceValue = (TextView) this.findViewById(R.id.textDistanceValue);
        this.textSpeedValue = (TextView) this.findViewById(R.id.textSpeedValue);
        this.textCadenceValue = (TextView) this.findViewById(R.id.textCadenceValue);

        this.buttonPause = (Button) this.findViewById(R.id.buttonPause);
    }

    private void setItemListeners() {
        this.buttonPause.setOnClickListener(this.buttonPauseListener);
    }

    @Override
    public void onBackPressed() {

    }
}