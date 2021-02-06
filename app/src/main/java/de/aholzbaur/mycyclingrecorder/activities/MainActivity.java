package de.aholzbaur.mycyclingrecorder.activities;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import de.aholzbaur.mycyclingrecorder.R;

public class MainActivity extends AppCompatActivity {
    private TextView textTitleLocation = null;
    private SwitchCompat switchUseLocation = null;
    private final CompoundButton.OnCheckedChangeListener switchUseLocationListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                enableUseLocation();
            } else {
                disableUseLocation();
            }
        }
    };
    private SwitchCompat switchUseLocationSpeed = null;
    private final CompoundButton.OnCheckedChangeListener switchUseLocationSpeedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                enableUseLocationSpeed();
            } else {
                disableUseLocationSpeed();
            }
        }
    };
    private SwitchCompat switchUseLocationDistance = null;
    private final CompoundButton.OnCheckedChangeListener switchUseLocationDistanceListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                enableUseLocationDistance();
            } else {
                disableUseLocationDistance();
            }
        }
    };

    private TextView textTitleSpeed = null;
    private SwitchCompat switchUseSpeed = null;
    private final CompoundButton.OnCheckedChangeListener switchUseSpeedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                enableUseSpeed();
            } else {
                disableUseSpeed();
            }
        }
    };
    private SwitchCompat switchUseSpeedDistance = null;
    private final CompoundButton.OnCheckedChangeListener switchUseSpeedDistanceListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                enableUseSpeedDistance();
            } else {
                disableUseSpeedDistance();
            }
        }
    };

    private TextView textTitleCadence = null;
    private SwitchCompat switchUseCadence = null;
    private final CompoundButton.OnCheckedChangeListener switchUseCadenceListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                enableUseCadence();
            } else {
                disableUseCadence();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.getItems();
    }

    private void getItems() {
        this.textTitleLocation = this.findViewById(R.id.textTitleLocation);
        this.switchUseLocation = this.findViewById(R.id.switchUseLocation);
        this.switchUseLocation.setOnCheckedChangeListener(this.switchUseLocationListener);
        this.switchUseLocationSpeed = this.findViewById(R.id.switchUseLocationSpeed);
        this.switchUseLocationSpeed.setOnCheckedChangeListener(this.switchUseLocationSpeedListener);
        this.switchUseLocationDistance = this.findViewById(R.id.switchUseLocationDistance);
        this.switchUseLocationDistance.setOnCheckedChangeListener(this.switchUseLocationDistanceListener);

        this.textTitleSpeed = this.findViewById(R.id.textTitleSpeed);
        this.switchUseSpeed = this.findViewById(R.id.switchUseSpeed);
        this.switchUseSpeed.setOnCheckedChangeListener(this.switchUseSpeedListener);
        this.switchUseSpeedDistance = this.findViewById(R.id.switchUseSpeedDistance);
        this.switchUseSpeedDistance.setOnCheckedChangeListener(this.switchUseSpeedDistanceListener);

        this.textTitleCadence = this.findViewById(R.id.textTitleCadence);
        this.switchUseCadence = this.findViewById(R.id.switchUseCadence);
        this.switchUseCadence.setOnCheckedChangeListener(this.switchUseCadenceListener);
    }

    private void enableUseLocation() {
        if (this.switchUseSpeed.isChecked() == false) {
            this.switchUseLocationSpeed.setEnabled(true);
            this.switchUseLocationSpeed.setChecked(false);
        }
        if (this.switchUseSpeedDistance.isChecked() == false) {
            this.switchUseLocationDistance.setEnabled(true);
            this.switchUseLocationDistance.setChecked(false);
        }
    }

    private void disableUseLocation() {
        this.switchUseLocationSpeed.setEnabled(false);
        this.switchUseLocationSpeed.setChecked(false);
        this.switchUseLocationDistance.setEnabled(false);
        this.switchUseLocationDistance.setChecked(false);
    }

    private void enableUseLocationSpeed() {
        this.switchUseSpeed.setEnabled(false);
        this.switchUseSpeed.setChecked(false);
        this.switchUseSpeedDistance.setEnabled(false);
        this.switchUseSpeedDistance.setChecked(false);
    }

    private void disableUseLocationSpeed() {
        this.switchUseSpeed.setEnabled(true);
        this.switchUseSpeed.setChecked(false);
        this.switchUseSpeedDistance.setChecked(false);
    }

    private void enableUseLocationDistance() {
        this.switchUseSpeedDistance.setEnabled(false);
        this.switchUseSpeedDistance.setChecked(false);
    }

    private void disableUseLocationDistance() {
        if (this.switchUseSpeed.isChecked() == true) {
            this.switchUseSpeedDistance.setEnabled(true);
        }
        this.switchUseSpeedDistance.setChecked(false);
    }


    private void enableUseSpeed() {
        if (this.switchUseLocation.isChecked() == true) {
            this.switchUseLocationSpeed.setEnabled(false);
            this.switchUseLocationSpeed.setChecked(false);
        }
        this.switchUseSpeedDistance.setEnabled(true);
        this.switchUseSpeedDistance.setChecked(false);
    }

    private void disableUseSpeed() {
        if (this.switchUseLocation.isChecked() == true) {
            this.switchUseLocationSpeed.setEnabled(true);
            this.switchUseLocationSpeed.setChecked(false);
        }
        this.switchUseSpeedDistance.setEnabled(false);
        this.switchUseSpeedDistance.setChecked(false);
    }

    private void enableUseSpeedDistance() {
        this.switchUseLocationDistance.setEnabled(false);
        this.switchUseLocationDistance.setChecked(false);
    }

    private void disableUseSpeedDistance() {
        if (this.switchUseLocation.isChecked() == true) {
            this.switchUseLocationDistance.setEnabled(true);
            this.switchUseLocationDistance.setChecked(false);
        }
    }

    private void enableUseCadence() {

    }

    private void disableUseCadence() {

    }
}