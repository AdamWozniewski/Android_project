package com.example.adam.myspace;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static String global_login="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase db;
        db= openOrCreateDatabase("app",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(id_user INT,login VARCHAR,haslo VARCHAR,opis VARCHAR)");
        db.execSQL("INSERT INTO users (id_user,login,haslo,opis) VALUES(1,'admin','admin','Lorem Ipsum')");
        db.execSQL("INSERT INTO users (id_user,login,haslo,opis) VALUES(2,'ziomek1','ziomek1','Lorem Ipsum')");


        final EditText et_psswd=(EditText)findViewById(R.id.editText2);
        final EditText et_login=(EditText)findViewById(R.id.editText);
        Button btn_login=(Button)findViewById(R.id.button_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur =db.rawQuery("SELECT login,haslo,id_user FROM users",null);

                String uname;
                String uhaslo;
                boolean stop=false;
                while(cur.moveToNext() && stop==false){
                    uname = cur.getString(cur.getColumnIndex("login"));
                    uhaslo=cur.getString(cur.getColumnIndex("haslo"));
//
                    if(uname.equals(et_login.getText().toString()) && uhaslo.equals(et_psswd.getText().toString())){
                        Intent intent=new Intent(v.getContext(),prolif.class);

                        String idik=cur.getString(cur.getColumnIndex("id_user"));
                        intent.putExtra("name_key",idik);

                        startActivity(intent);
                        stop=true;
                        break;
                    }
                    else {
                        Toast e=Toast.makeText(MainActivity.this,"brak",500);
                        e.show();
                        break;
                    }
                }
            }
        });
    }
}
