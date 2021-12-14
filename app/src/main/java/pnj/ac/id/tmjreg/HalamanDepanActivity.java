package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HalamanDepanActivity extends AppCompatActivity {

    Button actionLihatData,actionTambahData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_depan);
        actionLihatData = findViewById(R.id.actionLihat);
        actionTambahData = findViewById(R.id.actionInsert);


        actionTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanDepanActivity.this, TambahPegawaiActivity.class);
                startActivity(intent);
            }
        });

        actionLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanDepanActivity.this, ListPegawaiActivity.class);
                startActivity(intent);
            }
        });

    }
}