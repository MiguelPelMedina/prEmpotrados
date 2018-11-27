package com.example.miguelpelmedina.prempotrados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.InputPrinc);
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
    }

    private void AddContact() {
    }
}
