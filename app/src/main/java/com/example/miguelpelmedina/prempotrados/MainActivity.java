package com.example.miguelpelmedina.prempotrados;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.miguelpelmedina.prempotrados.Database.Agenda;
import com.example.miguelpelmedina.prempotrados.Database.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //input
    private EditText input;
    //list
    ArrayList<Usuario> arrayOfUsers;
    UsersAdapter adapter;
    private ListView list;
    //bd
    Agenda db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.InputPrinc);
        db = Agenda.getmInstance(this);

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
                case R.id.IdiomBtn:  ChangeLanguage(); break;
            }
    }
    /*
        METODOS DEL BOTÓN
     */

    private void ChangeLanguage() {
        Locale localizacion = new Locale("es", "ES");
        if(Locale.getDefault().equals(localizacion)){
            localizacion = new Locale("US", "US");
        }

        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    private void SearchContact() {

        adapter.clear();

        List<Usuario> NewUsers = db.Search(input.getText().toString().trim().toLowerCase());
        if(NewUsers.isEmpty()){
            Toast.makeText(this, R.string.errorNotFound, Toast.LENGTH_LONG).show();
        }else{
            adapter.addAll(NewUsers);
        }

    }

    private void AddContact() {
        Intent intent = new Intent(this, AddContactActivy.class);
        startActivity(intent);
    }


}
