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
import pnj.ac.id.tmjreg.model.UserModel;

public class AdapterUser extends ArrayAdapter<UserModel> {
    int resource;
    Context context;

    public AdapterUser(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      Holder holder = null;
      if (convertView == null){
          holder = new Holder();
          convertView = LayoutInflater.from(context).inflate(resource,parent, false);
          holder.txtEmail = convertView.findViewById(R.id.txtEmail);
          holder.txtNama = convertView.findViewById(R.id.txtNama);
        convertView.setTag(holder);
      } else {
          holder = (Holder) convertView.getTag();
      }
      UserModel model = getItem(position);
      holder.txtEmail.setText(model.getEmail());
      holder.txtNama.setText(model.getNama());
        return convertView;
    }

    class Holder{
        TextView txtNama, txtEmail;
    }
}
