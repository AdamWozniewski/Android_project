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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SQLiteDatabase db;
        db= openOrCreateDatabase("app",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(login VARCHAR,haslo VARCHAR,opis VARCHAR)");
        db.execSQL("INSERT INTO users (login,haslo,opis) VALUES('admin','admin','Lorem Ipsum')");
        db.execSQL("INSERT INTO users (login,haslo,opis) VALUES('ziomek1','ziomek1','Lorem Ipsum')");


        final EditText et_psswd=(EditText)findViewById(R.id.editText2);
        final EditText et_login=(EditText)findViewById(R.id.editText);
        Button btn_login=(Button)findViewById(R.id.button_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur =db.rawQuery("SELECT login,haslo FROM users",null);
                String[] array = new String[cur.getCount()];
//                int i=0;
                String uname;
                String uhaslo;

//                while(cur.moveToNext() && i< array.length){
//                    uname = cur.getString(cur.getColumnIndex("login"));
//                    uhaslo=cur.getString(cur.getColumnIndex("haslo"));
//
//                    if(uname.equals(et_login.getText().toString()) && uhaslo.equals(et_psswd.getText().toString())){
//                        Toast t=Toast.makeText(MainActivity.this,et_psswd.getText().toString(),500);
//                        t.show();
//                        break;
//                    }else {
//                        Toast e=Toast.makeText(MainActivity.this,"brak",500);
//                        e.show();
//                    }
//
//                    array[i] = uname;
//                    i++;
//                }
//                Toast w=Toast.makeText(MainActivity.this,"za pętlą",500);
//                w.show();

                Intent intent=new Intent(v.getContext(),prolif.class);
                startActivity(intent);

//                Intent intentDB=new Intent(v.getContext(),prolif.class);
                intent.putExtra("name_key","imie");
            }
        });

    }
}
