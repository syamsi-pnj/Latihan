package pnj.ac.id.tmjreg;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.PasswordAuthentication;

public class EksternalStorage extends AppCompatActivity {

    Button actionBuat,actionUbah,actionBaca,actionHapus,actionTambah;
    EditText edtInput;
    public static String FILENAME = "bacaan.txt";
    public static final int request_code = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        actionBuat = findViewById(R.id.actionBuat);
        actionUbah = findViewById(R.id.actionUbah);
        actionBaca = findViewById(R.id.actionBaca);
        actionHapus = findViewById(R.id.actionHapus);
        actionTambah = findViewById(R.id.actionTambah);
        edtInput = findViewById(R.id.edtInput);

        actionBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(periksaIzinPenyimpanan()){
                    buatData();

                }
            }
        });

        actionBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(periksaIzinPenyimpanan()){
                    bacaData();
                }

            }
        });

        actionUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(periksaIzinPenyimpanan()){
                    ubahData();
                }
            }
        });

        actionHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(periksaIzinPenyimpanan()){
                    hapusData();
                }
            }
        });

        actionTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(periksaIzinPenyimpanan()){
                    TambahData();
                }
            }
        });
    }

    void buatData() {

        String data = edtInput.getText().toString();
        /*String data = edtInput.getText().toString();

         */
        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            Log.e("ERROR", ""+e.getMessage());
        }
    }

    void TambahData() {

        String data = edtInput.getText().toString();
        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            Log.e("ERROR", ""+e.getMessage());
        }
    }

    void bacaData(){
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);

        if(file.exists()) {
           StringBuilder text = new StringBuilder();

           try {
               BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
               String line = bufferedReader.readLine();

               while (line !=null){
                   text.append(line);
                   line = bufferedReader.readLine();
               }

               bufferedReader.close();


           }catch (Exception e){
               Log.e("ERROR", ""+e.getMessage());
           }

           edtInput.setText(text.toString());
        }
    }

    void ubahData() {
        String data = edtInput.getText().toString();
        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            Log.e("ERROR", ""+e.getMessage());
        }
    }

    void hapusData() {
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        if(file.exists()) {
            file.delete();
        }
    }

    boolean periksaIzinPenyimpanan() {
        if(Build.VERSION.SDK_INT >= 23) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, request_code);
            return false;
            }
        }else {
            return  true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case request_code:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(EksternalStorage.this, "Izin Berhasil", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}