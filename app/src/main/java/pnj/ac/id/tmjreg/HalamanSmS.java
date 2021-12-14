package pnj.ac.id.tmjreg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import pnj.ac.id.tmjreg.receiver.MessageListener;
import pnj.ac.id.tmjreg.receiver.MessageReceiver;

public class HalamanSmS extends AppCompatActivity implements MessageListener, View.OnClickListener {
    public static final int request_code = 100;
    Button actionSmsByIntent, actionSmsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_sm_s);

        actionSmsByIntent = findViewById(R.id.actionSmsByIntent);
        actionSmsManager = findViewById(R.id.actionSmsManager);
        actionSmsManager.setOnClickListener(this);
        actionSmsByIntent.setOnClickListener(this);


        if (periksaIzinPenyimpanan()) {
            MessageReceiver.bindListener(this);
        }

    }

    @Override
    public void messageReceived(String message) {
        Toast.makeText(this, "New Message Received: " + message, Toast.LENGTH_SHORT).show();
    }

    boolean periksaIzinPenyimpanan() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS}, request_code);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case request_code:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(HalamanSmS.this, "Izin Berhasil", Toast.LENGTH_SHORT).show();
                    MessageReceiver.bindListener(HalamanSmS.this);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionSmsByIntent:
                if(periksaIzinPenyimpanan()){
                    sendSmsBySIntent();
                }
                break;
            case R.id.actionSmsManager:
                if(periksaIzinPenyimpanan()){
                    sendSmsByManager();
                }
                break;
        }
    }

    public void sendSmsByManager() {
        String nomorTujuan = "0089613614003";
        String message = "Halo Apa Kabar";
        try {// Mengambil default instance dari SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(nomorTujuan, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Berhasil Dikirim!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Pengiriman SMS Gagal...", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void sendSmsBySIntent() {
        // menambahkan phone number ke URI data
        String nomorTujuan = "0089613614003";
        String message = "Halo Apa Kabar";
        Uri uri = Uri.parse("smsto:" + nomorTujuan);// membuat intent baru dengan ACTION_SENDTO
        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);// menambahkan isi SMS pada field sms_body
        smsSIntent.putExtra("sms_body", message);
        try {
            startActivity(smsSIntent);
        } catch (Exception ex) {
            Toast.makeText(HalamanSmS.this, "Pengiriman SMS Gagal...", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}