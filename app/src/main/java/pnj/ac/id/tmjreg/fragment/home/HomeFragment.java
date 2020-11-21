package pnj.ac.id.tmjreg.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.List;

import pnj.ac.id.tmjreg.DetailBeritaActivity;
import pnj.ac.id.tmjreg.LatihanListActivity;
import pnj.ac.id.tmjreg.LoginActivity;
import pnj.ac.id.tmjreg.R;
import pnj.ac.id.tmjreg.adapter.AdapterBerita;
import pnj.ac.id.tmjreg.model.BeritaModel;

public class HomeFragment extends Fragment {
    ListView listView;
    AdapterBerita adapterBerita;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        adapterBerita = new AdapterBerita(getActivity(), R.layout.item_berita_layout);
        listView.setAdapter(adapterBerita);
        loadDataList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeritaModel model = (BeritaModel) parent.getAdapter().getItem(position);

                Intent intent =new Intent(getActivity(), DetailBeritaActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("judul", model.getJudulBerita());
                intent.putExtra("isi",model.getIsiBerita());
                startActivity(intent);

            }
        });

        registerForContextMenu(listView);
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

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderTitle("Pilih Option");
//        menu.add(0,v.getId(),0,"Panggil");
//        menu.add(0,v.getId(),0,"Kirim SMS");
//        menu.add(0,v.getId(),0,"Hapus Contact");
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        if (item.getTitle().equals("Panggil")){
//            Toast.makeText(getActivity(),"Panggil dipilih", Toast.LENGTH_SHORT).show();
//        }else if (item.getTitle().equals("Kirim SMS")){
//            Toast.makeText(getActivity(),"Kirim SMS dipilih", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(getActivity(),"Hapus Contact dipilih", Toast.LENGTH_SHORT).show();
//        }

        switch (item.getItemId()){
            case R.id.actionMenu1:
                Toast.makeText(getActivity(),"Menu 1 dipilih", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionMenu2:
                Toast.makeText(getActivity(),"Menu 2 dipilih", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionMenu3:
                Toast.makeText(getActivity(),"Menu 3 dipilih", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionMenu1:
                Toast.makeText(getActivity(),"Menu 1 dipilih", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionMenu2:
                Toast.makeText(getActivity(),"Menu 2 dipilih", Toast.LENGTH_SHORT).show();
                break;
            case R.id.actionMenu3:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LatihanAndroid", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(),"Menu 3 dipilih", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}