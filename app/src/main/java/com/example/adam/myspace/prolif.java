package com.example.adam.myspace;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class prolif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prolif);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        TextView tv=(TextView)findViewById(R.id.textView2);
//        tv.setText(getIntent().getStringExtra("name_key"));
        String name_key=getIntent().getStringExtra("name_key");


        Toast t=Toast.makeText(prolif.this,"Witaj "+name_key,5000);
        t.show();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edytuj swój profil", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

}
