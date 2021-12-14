package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pnj.ac.id.tmjreg.service.MyService;

public class LatihanServiceActivity extends AppCompatActivity {

    Button actionStartService,actionStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_service);
        actionStartService = findViewById(R.id.actionStart);
        actionStopService = findViewById(R.id.actionStopService);


        actionStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(LatihanServiceActivity.this, MyService.class));
            }
        });

        actionStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(LatihanServiceActivity.this, MyService.class));
            }
        });

    }
}