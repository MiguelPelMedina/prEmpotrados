package com.example.miguelpelmedina.prempotrados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguelpelmedina.prempotrados.Database.Usuario;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Usuario> {

    public UsersAdapter(Context ctx, ArrayList<Usuario> users){
        super(ctx, 0,users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Usuario user = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user,parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.UserName);
        TextView phone = (TextView) convertView.findViewById(R.id.UserPhone);
        Button callbtn = (Button) convertView.findViewById(R.id.Call_btn);

        name.setText(user.getName());
        phone.setText(user.getTlf());
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"LLAMANDO",Toast.LENGTH_LONG).show();

            }
        });

        return convertView;
    }
}
