package pnj.ac.id.tmjreg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import pnj.ac.id.tmjreg.R;
import pnj.ac.id.tmjreg.model.PegawaiModel;

public class AdapterPegawai extends ArrayAdapter<PegawaiModel> {
    Context context;
    int resource;

    public AdapterPegawai(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      Holder holder = null;

        if(convertView==null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            holder.txtNama = convertView.findViewById(R.id.txtNama);
            holder.txtPosisi = convertView.findViewById(R.id.txtPosisi);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        PegawaiModel model = getItem(position);
        holder.txtNama.setText(model.getNama());
        holder.txtPosisi.setText(model.getPosisi());

        return convertView;
    }

    class Holder {
        TextView txtNama,txtPosisi;
    }
}
