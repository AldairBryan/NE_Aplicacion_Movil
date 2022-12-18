package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.db.DbHelper;

public class MainActivity extends AppCompatActivity {
    Button btonCrear,btonConectar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btonCrear = findViewById(R.id.button_createBD);
        btonConectar = findViewById(R.id.button_connectBD);

        DbHelper dbHelper=new DbHelper(MainActivity.this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        btonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db!=null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS CREADA",Toast.LENGTH_LONG).show();
                }
            }
        });

        btonConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTablas= new Intent(getApplicationContext(), SeleccionarTabla.class);
                startActivity(intentTablas);
            }
        });
    }
}