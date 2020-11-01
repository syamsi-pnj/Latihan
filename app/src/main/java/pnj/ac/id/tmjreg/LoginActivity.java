package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText edtEmail,edtPassword;
    Button actionLogin;
    TextView txtRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        actionLogin = findViewById(R.id.actionLogin);
        txtRegister = findViewById(R.id.txtRegister);

        actionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().length() > 0 && edtPassword.getText().toString().length()>0) {
                    //cek login
                    if(edtEmail.getText().toString().equals("syamsi@mail.com") && edtPassword.getText().toString().equals("1234")) {
                        //sukses login
                        Intent intent = new Intent(LoginActivity.this, LatihanListActivity.class);
                        startActivity(intent);
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
}