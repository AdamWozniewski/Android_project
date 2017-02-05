package com.example.adam.myspace;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class prolif extends AppCompatActivity {
    private LocationManager locMag;
    private LocationListener locLis;
    private TextView textLocalisation;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prolif);

        final SQLiteDatabase db;
        db= openOrCreateDatabase("app",MODE_PRIVATE,null);
//        db.execSQL("SELECT login FROM users WHERE id_user=1");

//        ID z logowania

        final String id_login_s=getIntent().getStringExtra("name_key");

//        TextView tvLoginID=(TextView)findViewById(R.id.textViewID);
//        tvLoginID.setText(id_login_s);

        Cursor curs =db.rawQuery("SELECT * FROM users WHERE id_user='"+id_login_s+"'",null);
        curs.moveToFirst();
//  PROFIL
        TextView tvLogin=(TextView)findViewById(R.id.textViewImie);
        tvLogin.setText(curs.getString(curs.getColumnIndex("login")));

        TextView tvOpis=(TextView)findViewById(R.id.textViewOpiss);
        tvOpis.setText(curs.getString(curs.getColumnIndex("opis")));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.buttonFoto);
        img = (ImageView) findViewById(R.id.imageView);


        Toast t = Toast.makeText(prolif.this, "Witaj "+ id_login_s, 5000);

        if(id_login_s!=null){
            t.show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_image = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent_image, 0);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edytuj swój profil", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent_profil = new Intent(prolif.this, edycjaProfilu.class);
                intent_profil.putExtra("to_edit",id_login_s);
                startActivityForResult(intent_profil, 0);
            }
        });

        Button btnLogout=(Button)findViewById(R.id.button2);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(prolif.this, MainActivity.class);
                startActivity(logout);
            }
        });
//        LOKALIZACJA
        textLocalisation = (TextView) findViewById(R.id.textView6);
//        this.textLocalisation.setText("asfasfasfaf");

        locMag = (LocationManager) getSystemService(LOCATION_SERVICE);
        locLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                textLocalisation.append("\n " + location.getLongitude() + " " + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        configureLocalisation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configureLocalisation();
                break;
            default:
                break;
        }
    }

     void configureLocalisation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        locMag.requestLocationUpdates("gps", 5000, 0, locLis);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap b =(Bitmap)data.getExtras().get("data");
        img.setImageBitmap(b);
    }
}
