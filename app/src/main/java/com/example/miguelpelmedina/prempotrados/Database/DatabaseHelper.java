package com.example.miguelpelmedina.prempotrados.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME ="usuarios_db";

    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREA LA TABLA
        db.execSQL(Usuario.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Usuario.TABLE_NAME);


        //crea la tabla otra vez
        onCreate(db);
    }

    public List<Usuario> Search(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        List<Usuario> users = new ArrayList<>();
        //Query
        Cursor cursor =db.query(Usuario.TABLE_NAME,
                new String[]{Usuario.COLUMN_NAME, Usuario.COLUMN_TLF},
                Usuario.COLUMN_NAME +" LIKE ?",
                    new String[]{"%"+name+"name"},null,null,null,null);
        //recorremos los resultados y devolvemos la lista
        if(cursor.moveToFirst()){
            do{
                Usuario user = new Usuario();
                user.setName(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_NAME)));
                user.setTlf(cursor.getInt(cursor.getColumnIndex(Usuario.COLUMN_TLF)));
                users.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public void add(Usuario u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+Usuario.TABLE_NAME+" ("+Usuario.COLUMN_NAME+", "
                    +Usuario.COLUMN_TLF+") VALUES ( ?, ?)", new String [] {u.getName(),  String.valueOf(u.getTlf())});

        db.close();

    }

}
