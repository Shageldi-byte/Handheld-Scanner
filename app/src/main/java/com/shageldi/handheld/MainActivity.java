package com.shageldi.handheld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.handheldgroup.cap2dapi.ScannerIntent;
import com.handheldgroup.cap2dapi.SsiCodeTypes;
import com.handheldgroup.devtools.sidekey.SideKeyConfig;
import com.handheldgroup.devtools.sidekey.SidekeyManager;
import com.shageldi.handheld.honey.HoneywellScanner;
import com.shageldi.handheld.honey.HoneywellScannerNative;
import com.shageldi.handheld.honey.HoneywellScannerPlugin;
import com.shageldi.handheld.honey.ScannedData;
import com.shageldi.handheld.honey.ScannerCallBack;

import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HoneywellScannerNative scannerNative=new HoneywellScannerNative(this);
        scannerNative.startScanner();

//        Toast.makeText(this, "Start: "+scannerNative.isStarted(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Support: "+scannerNative.isSupported(), Toast.LENGTH_SHORT).show();

        scannerNative.setScannerCallBack(new ScannerCallBack() {
            @Override
            public void onDecoded(ScannedData scannedData) {
                System.out.println(scannedData.getCode());
//                Toast.makeText(MainActivity.this, scannedData.getCharset(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception error) {
                Log.e("ERROR",error.getMessage());
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}