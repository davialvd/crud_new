package optativa2.android.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText cod, nom, apel, cor, sem;
    Button agregar, consultar, eliminar, actualizar;
    TextView nombre, apellido, correo, semestre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias layout xml
        cod = findViewById(R.id.et1);
        nom = findViewById(R.id.et2);
        apel = findViewById(R.id.et3);
        cor = findViewById(R.id.et4);
        sem = findViewById(R.id.et5);

        nombre = findViewById(R.id.textView);
        apellido = findViewById(R.id.textView2);
        correo = findViewById(R.id.textView3);
        semestre = findViewById(R.id.textView4);

        agregar = findViewById(R.id.button5);
        consultar = findViewById(R.id.button6);
        eliminar = findViewById(R.id.button4);
        actualizar = findViewById(R.id.button7);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alta();
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consultar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Borrar();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        });
    }

    public void Consultar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //Lectura
        String codigo = cod.getText().toString();

        //Consulta
        Cursor fila = db.rawQuery("SELECT nombre, apellido, correo, semestre FROM usuarios WHERE codigo ="+codigo, null);

        if (fila.moveToFirst()){
            nom.setText(fila.getString(0));
            apel.setText(fila.getString(1));
            cor.setText(fila.getString(2));
            sem.setText(fila.getString(3));

            nombre.setText(fila.getString(0));
            apellido.setText(fila.getString(1));
            correo.setText(fila.getString(2));
            semestre.setText(fila.getString(3));
        } else {
            Toast.makeText(this, "No existe registro", Toast.LENGTH_LONG).show();
        }
    }

    //Registro
    public void Alta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = cod.getText().toString();
        String nombre = nom.getText().toString();
        String apellido = apel.getText().toString();
        String correo = cor.getText().toString();
        String semestre = sem.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("codigo", codigo);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("correo", correo);
        registro.put("semestre", semestre);

        db.insert("usuarios", null, registro);

        db.close();

        cod.setText("");
        nom.setText("");
        apel.setText("");
        cor.setText("");
        sem.setText("");

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    //Eliminar
    public void Borrar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = cod.getText().toString();

        int cant = db.delete("usuarios", "codigo = "+codigo,null);

        db.close();

        nom.setText("");
        apel.setText("");
        cor.setText("");
        sem.setText("");

        if (cant == 1){
            Toast.makeText(this, "Se elimino el registro correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe registro", Toast.LENGTH_LONG).show();
        }
    }

    public void Editar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = cod.getText().toString();
        String nombre = nom.getText().toString();
        String apellido = apel.getText().toString();
        String correo = cor.getText().toString();
        String semestre = sem.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("correo", correo);
        registro.put("semestre", semestre);

        int cant = db.update("usuarios", registro, "codigo="+codigo, null);

        db.close();

        nom.setText("");
        apel.setText("");
        cor.setText("");
        sem.setText("");

        if (cant == 1){
            Toast.makeText(this, "Se actualizo el registro correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe registro", Toast.LENGTH_LONG).show();
        }
    }
}