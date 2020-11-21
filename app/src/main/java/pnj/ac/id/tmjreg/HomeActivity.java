package pnj.ac.id.tmjreg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HomeActivity extends AppCompatActivity {

    //SharedPreferences preferences;
    TextView txtEmail, txtNama, txtJamLahir, txtTglLahir;
    public String[] doc;
    public static String FILENAME = "register.txt";
    //Button actionInternalStorage,actionEksternalStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //preferences = getSharedPreferences("profile", MODE_PRIVATE);
        txtEmail = findViewById(R.id.Email);
        txtNama = findViewById(R.id.Nama);
        txtJamLahir = findViewById(R.id.JamLahir);
        txtTglLahir = findViewById(R.id.TglLahir);
        bacaData();
        txtEmail.setText(doc[0]);
        txtNama.setText(doc[2]);
        txtJamLahir.setText(doc[3]);
        txtTglLahir.setText(doc[4]);


        //actionInternalStorage = findViewById(R.id.actionInternal);
        //actionEksternalStorage = findViewById(R.id.actionEksternalStorage);

        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.container, homeFragment);

        /*
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
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Logout:
                SharedPreferences sharedPreferences = this.getSharedPreferences("isLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();

                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
                this.finish();
                //Toast.makeText(this,"Menu 3 dipilih", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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

            doc = text.toString().split(" ");

        }

    }
}