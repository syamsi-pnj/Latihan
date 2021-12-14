package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pnj.ac.id.tmjreg.adapter.AdapterPegawai;
import pnj.ac.id.tmjreg.model.PegawaiModel;
import pnj.ac.id.tmjreg.server.Server;

public class ListPegawaiActivity extends AppCompatActivity {

    ListView listView;
    AdapterPegawai adapterPegawai;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pegawai);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        listView = findViewById(R.id.listView);
        adapterPegawai = new AdapterPegawai(this, R.layout.item_list_pegawai);
        listView.setAdapter(adapterPegawai);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    void getData() {
        progressDialog.show();
        ArrayList data = new ArrayList<PegawaiModel>();
        adapterPegawai.clear();
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Server._LISTPEGAWAI;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            if(jsonArray.length()>0){

                                for(int i=0; i< jsonArray.length(); i++) {

                                    JSONObject item = jsonArray.getJSONObject(i);

                                    PegawaiModel model = new PegawaiModel();
                                    model.setId(item.getString("id"));
                                    model.setNama(item.getString("name"));
                                    model.setGaji(item.getString("gaji"));
                                    model.setPosisi(item.getString("posisi"));
                                    data.add(model);
                                }
                                adapterPegawai.addAll(data);
                                adapterPegawai.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ListPegawaiActivity.this, "Error :"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}