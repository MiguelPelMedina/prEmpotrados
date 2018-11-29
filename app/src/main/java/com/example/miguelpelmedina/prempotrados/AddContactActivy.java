package com.example.miguelpelmedina.prempotrados;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miguelpelmedina.prempotrados.Database.DatabaseHelper;
import com.example.miguelpelmedina.prempotrados.Database.Usuario;

public class AddContactActivy extends AppCompatActivity {

    EditText nameTxt;
    EditText phoneTxt;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_activy);

        db = DatabaseHelper.getmInstance(this);
    }

    public void OnClick(View view){
        switch (view.getId()){
            case R.id.Add_btn: Add(); break;
            case R.id.Back_btn: Back(); break;
        }
    }
    //ACIONES DEL BOTON
    public void Add(){
        //Obtengo los parámetros
        nameTxt = findViewById(R.id.NameInput);
        phoneTxt = findViewById(R.id.PhoneInput);
        String name = nameTxt.getText().toString().trim().toLowerCase();
        String numb = phoneTxt.getText().toString().toLowerCase();
        //compruebo que ninguno es vacio
        if(name.equals("") || numb.equals("")){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.error)
                    .setMessage(R.string.errorMessage)
                    .setPositiveButton("OK", null)
                    .show();
        }else {

            //METER EN LA BD
            try {
                db.add(new Usuario(name, numb));

            } catch (Error e) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.error)
                        .setMessage(R.string.errorMessage)
                        .setPositiveButton("OK", null)
                        .show();
            }
            String msg = getString(R.string.InsertMessage);
            Toast.makeText(this, msg+"["+name+"]", Toast.LENGTH_LONG).show();
        }

    }
    public void Back(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
