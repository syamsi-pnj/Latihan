package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pnj.ac.id.tmjreg.server.Server;

public class TambahPegawaiActivity extends AppCompatActivity {

    EditText edtNama,edtPosisi,edtGaji;
    Button actionSimpan;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pegawai);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");

        edtNama = findViewById(R.id.edtNama);
        edtGaji = findViewById(R.id.edtGaji);
        edtPosisi = findViewById(R.id.edtPosisi);
        actionSimpan = findViewById(R.id.actionSimpan);

        actionSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimData();
            }
        });
    }

    void kirimData() {

        progressDialog.show();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Server._TAMBAHPEGAWAI;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            Toast.makeText(TambahPegawaiActivity.this, msg, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(TambahPegawaiActivity.this, "Error :"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> data = new HashMap<>();
                data.put("name", edtNama.getText().toString());
                data.put("position", edtPosisi.getText().toString());
                data.put("salary", edtGaji.getText().toString());
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}