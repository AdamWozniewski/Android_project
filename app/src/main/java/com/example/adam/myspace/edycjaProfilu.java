package com.example.adam.myspace;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class edycjaProfilu extends AppCompatActivity {
    String toReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_profilu);

        final SQLiteDatabase db;
        db= openOrCreateDatabase("app",MODE_PRIVATE,null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS users(id_user INT,login VARCHAR,haslo VARCHAR,opis VARCHAR");

        Button btnPrev=(Button)findViewById(R.id.buttonPrev);
        Button btnSave=(Button)findViewById(R.id.buttonSAVE);

        final TextView imie=(TextView)findViewById(R.id.editText3);
        final TextView haslo=(TextView)findViewById(R.id.editText4);
        final TextView opis=(TextView)findViewById(R.id.editText5);
        final String id_login_s_s=getIntent().getStringExtra("to_edit");
        toReturn=id_login_s_s;
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE users SET login='"+ imie.getText().toString()+"'  WHERE id_user='"+id_login_s_s+"';");
                db.execSQL("UPDATE users SET haslo='"+ haslo.getText().toString()+"'  WHERE id_user='"+id_login_s_s+"';");
                db.execSQL("UPDATE users SET opis='"+ opis.getText().toString()+"'  WHERE id_user='"+id_login_s_s+"';");
//                global_login=id_login_s_s;
                Toast suc=Toast.makeText(edycjaProfilu.this,"Zapisano !",3000);
                suc.show();

                toReturn=id_login_s_s;
            }


        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_profil=new Intent(edycjaProfilu.this,prolif.class);
                intent_to_profil.putExtra("name_key",toReturn);
                startActivity(intent_to_profil);
            }
        });
    }
}
