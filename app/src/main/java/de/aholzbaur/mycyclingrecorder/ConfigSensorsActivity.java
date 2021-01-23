package de.aholzbaur.mycyclingrecorder;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class ConfigSensorsActivity extends AppCompatActivity {
    private TextView textBtDisabledHint = null;
    private TextView textBtDeviceNameTitle = null;
    private TextView textBtDeviceAsSensor = null;

    private ScrollView scrollAvailBtDeviceList = null;
    private LinearLayout scrollAvailBtDeviceListLayout = null;

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
        this.textBtDeviceNameTitle = this.findViewById(R.id.textBtDeviceNameTitle);
        this.textBtDeviceAsSensor = this.findViewById(R.id.textBtDeviceAsSensor);

        this.scrollAvailBtDeviceList = this.findViewById(R.id.scrollAvailBtDeviceList);
        this.scrollAvailBtDeviceListLayout = this.findViewById(R.id.scrollAvailBtDeviceListLayout);

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
            this.textBtDeviceNameTitle.setVisibility(View.GONE);
            this.textBtDeviceAsSensor.setVisibility(View.GONE);
            this.scrollAvailBtDeviceList.setVisibility(View.GONE);
        } else {
            this.textBtDisabledHint.setVisibility(View.GONE);
            this.buttonEnableBt.setVisibility(View.GONE);
            this.textBtDeviceNameTitle.setVisibility(View.VISIBLE);
            this.textBtDeviceAsSensor.setVisibility(View.VISIBLE);
            this.scrollAvailBtDeviceList.setVisibility(View.VISIBLE);

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

            this.scrollAvailBtDeviceListLayout.addView(emptyListItem);
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