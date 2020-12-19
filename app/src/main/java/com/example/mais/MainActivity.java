package com.example.mais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mais.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText etPassword;
    private Button btnNuevaCuenta;
    private Button btnAcceder;
    private SQLiteDatabase db;
    private VideoView videoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassword = (EditText)findViewById(R.id.etPassword);
        btnAcceder = (Button)findViewById(R.id.btnAcceder);
        btnNuevaCuenta = (Button)findViewById(R.id.btnNuevaCuenta);
        videoView2 = (VideoView)findViewById(R.id.videoView2);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.fondo);

        videoView2.setVideoURI(uri);
        videoView2.start();

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        db=openOrCreateDatabase("user", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(id NUMBER, nombre VARCHAR,pwd VARCHAR);");

        Cursor cursor = db.rawQuery("SELECT * FROM user",null);

        if(cursor.getCount()==0){
            Toast.makeText(this,"Necesitas crear usuario",Toast.LENGTH_LONG).show();
            openNewUser();
        }

        btnNuevaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Creando nuevo usuario",Toast.LENGTH_LONG).show();
                openNewUser();
            }
        });
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        User user = new User();
        if(etPassword.getText().length()>0){
            user.setPwd(etPassword.getText().toString());
            Cursor cursor = db.rawQuery("SELECT * FROM user WHERE pwd = '"+user.getPwd()+"';",null);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                user.setId(cursor.getInt((cursor.getColumnIndex("id"))));
                user.setNombre(cursor.getString((cursor.getColumnIndex("nombre"))));
                user.setPwd(cursor.getString((cursor.getColumnIndex("pwd"))));
                Toast.makeText(getApplicationContext(),"Datos recuperados",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,PsikoTropicActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);

            }else{
                etPassword.setError("Contraseña incorrecta");
            }
        }else{
            etPassword.setError("Introduzca la contraseña");
        }
    }

    private void openNewUser() {
        Intent intent=new Intent(this,NewUser.class);
        startActivity(intent);
    }
}
