package com.example.usuariopc.basededatos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class AdminSqLiteOpenhelper  extends SQLiteOpenHelper{
    public AdminSqLiteOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
    BaseDeDatos.execSQL("create table articulos(codigo int  primary key, descripcion text, precio int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
