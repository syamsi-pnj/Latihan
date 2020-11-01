package pnj.ac.id.tmjreg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import pnj.ac.id.tmjreg.R;
import pnj.ac.id.tmjreg.model.BeritaModel;

public class AdapterBerita extends ArrayAdapter<BeritaModel> {
    Context context;
    int resource;

    public AdapterBerita(@NonNull Context context, int resource) {
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
            holder.imageBerita = convertView.findViewById(R.id.imgBerita);
            holder.txtJudul = convertView.findViewById(R.id.txtJudul);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        BeritaModel model = getItem(position);
        Picasso.get().load(model.getImage()).into(holder.imageBerita);
        holder.txtJudul.setText(model.getJudulBerita());

        return convertView;
    }

    class Holder {
        ImageView imageBerita;
        TextView txtJudul;
    }
}
