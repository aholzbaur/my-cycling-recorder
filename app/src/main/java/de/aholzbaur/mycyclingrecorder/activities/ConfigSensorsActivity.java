package de.aholzbaur.mycyclingrecorder.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.Set;

import de.aholzbaur.mycyclingrecorder.R;
import de.aholzbaur.mycyclingrecorder.data.Settings;

public class ConfigSensorsActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout = null;

    private TextView textBtDisabledHint = null;
    private TextView textBtDeviceNameTitle = null;
    private TextView textBtDeviceAsSensor = null;

    private ScrollView scrollAvailBtDeviceList = null;
    private LinearLayout scrollAvailBtDeviceListLayout = null;
    private Button buttonEnableBt = null;
    private final View.OnClickListener buttonEnableBtOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showEnableBtIntent();
        }
    };
    private Button buttonBtSettings = null;
    private final View.OnClickListener buttonBtSettingsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showBtSettings();
        }
    };

    private TextView textAddSensorsTitle = null;
    private TextView textGpsTitle = null;
    private TextView textUseGpsLocation = null;

    private SwitchCompat switchUseGpsLocation = null;
    private CompoundButton.OnCheckedChangeListener switchUseGpsLocationOnCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switchUseGpsSpeedDistance.setEnabled(isChecked);
            updateSettings();
        }
    };
    private TextView textUseGpsSpeedDistance = null;
    private SwitchCompat switchUseGpsSpeedDistance = null;
    private CompoundButton.OnCheckedChangeListener switchUseGpsSpeedDistanceOnCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            updateSettings();
        }
    };

    private BluetoothAdapter bluetoothAdapter = null;

    private Settings settings = Settings.getInstance();

    private BroadcastReceiver btStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                configViews();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_config_sensors);

        this.getItems();

        this.configViews();

        this.registerReceiver(this.btStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
    }

    private void getItems() {
        this.constraintLayout = (ConstraintLayout) this.findViewById(R.id.constraintLayout);

        this.textBtDisabledHint = (TextView) this.findViewById(R.id.textBtDisabledHint);
        this.textBtDeviceNameTitle = (TextView) this.findViewById(R.id.textBtDeviceNameTitle);
        this.textBtDeviceAsSensor = (TextView) this.findViewById(R.id.textBtDeviceAsSensor);

        this.scrollAvailBtDeviceList = (ScrollView) this.findViewById(R.id.scrollAvailBtDeviceList);
        this.scrollAvailBtDeviceListLayout = (LinearLayout) this.findViewById(R.id.scrollAvailBtDeviceListLayout);

        this.buttonEnableBt = (Button) this.findViewById(R.id.buttonEnableBt);
        this.buttonEnableBt.setOnClickListener(this.buttonEnableBtOnClickListener);
        this.buttonBtSettings = (Button) this.findViewById(R.id.buttonBtSettings);
        this.buttonBtSettings.setOnClickListener(this.buttonBtSettingsOnClickListener);

        this.textAddSensorsTitle = (TextView) this.findViewById(R.id.textAddSensorsTitle);
        this.textGpsTitle = (TextView) this.findViewById(R.id.textGpsTitle);
        this.textUseGpsLocation = (TextView) this.findViewById(R.id.textUseGpsLocation);
        this.switchUseGpsLocation = (SwitchCompat) this.findViewById(R.id.switchUseGpsLocation);
        this.switchUseGpsLocation.setChecked(this.settings.isUseGpsLocation());
        this.switchUseGpsLocation.setOnCheckedChangeListener(this.switchUseGpsLocationOnCheckedListener);
        this.textUseGpsSpeedDistance = (TextView) this.findViewById(R.id.textUseGpsSpeedDistance);
        this.switchUseGpsSpeedDistance = (SwitchCompat) this.findViewById(R.id.switchUseGpsSpeedDistance);
        this.switchUseGpsSpeedDistance.setChecked(this.settings.isUseGpsSpeedDistance());
        this.switchUseGpsSpeedDistance.setEnabled(this.switchUseGpsLocation.isChecked());
        this.switchUseGpsSpeedDistance.setOnCheckedChangeListener(this.switchUseGpsSpeedDistanceOnCheckedListener);
    }

    private void configViews() {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (this.bluetoothAdapter.isEnabled() == false) {
            this.textBtDisabledHint.setVisibility(View.VISIBLE);
            this.buttonEnableBt.setVisibility(View.VISIBLE);
            this.textBtDeviceNameTitle.setVisibility(View.GONE);
            this.textBtDeviceAsSensor.setVisibility(View.GONE);
            this.scrollAvailBtDeviceList.setVisibility(View.GONE);
            this.buttonBtSettings.setVisibility(View.GONE);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.constraintLayout);
            constraintSet.connect(R.id.textAddSensorsTitle, ConstraintSet.TOP, R.id.buttonEnableBt, ConstraintSet.BOTTOM);
            constraintSet.applyTo(this.constraintLayout);
        } else {
            this.textBtDisabledHint.setVisibility(View.GONE);
            this.buttonEnableBt.setVisibility(View.GONE);
            this.textBtDeviceNameTitle.setVisibility(View.VISIBLE);
            this.textBtDeviceAsSensor.setVisibility(View.VISIBLE);
            this.scrollAvailBtDeviceList.setVisibility(View.VISIBLE);
            this.buttonBtSettings.setVisibility(View.VISIBLE);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.constraintLayout);
            constraintSet.connect(R.id.textAddSensorsTitle, ConstraintSet.TOP, R.id.buttonBtSettings, ConstraintSet.BOTTOM);
            constraintSet.applyTo(this.constraintLayout);

            this.fillAvailBtDevicesList();
        }
    }

    void fillAvailBtDevicesList() {
        this.scrollAvailBtDeviceListLayout.removeAllViews();

        Set<BluetoothDevice> availBtDevices = this.bluetoothAdapter.getBondedDevices();

        if (availBtDevices.size() > 0) {
            for (BluetoothDevice device : availBtDevices) {
                LinearLayout newListItem = (LinearLayout) this.getLayoutInflater().inflate(R.layout.avail_bt_device_list_item, null);
                TextView newListItemName = (TextView) newListItem.getChildAt(0);

                newListItemName.setText(device.getName());

                this.scrollAvailBtDeviceListLayout.addView(newListItem);
            }
        } else {
            LinearLayout emptyListItem = (LinearLayout) this.getLayoutInflater().inflate(R.layout.avail_bt_device_list_item, null);
            CheckBox emptyListItemCheckbox = (CheckBox) emptyListItem.getChildAt(2);

            emptyListItemCheckbox.setVisibility(View.INVISIBLE);

            this.scrollAvailBtDeviceListLayout.addView(emptyListItem);
        }
    }

    private void showEnableBtIntent() {
        Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

        this.startActivity(enableBluetoothIntent);
    }

    private void showBtSettings() {
        Intent openBtSettingsIntent = new Intent(Intent.ACTION_MAIN, null);
        openBtSettingsIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName openBtSettingsCn = new ComponentName("com.android.settings", "com.android.settings.bluetooth.BluetoothSettings");
        openBtSettingsIntent.setComponent(openBtSettingsCn);
        openBtSettingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(openBtSettingsIntent);
    }

    private void updateSettings() {
        if (this.switchUseGpsLocation.isChecked() == true) {
            this.settings.setUseGpsLocation(true);
            this.settings.setUseGpsSpeedDistance(this.switchUseGpsSpeedDistance.isChecked());
        } else {
            this.settings.setUseGpsLocation(false);
            this.settings.setUseGpsSpeedDistance(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unregisterReceiver(this.btStateReceiver);
    }
}