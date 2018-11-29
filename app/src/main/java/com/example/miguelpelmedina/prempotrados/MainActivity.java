package com.example.miguelpelmedina.prempotrados;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.miguelpelmedina.prempotrados.Database.DatabaseHelper;
import com.example.miguelpelmedina.prempotrados.Database.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //input
    private EditText input;
    //list
    ArrayList<Usuario> arrayOfUsers;
    UsersAdapter adapter;
    private ListView list;
    //bd
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.InputPrinc);
        db = DatabaseHelper.getmInstance(this);

        arrayOfUsers = new ArrayList<Usuario>();
        adapter = new UsersAdapter(this, arrayOfUsers);
        list = findViewById(R.id.ContactList);
        list.setAdapter(adapter);
    }

    //BOTON PULSADO
    public void onClick(View view){
            switch (view.getId()){
                case R.id.Add_btn : AddContact(); break;
                case R.id.Search_btn: SearchContact(); break;
            }
    }
    /*
        METODOS DEL BOTÓN
     */

    private void SearchContact() {
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show();
        adapter.addAll(
                db.Search(
                        input.getText().toString().trim().toLowerCase()));
    }

    private void AddContact() {
        Intent intent = new Intent(this, AddContactActivy.class);
        startActivity(intent);
    }


}
