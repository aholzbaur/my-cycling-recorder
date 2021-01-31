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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.aholzbaur.mycyclingrecorder.R;
import de.aholzbaur.mycyclingrecorder.data.Settings;
import de.aholzbaur.mycyclingrecorder.dialogs.CloseAppOnErrorDialog;

public class MainActivity extends AppCompatActivity {
    private static final int OPEN_CONFIG_SENSORS_ACTIVITY = 1;

    private TextView textBtStatusValue = null;

    private Button buttonConfigSensors = null;

    private TextView textLocationTitle = null;
    private TextView textLongitudeValue = null;
    private TextView textLatitudeValue = null;
    private TextView textAccuracyValue = null;

    private TextView textSpeedTitle = null;
    private TextView textSpeedValue = null;
    private TextView textDistanceTitle = null;
    private TextView textDistanceValue = null;

    private Button buttonStart = null;

    private BluetoothAdapter bluetoothAdapter = null;

    private Settings settings = Settings.getInstance();

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

        this.configViews();

        this.checkBtOnStart();
    }

    private void getItems() {
        this.textBtStatusValue = (TextView) this.findViewById(R.id.textBtStatusValue);

        this.buttonConfigSensors = (Button) this.findViewById(R.id.buttonConfigSensors);
        this.buttonConfigSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfigSensorsView();
            }
        });

        this.textLocationTitle = (TextView) this.findViewById(R.id.textLocationTitle);
        this.textLongitudeValue = (TextView) this.findViewById(R.id.textLongitudeValue);
        this.textLatitudeValue = (TextView) this.findViewById(R.id.textLatitudeValue);
        this.textAccuracyValue = (TextView) this.findViewById(R.id.textAccuracyValue);

        this.textDistanceTitle = (TextView) this.findViewById(R.id.textDistanceTitle);
        this.textDistanceValue = (TextView) this.findViewById(R.id.textDistanceValue);
        this.textSpeedTitle = (TextView) this.findViewById(R.id.textSpeedTitle);
        this.textSpeedValue = (TextView) this.findViewById(R.id.textSpeedValue);

        this.buttonStart = (Button) this.findViewById(R.id.buttonStart);
        this.buttonStart.setEnabled(false);
    }

    private void configViews() {
        if (this.settings.isUseGpsLocation() == true) {
            this.textLocationTitle.setVisibility(View.VISIBLE);
            this.textLongitudeValue.setVisibility(View.VISIBLE);
            this.textLatitudeValue.setVisibility(View.VISIBLE);
            this.textAccuracyValue.setVisibility(View.VISIBLE);
        } else {
            this.textLocationTitle.setVisibility(View.GONE);
            this.textLongitudeValue.setVisibility(View.GONE);
            this.textLatitudeValue.setVisibility(View.GONE);
            this.textAccuracyValue.setVisibility(View.GONE);
        }

        if (this.settings.isUseGpsSpeedDistance() == true) {
            this.textDistanceTitle.setVisibility(View.VISIBLE);
            this.textDistanceValue.setVisibility(View.VISIBLE);
            this.textSpeedTitle.setVisibility(View.VISIBLE);
            this.textSpeedValue.setVisibility(View.VISIBLE);
        } else {
            this.textDistanceTitle.setVisibility(View.GONE);
            this.textDistanceValue.setVisibility(View.GONE);
            this.textSpeedTitle.setVisibility(View.GONE);
            this.textSpeedValue.setVisibility(View.GONE);
        }
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
        this.startActivityForResult(intentConfigSensors, OPEN_CONFIG_SENSORS_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == OPEN_CONFIG_SENSORS_ACTIVITY) {
            configViews();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unregisterReceiver(this.btStateReceiver);
    }
}