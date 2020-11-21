package pnj.ac.id.tmjreg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public Button button;
    SharedPreferences preferences;
    TextView txtEmail, txtNama;

    Button actionInternalStorage,actionEksternalStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferences = getSharedPreferences("profile", MODE_PRIVATE);
        txtEmail = findViewById(R.id.txtEmail);
        txtNama = findViewById(R.id.txtNama);

        actionInternalStorage = findViewById(R.id.actionInternal);
        actionEksternalStorage = findViewById(R.id.actionEksternalStorage);

        txtEmail.setText(preferences.getString("email", ""));
        txtNama.setText(preferences.getString("nama", ""));

        actionInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InternalStorage.class);
                startActivity(intent);
            }
        });

        actionEksternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EksternalStorage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu_tambah) {
            Intent tambah = new Intent(HomeActivity.this, InputDataMahasiswa.class);
            startActivity(tambah);
        }else {

        }

        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.bruBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,TambahInternal.class);
                startActivity(intent);
            }
        });*/
}
