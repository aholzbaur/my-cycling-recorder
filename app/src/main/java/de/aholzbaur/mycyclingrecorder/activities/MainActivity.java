package de.aholzbaur.mycyclingrecorder.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.aholzbaur.mycyclingrecorder.R;
import de.aholzbaur.mycyclingrecorder.dialogs.CloseAppOnErrorDialog;

public class MainActivity extends AppCompatActivity {
    private TextView textBtStatusValue = null;

    private Button buttonConfigSensors = null;
    private Button buttonStart = null;

    private BluetoothAdapter bluetoothAdapter = null;

    private BroadcastReceiver btStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                checkBtStatus();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.getItems();

        this.checkBtOnStart();
    }

    private void getItems() {
        this.textBtStatusValue = this.findViewById(R.id.textBtStatusValue);

        this.buttonConfigSensors = this.findViewById(R.id.buttonConfigSensors);
        this.buttonConfigSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfigSensorsView();
            }
        });
        this.buttonStart = this.findViewById(R.id.buttonStart);
        this.buttonStart.setEnabled(false);
    }

    private void checkBtOnStart() {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (this.bluetoothAdapter == null) {
            CloseAppOnErrorDialog d = new CloseAppOnErrorDialog(getResources().getString(R.string.dialog_close_app_bt_not_supported), this);
            d.show(getSupportFragmentManager(), null);
        } else {
            this.registerReceiver(this.btStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
            this.checkBtStatus();
        }
    }

    private void checkBtStatus() {
        if (this.bluetoothAdapter.isEnabled() == true) {
            this.textBtStatusValue.setText(R.string.bt_status_on);
        } else {
            this.textBtStatusValue.setText(R.string.bt_status_off);
        }
    }

    private void openConfigSensorsView() {
        Intent intentConfigSensors = new Intent(this, ConfigSensorsActivity.class);
        this.startActivity(intentConfigSensors);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unregisterReceiver(this.btStateReceiver);
    }
}