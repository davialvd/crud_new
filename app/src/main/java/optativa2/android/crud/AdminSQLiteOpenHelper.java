package optativa2.android.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version ) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crearcion tabla usuarios(codigo, nombre, apellido, correo, semestre)
        db.execSQL("CREATE TABLE usuarios (codigo integer primary key, nombre text, apellido text, correo text, semestre text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("CREATE TABLE usuarios (codigo integer primary key, nombre text, apellido text, correo text, semestre text);");
    }
}
