package pnj.ac.id.tmjreg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String _NAMA_DATABASE = "db_mahasiswa";
    public static int _VERSION = 1;

    public static String _TABLE_MAHASISWA = "tb_mahasiswa";
    public static String _COLUMN_ID = "id";
    public static String _COLUMN_NAMA = "nama";
    public static String _COLUMN_EMAIL = "email";
    public static String _COLUMN_JURUSAN = "jurusan";
    public static String _COLUMN_KELAS = "kelas";
    public static String _COLUMN_TANGGAL_LAHIR = "tanggal_lahir";
    public static String _COLUMN_JAM_LAHIR = "jam_lahir";

    public DatabaseHelper(@Nullable Context context) {
        super(context, _NAMA_DATABASE, null, _VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+_TABLE_MAHASISWA+" ("+_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                _COLUMN_NAMA+" TEXT," +
                _COLUMN_EMAIL+" TEXT," +
                _COLUMN_JURUSAN+" TEXT," +
                _COLUMN_KELAS+" TEXT," +
                _COLUMN_TANGGAL_LAHIR+" TEXT," +
                _COLUMN_JAM_LAHIR+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
