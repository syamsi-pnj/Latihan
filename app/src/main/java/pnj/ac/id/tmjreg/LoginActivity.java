package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class LoginActivity extends AppCompatActivity {
    EditText edtEmail,edtPassword;
    Button actionLogin;
    TextView txtRegister;

    SharedPreferences preferences;
    public String[] doc;
    public static String FILENAME = "register.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("LoginStatus", MODE_PRIVATE);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        actionLogin = findViewById(R.id.actionLogin);
        txtRegister = findViewById(R.id.txtRegister);
        File file = new File(getFilesDir(),FILENAME);
        bacaData();

        actionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().length() > 0 && edtPassword.getText().toString().length()>0) {
                    //cek login
                    if(edtEmail.getText().toString().equals(doc[0]) && edtPassword.getText().toString().equals(doc[1])) {
                        //sukses login
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        SharedPreferences.Editor edit = preferences.edit();

//                        edit.putString("email", "syamsi@mail.com");
//                        edit.putString("nama", "syamsi dwi cahya");
                        edit.putBoolean("isLogin", true);
                        edit.commit();
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Mohon Maaf Email dan Password Salah", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this, "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void bacaData(){
        File file = new File(getFilesDir(),FILENAME);

        if(file.exists()){
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
                Log.e("ERROR",""+e.getMessage());
            }
            doc = text.toString().split(" ");
        }
    }
}