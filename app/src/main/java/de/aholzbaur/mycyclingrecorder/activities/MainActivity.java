package de.aholzbaur.mycyclingrecorder.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button buttonStart = null;
    private View.OnClickListener buttonStartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.getItems();

        this.initItems();

        this.setItemListeners();
    }

    private void getItems() {
        this.textTitleLocation = this.findViewById(R.id.textTitleLocation);
        this.switchUseLocation = this.findViewById(R.id.switchUseLocation);
        this.switchUseLocationDistance = this.findViewById(R.id.switchUseLocationDistance);
        this.switchUseLocationSpeed = this.findViewById(R.id.switchUseLocationSpeed);

        this.textTitleSpeed = this.findViewById(R.id.textTitleSpeed);
        this.switchUseSpeed = this.findViewById(R.id.switchUseSpeed);
        this.switchUseSpeedDistance = this.findViewById(R.id.switchUseSpeedDistance);

        this.textTitleCadence = this.findViewById(R.id.textTitleCadence);
        this.switchUseCadence = this.findViewById(R.id.switchUseCadence);

        this.buttonStart = this.findViewById(R.id.buttonStart);
    }

    private void initItems() {
        this.switchUseLocation.setChecked(false);
        this.switchUseLocation.setEnabled(true);
        this.switchUseLocationDistance.setChecked(false);
        this.switchUseLocationDistance.setEnabled(false);
        this.switchUseLocationSpeed.setChecked(false);
        this.switchUseLocationSpeed.setEnabled(false);

        this.switchUseSpeed.setChecked(false);
        this.switchUseSpeed.setEnabled(true);
        this.switchUseSpeedDistance.setChecked(false);
        this.switchUseSpeedDistance.setEnabled(false);

        this.switchUseCadence.setChecked(false);
        this.switchUseCadence.setEnabled(true);

        this.buttonStart.setEnabled(false);
    }

    private void setItemListeners() {
        this.switchUseLocation.setOnCheckedChangeListener(this.switchUseLocationListener);
        this.switchUseLocationDistance.setOnCheckedChangeListener(this.switchUseLocationDistanceListener);
        this.switchUseLocationSpeed.setOnCheckedChangeListener(this.switchUseLocationSpeedListener);

        this.switchUseSpeed.setOnCheckedChangeListener(this.switchUseSpeedListener);
        this.switchUseSpeedDistance.setOnCheckedChangeListener(this.switchUseSpeedDistanceListener);

        this.switchUseCadence.setOnCheckedChangeListener(this.switchUseCadenceListener);

        this.buttonStart.setOnClickListener(this.buttonStartListener);
    }

    private void disableAllBtSensors() {
        this.switchUseSpeed.setEnabled(false);
        this.switchUseSpeed.setChecked(false);
        this.switchUseSpeedDistance.setEnabled(false);
        this.switchUseSpeedDistance.setChecked(false);

        this.switchUseCadence.setEnabled(false);
        this.switchUseCadence.setChecked(false);
    }

    private void enableAllBtSensors() {
        this.switchUseSpeed.setEnabled(true);
        this.switchUseSpeed.setChecked(false);
        this.switchUseSpeedDistance.setEnabled(false);
        this.switchUseSpeedDistance.setChecked(false);

        this.switchUseCadence.setEnabled(true);
        this.switchUseCadence.setChecked(false);
    }

    private void enableUseLocation() {
        if (this.switchUseSpeedDistance.isChecked() == false) {
            this.switchUseLocationDistance.setEnabled(true);
            this.switchUseLocationDistance.setChecked(false);
        }
        if (this.switchUseSpeed.isChecked() == false) {
            this.switchUseLocationSpeed.setEnabled(true);
            this.switchUseLocationSpeed.setChecked(false);
        }
    }

    private void disableUseLocation() {
        this.switchUseLocationDistance.setEnabled(false);
        this.switchUseLocationDistance.setChecked(false);
        this.switchUseLocationSpeed.setEnabled(false);
        this.switchUseLocationSpeed.setChecked(false);
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

    private void enableUseSpeed() {
        if (this.switchUseLocationDistance.isChecked() == false) {
            this.switchUseSpeedDistance.setEnabled(true);
            this.switchUseSpeedDistance.setChecked(false);
        }
        if (this.switchUseLocation.isChecked() == true) {
            this.switchUseLocationSpeed.setEnabled(false);
            this.switchUseLocationSpeed.setChecked(false);
        }
    }

    private void disableUseSpeed() {
        this.switchUseSpeedDistance.setEnabled(false);
        this.switchUseSpeedDistance.setChecked(false);
        if (this.switchUseLocation.isChecked() == true) {
            this.switchUseLocationSpeed.setEnabled(true);
            this.switchUseLocationSpeed.setChecked(false);
        }
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