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
    private SwitchCompat switchUseLocationSpeed = null;
    private SwitchCompat switchUseLocationDistance = null;

    private TextView textTitleSpeed = null;
    private SwitchCompat switchUseSpeed = null;
    private SwitchCompat switchUseSpeedDistance = null;

    private TextView textTitleCadence = null;
    private SwitchCompat switchUseCadence = null;

    private CompoundButton.OnCheckedChangeListener switchOnCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            updateSwitches();
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
        this.switchUseLocation.setOnCheckedChangeListener(this.switchOnCheckedListener);
        this.switchUseLocationSpeed = this.findViewById(R.id.switchUseLocationSpeed);
        this.switchUseLocationSpeed.setOnCheckedChangeListener(this.switchOnCheckedListener);
        this.switchUseLocationDistance = this.findViewById(R.id.switchUseLocationDistance);
        this.switchUseLocationDistance.setOnCheckedChangeListener(this.switchOnCheckedListener);

        this.textTitleSpeed = this.findViewById(R.id.textTitleSpeed);
        this.switchUseSpeed = this.findViewById(R.id.switchUseSpeed);
        this.switchUseSpeed.setOnCheckedChangeListener(this.switchOnCheckedListener);
        this.switchUseSpeedDistance = this.findViewById(R.id.switchUseSpeedDistance);
        this.switchUseSpeedDistance.setOnCheckedChangeListener(this.switchOnCheckedListener);

        this.textTitleCadence = this.findViewById(R.id.textTitleCadence);
        this.switchUseCadence = this.findViewById(R.id.switchUseCadence);
        this.switchUseCadence.setOnCheckedChangeListener(this.switchOnCheckedListener);
    }

    private void updateSwitches() {

    }
}