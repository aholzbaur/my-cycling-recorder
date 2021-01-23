package de.aholzbaur.mycyclingrecorder;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigSensorsActivity extends AppCompatActivity {
    private TextView textBtDisabledHint = null;
    private TextView textDeviceNameTitle = null;
    private TextView textDeviceAsSensor = null;

    private ScrollView scrollAvailDeviceList = null;

    private Button buttonEnableBt = null;

    private static final int INTENT_ACTION_ENABLE_BLUETOOTH = 1;

    private BluetoothAdapter bluetoothAdapter = null;

    private BroadcastReceiver btStateReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_config_sensors);

        this.getItems();

        this.configBtSensorsViews();

        this.configBtStateReceiver();
    }

    private void getItems() {
        this.textBtDisabledHint = this.findViewById(R.id.textBtDisabledHint);
        this.textDeviceNameTitle = this.findViewById(R.id.textDeviceNameTitle);
        this.textDeviceAsSensor = this.findViewById(R.id.textDeviceAsSensor);

        this.scrollAvailDeviceList = this.findViewById(R.id.scrollAvailDeviceList);

        this.buttonEnableBt = this.findViewById(R.id.buttonEnableBt);
        this.buttonEnableBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnableBtIntent();
            }
        });
    }

    private void configBtSensorsViews() {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (this.bluetoothAdapter.isEnabled() == false) {
            this.textBtDisabledHint.setVisibility(View.VISIBLE);
            this.buttonEnableBt.setVisibility(View.VISIBLE);
            this.textDeviceNameTitle.setVisibility(View.GONE);
            this.textDeviceAsSensor.setVisibility(View.GONE);
            this.scrollAvailDeviceList.setVisibility(View.GONE);
        } else {
            this.textBtDisabledHint.setVisibility(View.GONE);
            this.buttonEnableBt.setVisibility(View.GONE);
            this.textDeviceNameTitle.setVisibility(View.VISIBLE);
            this.textDeviceAsSensor.setVisibility(View.VISIBLE);
            this.scrollAvailDeviceList.setVisibility(View.VISIBLE);
        }
    }

    private void configBtStateReceiver() {
        this.btStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    configBtSensorsViews();
                }
            }
        };
        this.registerReceiver(this.btStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
    }

    private void showEnableBtIntent() {
        Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        this.startActivity(enableBluetoothIntent);
    }
}