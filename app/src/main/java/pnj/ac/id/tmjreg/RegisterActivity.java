package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.ac.id.tmjreg.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtBod,edtJamLahir,edtEmail,edtPassword,edtNama;
    String nama, email, jam_lahir, bod, password, data;
    Button actionSimpan;
    Calendar calendar = Calendar.getInstance();
    public static String FILENAME = "register.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        actionSimpan = findViewById(R.id.actionSimpan);
        edtBod = findViewById(R.id.edtBod);
        edtJamLahir =findViewById(R.id.edtJamLahir);

        edtBod.setOnClickListener(this);
        edtJamLahir.setOnClickListener(this);
        actionSimpan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edtBod:
                //event click BOD
                new DatePickerDialog(this,dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.edtJamLahir:
                //event click Jam Lahir
                new TimePickerDialog(this, timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),true).show();
                break;
            case R.id.actionSimpan:
                if (edtNama.getText().toString().length() > 0 && edtEmail.getText().toString().length() > 0 && edtPassword.getText().toString().length()>0
                        && edtBod.getText().toString().length()>0 && edtJamLahir.getText().toString().length() > 0){
                    simpan();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    void simpan(){
        nama = edtNama.getText().toString();
        email = edtEmail.getText().toString();;
        jam_lahir = edtJamLahir.getText().toString();
        bod = edtBod.getText().toString();
        password = edtPassword.getText().toString();

        data = email +" "+ password +" "+ nama +" "+  jam_lahir +" "+ bod;

        //buat file baru
        File file = new File(getFilesDir(),FILENAME);

        //pemasukan isi file
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


    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            edtBod.setText(dateFormat.format(calendar.getTime()));

        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            edtJamLahir.setText(timeFormat.format(calendar.getTime()));
        }
    };
}