package pnj.ac.id.tmjreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pnj.ac.id.tmjreg.adapter.AdapterBerita;
import pnj.ac.id.tmjreg.model.BeritaModel;

public class LatihanListActivity extends AppCompatActivity {
    ListView listView;
    String[] items = new String[]{"Satu","Dua","Tiga"};

        AdapterBerita adapterBerita;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_list);
        listView = findViewById(R.id.listView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_expandable_list_item_1,items);
//        listView.setAdapter(adapter);
        adapterBerita = new AdapterBerita(this, R.layout.item_berita_layout);
        listView.setAdapter(adapterBerita);
        loadDataList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeritaModel model = (BeritaModel) parent.getAdapter().getItem(position);

                Intent intent =new Intent(LatihanListActivity.this, DetailBeritaActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("judul", model.getJudulBerita());
                intent.putExtra("isi",model.getIsiBerita());
                startActivity(intent);

            }
        });
    }

    void loadDataList() {
        String[] image = new String[]{"https://akcdn.detik.net.id/community/media/visual/2020/10/26/adv-transpark.jpeg?w=700&q=90",
        "https://akcdn.detik.net.id/community/media/visual/2020/10/27/kapolsek-mampang-prapatan-kompol-sujarwo-didampingi-kanit-reskrim-iptu-sigit-dan-kasubag-humas-akprita-1_169.jpeg?w=700&q=90",
        "https://akcdn.detik.net.id/community/media/visual/2020/10/27/temuan-indikasi-kehidupan-di-venus-sebuah-kesalahan-pengukuran.jpeg?w=700&q=90"};

        String[] judul = new String[]{"Vaksin COVID-19 Ditemukan, Saatnya Berburu Investasi Properti",
        "Alarm Berbunyi, 2 Pria Ini Gagal Curi Motor di Mampang Jaksel",
        "Temuan Indikasi Kehidupan di Venus, Sebuah Kesalahan Pengukuran?"};

        String[] isiBerita = new String[]{"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."};

        for(int i=0; i< image.length;i++){
            BeritaModel model = new BeritaModel();
            model.setImage(image[i]);
            model.setJudulBerita(judul[i]);
            model.setIsiBerita(isiBerita[i]);
            adapterBerita.add(model);
        }
        adapterBerita.notifyDataSetChanged();
    }
}