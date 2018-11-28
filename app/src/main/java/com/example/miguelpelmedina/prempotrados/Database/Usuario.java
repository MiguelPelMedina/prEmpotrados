package com.example.miguelpelmedina.prempotrados.Database;

public class Usuario {

    public static final String TABLE_NAME = "Usuario";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TLF = "tlf";

    private String name;
    private int tlf;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                +COLUMN_NAME + "TEXT PRIMARY KEY,"
                +COLUMN_TLF + "INTEGER)";

    public Usuario(){

    }
    public Usuario(String n, int t){
        this.name = n; this.tlf = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }
}
