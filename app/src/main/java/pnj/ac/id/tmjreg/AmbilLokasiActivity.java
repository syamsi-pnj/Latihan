package pnj.ac.id.tmjreg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import pnj.ac.id.tmjreg.receiver.MessageReceiver;

public class AmbilLokasiActivity extends AppCompatActivity implements LocationListener {

    TextView tvLat;
    TextView tvLng;
    TextView tvStatus;
    double lat;
    double lng;
    long minTime;
    float minDistance;
    String locProvider;
    LocationManager locMgr;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_lokasi);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvLat =  (TextView) findViewById(R.id.tvLat);
        tvLng =  (TextView) findViewById(R.id.tvLng);

        if(periksaIzinPenyimpanan()){
            // ambil location manager
            locMgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            //ambil lokasi terakhir berdasarkan network agar cepat//user dapat kesal jika diawal menunggu terlalu lama app mendapatkan lokasi
            tvStatus.setText("ambil lokasi terakhir berdasarkan network");
            locProvider = LocationManager.NETWORK_PROVIDER;
            @SuppressLint("MissingPermission") Location lastKnownLocation = locMgr.getLastKnownLocation(locProvider);

        try {
            lat = lastKnownLocation.getLatitude();
            lng = lastKnownLocation.getLongitude();
            tvLat.setText(String.valueOf(lat));
            tvLng.setText(String.valueOf(lng));
        }catch (Exception ex) {
            Log.e("error", ""+ ex.getMessage());
        }

            Criteria cr = new Criteria();
            cr.setAccuracy(Criteria.ACCURACY_FINE);
            locProvider = locMgr.getBestProvider(cr, false);

            minTime = 5 * 1000;
            minDistance = 1;
        }
    }

    boolean periksaIzinPenyimpanan() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
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
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(AmbilLokasiActivity.this, "Izin Berhasil", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        tvLat.setText(String.valueOf(lat));tvLng.setText(String.valueOf(lng));
        Time now = new Time();
        now.setToNow();tvStatus.setText("Direfresh berdasarkan:  "+locProvider+" Waktu: "+now.hour+":"+now.minute+":"+now.second);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

}