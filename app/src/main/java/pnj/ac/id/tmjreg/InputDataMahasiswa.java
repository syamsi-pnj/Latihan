package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pnj.ac.id.tmjreg.database.DatabaseHelper;

public class InputDataMahasiswa extends AppCompatActivity {
    EditText edtNama, edtEmail, edtJurusan, edtKelas, edtTanggalLahir, edtJamLahir;
    Button actionSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_mahasiswa);
        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtJurusan = findViewById(R.id.edtJurusan);
        edtKelas = findViewById(R.id.edtKelas);
        edtTanggalLahir = findViewById(R.id.edtTanggalLahir);
        edtJamLahir = findViewById(R.id.edtJamLahir);
        actionSimpan = findViewById(R.id.actionSimpan);

        actionSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });

        edtTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTanggalLahir();
            }
        });

        edtJamLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJamLahir();
            }
        });
    }


    void showTanggalLahir() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, listenerDate, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    void showJamLahir() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, listenerTime,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener listenerDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            edtTanggalLahir.setText(dateFormat.format(calendar.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener listenerTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            edtJamLahir.setText(timeFormat.format(calendar.getTime()));
        }
    };

    void simpan() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._COLUMN_NAMA, edtNama.getText().toString());
        contentValues.put(DatabaseHelper._COLUMN_EMAIL, edtEmail.getText().toString());
        contentValues.put(DatabaseHelper._COLUMN_JURUSAN, edtJurusan.getText().toString());
        contentValues.put(DatabaseHelper._COLUMN_KELAS, edtKelas.getText().toString());
        contentValues.put(DatabaseHelper._COLUMN_TANGGAL_LAHIR, edtTanggalLahir.getText().toString());
        contentValues.put(DatabaseHelper._COLUMN_JAM_LAHIR, edtJamLahir.getText().toString());

        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();

       long insert = db.insert(DatabaseHelper._TABLE_MAHASISWA, null, contentValues);

       if(insert != -1) {
           Toast.makeText(this, "Simpan Data Berhasil", Toast.LENGTH_SHORT).show();
           finish();
       }

    }

}