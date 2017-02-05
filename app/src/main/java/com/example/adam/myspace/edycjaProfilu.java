package com.example.adam.myspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class edycjaProfilu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_profilu);


        Button btnPrev=(Button)findViewById(R.id.buttonPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_profil=new Intent(edycjaProfilu.this,prolif.class);
                startActivity(intent_to_profil);
            }
        });
    }
}
