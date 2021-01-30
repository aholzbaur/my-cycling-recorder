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
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

import de.aholzbaur.mycyclingrecorder.R;

public class ConfigSensorsActivity extends AppCompatActivity {
    private TextView textBtDisabledHint = null;
    private TextView textBtDeviceNameTitle = null;
    private TextView textBtDeviceAsSensor = null;
    private TextView textAddSensorsTitle = null;
    private TextView textGpsTitle = null;
    private TextView textUseGpsLocation = null;
    private TextView textUseGpsSpeedDistance = null;

    private Switch switchUseGpsLocation = null;
    private CompoundButton.OnCheckedChangeListener switchUseGpsLocationOnCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switchUseGpsSpeedDistance.setEnabled(isChecked);
        }
    };
    private Switch switchUseGpsSpeedDistance = null;

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
    private BluetoothAdapter bluetoothAdapter = null;

    private BroadcastReceiver btStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                configBtSensorsViews();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_config_sensors);

        this.getItems();

        this.configBtSensorsViews();

        this.registerReceiver(this.btStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
    }

    private void getItems() {
        this.textBtDisabledHint = this.findViewById(R.id.textBtDisabledHint);
        this.textBtDeviceNameTitle = this.findViewById(R.id.textBtDeviceNameTitle);
        this.textBtDeviceAsSensor = this.findViewById(R.id.textBtDeviceAsSensor);
        this.textAddSensorsTitle = this.findViewById(R.id.textAddSensorsTitle);
        this.textGpsTitle = this.findViewById(R.id.textGpsTitle);
        this.textUseGpsLocation = this.findViewById(R.id.textUseGpsLocation);
        this.textUseGpsSpeedDistance = this.findViewById(R.id.textUseGpsSpeedDistance);

        this.switchUseGpsLocation = this.findViewById(R.id.switchUseGpsLocation);
        this.switchUseGpsLocation.setOnCheckedChangeListener(this.switchUseGpsLocationOnCheckedListener);
        this.switchUseGpsSpeedDistance = this.findViewById(R.id.switchUseGpsSpeedDistance);
        this.switchUseGpsSpeedDistance.setEnabled(this.switchUseGpsLocation.isChecked());

        this.scrollAvailBtDeviceList = this.findViewById(R.id.scrollAvailBtDeviceList);
        this.scrollAvailBtDeviceListLayout = this.findViewById(R.id.scrollAvailBtDeviceListLayout);

        this.buttonEnableBt = this.findViewById(R.id.buttonEnableBt);
        this.buttonEnableBt.setOnClickListener(this.buttonEnableBtOnClickListener);
        this.buttonBtSettings = this.findViewById(R.id.buttonBtSettings);
        this.buttonBtSettings.setOnClickListener(this.buttonBtSettingsOnClickListener);
    }

    private void configBtSensorsViews() {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (this.bluetoothAdapter.isEnabled() == false) {
            this.textBtDisabledHint.setVisibility(View.VISIBLE);
            this.buttonEnableBt.setVisibility(View.VISIBLE);
            this.textBtDeviceNameTitle.setVisibility(View.GONE);
            this.textBtDeviceAsSensor.setVisibility(View.GONE);
            this.scrollAvailBtDeviceList.setVisibility(View.GONE);
            this.buttonBtSettings.setVisibility(View.GONE);
            this.textAddSensorsTitle.setVisibility(View.GONE);
            this.textGpsTitle.setVisibility(View.GONE);
            this.textUseGpsLocation.setVisibility(View.GONE);
            this.textUseGpsSpeedDistance.setVisibility(View.GONE);
            this.switchUseGpsLocation.setVisibility(View.GONE);
            this.switchUseGpsSpeedDistance.setVisibility(View.GONE);
        } else {
            this.textBtDisabledHint.setVisibility(View.GONE);
            this.buttonEnableBt.setVisibility(View.GONE);
            this.textBtDeviceNameTitle.setVisibility(View.VISIBLE);
            this.textBtDeviceAsSensor.setVisibility(View.VISIBLE);
            this.scrollAvailBtDeviceList.setVisibility(View.VISIBLE);
            this.buttonBtSettings.setVisibility(View.VISIBLE);
            this.textAddSensorsTitle.setVisibility(View.VISIBLE);
            this.textGpsTitle.setVisibility(View.VISIBLE);
            this.textUseGpsLocation.setVisibility(View.VISIBLE);
            this.textUseGpsSpeedDistance.setVisibility(View.VISIBLE);
            this.switchUseGpsLocation.setVisibility(View.VISIBLE);
            this.switchUseGpsSpeedDistance.setVisibility(View.VISIBLE);

            fillAvailBtDevicesList();
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
        startActivity(openBtSettingsIntent);
    }
}