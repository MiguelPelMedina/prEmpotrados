package com.example.miguelpelmedina.prempotrados;

import android.content.Intent;
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
    public void Add(){
        nameTxt = findViewById(R.id.NameInput);
        phoneTxt = findViewById(R.id.PhoneInput);
        String name = nameTxt.getText().toString().trim().toLowerCase();
        String numb = phoneTxt.getText().toString().toLowerCase();

        //METER EN LA BD
        db.add(new Usuario(name,numb));
        Toast.makeText(this,"PA ENTRO", Toast.LENGTH_LONG).show();

        String prueb ="";
            for (Usuario u : db.Search(name)) {
                prueb+= u.getName();
            }
        Toast.makeText(this, prueb, Toast.LENGTH_LONG).show();


    }
    public void Back(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
