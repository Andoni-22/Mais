package com.example.mais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends AppCompatActivity {

    EditText etUsername;
    EditText etPwd;
    EditText etPwd2;
    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        btnCrear = (Button)findViewById(R.id.btnCrear);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPwd = (EditText)findViewById(R.id.etPwd);
        etPwd2 = (EditText)findViewById(R.id.etPwd2);




        btnCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = null;
                String user, pwd, pwd2;

                db = openOrCreateDatabase("user", Context.MODE_PRIVATE,null);

                int id;
                if(etUsername.getText().length()==0 || etPwd.getText().length()==0 || etPwd2.getText().length()==0){
                    if(etUsername.getText().length()==0){
                       etUsername.setError("No se puede dejar en blanco");
                    }
                    if(etPwd.getText().length()==0){
                       etPwd.setError("No se puede dejar en blanco");
                    }
                    if(etPwd2.getText().length()==0){
                       etPwd2.setError("No se puede dejar en blanco");
                    }
                }else{
                   user = etUsername.getText().toString();
                   pwd = etPwd.getText().toString();
                   pwd2 = etPwd2.getText().toString();

                   db = openOrCreateDatabase("user", Context.MODE_PRIVATE,null);
                   Cursor cursor = db.rawQuery("SELECT * FROM user",null);

                   id = cursor.getCount() + 1;

                    db.execSQL("INSERT INTO user VALUES('"+id+"','"+user+"','"+pwd+"')");
                    Toast.makeText(getApplicationContext(),"Datos añadidos",Toast.LENGTH_LONG).show();

                   if(!etPwd.getText().toString().equalsIgnoreCase(etPwd2.getText().toString())){
                       etPwd2.setError("No coinciden las contraseñas");
                   }
                }

            }
        });


    }
}
