package pnj.ac.id.tmjreg.fragment.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pnj.ac.id.tmjreg.LatihanListActivity;
import pnj.ac.id.tmjreg.LoginActivity;
import pnj.ac.id.tmjreg.R;
import pnj.ac.id.tmjreg.UpdateActivity;
import pnj.ac.id.tmjreg.database.DatabaseHelper;

public class ProfileFragment extends Fragment {
    TextView edtBod,edtJamLahir,edtEmail,edtNama;
    Button actionEdit;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("LatihanAndroid", Context.MODE_PRIVATE);
        edtBod = view.findViewById(R.id.edtBod);
        edtJamLahir = view.findViewById(R.id.edtJamLahir);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtNama = view.findViewById(R.id.edtNama);
        actionEdit = view.findViewById(R.id.actionEdit);

        SQLiteDatabase database = new DatabaseHelper(getActivity()).getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM tb_user WHERE id = ? ", new String[]{""+sharedPreferences.getInt("id",0)});
        edtBod.setText(cursor.getString(2));
        edtJamLahir.setText(cursor.getString(3));
        edtEmail.setText(cursor.getString(4));
        edtNama.setText(cursor.getString(1));
        cursor.moveToFirst();
        cursor.close();
        database.close();

        actionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                startActivity(intent);
            }
        });
    }
}
