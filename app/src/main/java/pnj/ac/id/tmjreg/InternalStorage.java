package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class InternalStorage extends AppCompatActivity {

    Button actionBuat,actionUbah,actionBaca,actionHapus;
    EditText edtInput;
    public static String FILENAME = "bacaan.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        actionBuat = findViewById(R.id.actionBuat);
        actionUbah = findViewById(R.id.actionUbah);
        actionBaca = findViewById(R.id.actionBaca);
        actionHapus = findViewById(R.id.actionHapus);
        edtInput = findViewById(R.id.edtInput);

        actionBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buatData();
            }
        });

        actionBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacaData();
            }
        });

        actionUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahData();
            }
        });

        actionHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusData();
            }
        });
    }

    void buatData() {
        String data = edtInput.getText().toString();
        File file = new File(getFilesDir(),FILENAME);

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

    void bacaData(){
        File file = new File(getFilesDir(), FILENAME);

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

            String[] doc = text.toString().split(" ");

            //String text1 = doc[0];
            //String text2 = doc[1];
            //String text3 = kata[2];
           //text = text;
           //edtInput.setText(text.toString());
        }
    }

    void ubahData() {
        String data = edtInput.getText().toString();
        /*String data = edtInput.getText().toString();

         */
        //buat file baru
        File file = new File(getFilesDir(),FILENAME);

        //isi file
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
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()) {
            file.delete();
        }
    }
}