package de.aholzbaur.mycyclingrecorder.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.aholzbaur.mycyclingrecorder.R;
import de.aholzbaur.mycyclingrecorder.data.Settings;

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

    private Settings settings = Settings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_recording);

        this.getItems();

        this.initItems();

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

    private void initItems() {
        this.textDurationValue.setVisibility(View.VISIBLE);

        if (this.settings.isEnableLocation() == true) {
            this.textLocationLongitudeValue.setVisibility(View.VISIBLE);
            this.textLocationLatitudeValue.setVisibility(View.VISIBLE);
            this.textLocationAccuracyValue.setVisibility(View.VISIBLE);
        } else {
            this.textLocationLongitudeValue.setVisibility(View.INVISIBLE);
            this.textLocationLatitudeValue.setVisibility(View.INVISIBLE);
            this.textLocationAccuracyValue.setVisibility(View.INVISIBLE);
        }

        if (this.settings.isEnableDistance() == true) {
            this.textDistanceValue.setVisibility(View.VISIBLE);
        } else {
            this.textDistanceValue.setVisibility(View.INVISIBLE);
        }

        if (this.settings.isEnableSpeed() == true) {
            this.textSpeedValue.setVisibility(View.VISIBLE);
        } else {
            this.textSpeedValue.setVisibility(View.INVISIBLE);
        }

        if (this.settings.isEnableCadence() == true) {
            this.textCadenceValue.setVisibility(View.VISIBLE);
        } else {
            this.textCadenceValue.setVisibility(View.INVISIBLE);
        }
    }

    private void setItemListeners() {
        this.buttonPause.setOnClickListener(this.buttonPauseListener);
    }

    @Override
    public void onBackPressed() {

    }
}