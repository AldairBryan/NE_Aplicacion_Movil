package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ne_aplicacion_movil.tablas.TablaEstadoRegistro;
import com.example.ne_aplicacion_movil.tablas.TablaPais;
import com.example.ne_aplicacion_movil.tablas.TablaProveedor;
import com.example.ne_aplicacion_movil.tablas.TablaTipoProveedor;

public class SeleccionarTabla extends AppCompatActivity {

    Button botonPais,botonTipoProveedor,botonProveedor,botonEstadoRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_tabla);

        botonPais= findViewById(R.id.botonTablaPaises);
        botonTipoProveedor= findViewById(R.id.botonTablaTipoP);
        botonProveedor= findViewById(R.id.botonTablaProveedores);
        botonEstadoRegistro=findViewById(R.id.botonTablaEstadoRegistro);

        botonEstadoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEstadoRegistro= new Intent(getApplicationContext(), TablaEstadoRegistro.class);
                startActivity(intentEstadoRegistro);
            }
        });
        botonPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTablaPais= new Intent(getApplicationContext(), TablaPais.class);
                startActivity(intentTablaPais);
            }
        });

        botonTipoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTipoProveedor= new Intent(getApplicationContext(), TablaTipoProveedor.class);
                startActivity(intentTipoProveedor);
            }
        });

        botonProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProveedor= new Intent(getApplicationContext(), TablaProveedor.class);
                startActivity(intentProveedor);
            }
        });
    }
}