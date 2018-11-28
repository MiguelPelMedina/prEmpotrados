package com.example.miguelpelmedina.prempotrados.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;

    //Database version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME ="usuarios_db";

    private DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getmInstance(Context ctx){
        //Singleton
        if(mInstance == null){
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
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
                new String[]{"%"+name+"%"},null,null,null,null);
        //recorremos los resultados y devolvemos la lista

        //Cursor cursor = db.rawQuery("SELECT * FROM "+Usuario.TABLE_NAME,null);
        //Cursor cursor = db.query(Usuario.TABLE_NAME, new String [] {Usuario.COLUMN_NAME, Usuario.COLUMN_TLF},
        //                Usuario.COLUMN_NAME+"= ?",new String[]{name},null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                Usuario user = new Usuario();
                user.setName(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_NAME)));
                user.setTlf(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_TLF)));
                users.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public void add(Usuario u){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("INSERT INTO "+Usuario.TABLE_NAME+" ("+Usuario.COLUMN_NAME+", "
        //            +Usuario.COLUMN_TLF+") VALUES ( ?, ?)", new String [] {u.getName(),  String.valueOf(u.getTlf())});

        ContentValues valores = new ContentValues();
        valores.put(Usuario.COLUMN_NAME, u.getName());
        valores.put(Usuario.COLUMN_TLF,u.getTlf());
        db.insert(Usuario.TABLE_NAME,null,valores);

        db.close();

    }

}
