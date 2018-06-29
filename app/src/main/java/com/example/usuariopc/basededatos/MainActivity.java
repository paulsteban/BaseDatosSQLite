package com.example.usuariopc.basededatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText et_codigo, et_descripcion,et_precio;
    private ListView lista;
    public ArrayList<Articulos> arrayList = new ArrayList<Articulos>();
    ArrayAdapter<Articulos> adapter;
    Articulos articulos = new Articulos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_codigo =(EditText) findViewById(R.id.txtcodigo);
        et_descripcion =(EditText) findViewById(R.id.txtnombre);
        et_precio =(EditText) findViewById(R.id.txtprecio);
        lista = (ListView) findViewById(R.id.listaCosas);
        cargarlista();


    }

    public void Registar(View view){
        AdminSqLiteOpenhelper conector =new AdminSqLiteOpenhelper(this,"administracion", null,1);
        SQLiteDatabase BaseDeDatos = conector.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        if (!codigo.isEmpty()&& !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            BaseDeDatos.insert("articulos",null, registro);
            BaseDeDatos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            Toast.makeText(this,"registro compleatdo",Toast.LENGTH_LONG).show();
            cargarlista();

        }else{
            Toast.makeText(this,"debes llenar todos los datos",Toast.LENGTH_LONG).show();
        }
    }
    public void Buscar(View view){

        AdminSqLiteOpenhelper conector = new AdminSqLiteOpenhelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = conector.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        if (!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select descripcion, precio from articulos where codigo ="+codigo,null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                BaseDeDatos.close();

            }else {
                Toast.makeText(this,"El producto no existe",Toast.LENGTH_LONG).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this,"debes llenar el codigo",Toast.LENGTH_LONG).show();
        }
    }
    public void modificar(View view){
        AdminSqLiteOpenhelper conector = new AdminSqLiteOpenhelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = conector.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        if (!codigo.isEmpty()&& !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);
            int cantidad = BaseDeDatos.update("articulos",registro,"codigo="+codigo,null);
            BaseDeDatos.close();
            if (cantidad==1){
                    Toast.makeText(this,"update compleatdo",Toast.LENGTH_LONG).show();


            }else {
                Toast.makeText(this,"articulo no encontrado",Toast.LENGTH_LONG).show();

            }
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

        }else{
            Toast.makeText(this,"debes llenar todos los datos",Toast.LENGTH_LONG).show();
        }
    }
    public void eliminar(View view){
        AdminSqLiteOpenhelper conector = new AdminSqLiteOpenhelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = conector.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        if (!codigo.isEmpty()){
            int cantidad = BaseDeDatos.delete("articulos","codigo="+codigo,null);
            BaseDeDatos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            if (cantidad==1){
                Toast.makeText(this,"delete compleatdo",Toast.LENGTH_LONG).show();
                cargarlista();

            }else {
                Toast.makeText(this,"articulo no encontrado",Toast.LENGTH_LONG).show();

            }        }else {
            Toast.makeText(this,"debes poner el codigo",Toast.LENGTH_LONG).show();

        }
    }
    public void cargarlista(){
        AdminSqLiteOpenhelper conector = new AdminSqLiteOpenhelper(this,"administracion",null,1);

        SQLiteDatabase db = conector.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM articulos ", null);
        arrayList.clear();
        if (c.moveToFirst()){
            do {

           arrayList.add(new Articulos(c.getString(0),
                c.getString(1), c.getString(2)));

                // Passing values

                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, arrayList);
        lista.setAdapter(adapter);

    }


}
