package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ne_aplicacion_movil.adaptadores.ListaEstadoRegistroAdapter;
import com.example.ne_aplicacion_movil.adaptadores.ListaPaisesAdapter;
import com.example.ne_aplicacion_movil.adaptadores.ListaProveedoresAdapter;
import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.db.DbProveedores;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.Proveedores;
import com.example.ne_aplicacion_movil.utils.SpacingItemDecoder;

import java.util.ArrayList;

public class TablaProveedor extends AppCompatActivity {
    RecyclerView listaProveedor;
    ArrayList<Proveedores> listaArrayProveedor;
    Button botonTablaProveedorHabilitar,
            botonTablaProveedorInhabilitar,
            botonTablaProveedorEditar,
            botonTablaProveedorEliminar,
            botonTablaProveedorCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_proveedor);

        botonTablaProveedorHabilitar=findViewById(R.id.botonTablaProveedorHabilitar);
        botonTablaProveedorInhabilitar=findViewById(R.id.botonTablaProveedorInhabilitar);
        botonTablaProveedorEditar=findViewById(R.id.botonTablaProveedorEditar);
        botonTablaProveedorEliminar=findViewById(R.id.botonTablaProveedorEliminar);
        botonTablaProveedorCancelar=findViewById(R.id.botonTablaProveedorCancelar);

        listaProveedor=findViewById(R.id.listaProveedores);
        listaProveedor.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecoder= new SpacingItemDecoder(10);
        listaProveedor.addItemDecoration(itemDecoder);
        DbProveedores dbProveedores=new DbProveedores(TablaProveedor.this);
        listaArrayProveedor=new ArrayList<Proveedores>();
        ListaProveedoresAdapter adapter=new ListaProveedoresAdapter(dbProveedores.mostrarProveedores());
        listaProveedor.setAdapter(adapter);

        botonTablaProveedorHabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarDatos();
            }
        });

        botonTablaProveedorInhabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarDatos();
            }
        });

        botonTablaProveedorEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarDatos();
            }
        });

        botonTablaProveedorEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarDatos();
            }
        });

        botonTablaProveedorCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tabla_proveedor,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuProveedorAnadir:
                Log.d("INFO","ANADIR ESTADO REGISTRO");
                anadirRegistroProveedor();
                return true;
            default:
                Log.d("INFO","NO ENTRASTE PERRO");
                return super.onOptionsItemSelected(item);
        }
    }
    private void anadirRegistroProveedor(){
        Intent intent=new Intent(this, AnadirProveedor.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            actualizarDatos();
        }
    }

    private void actualizarDatos(){
        DbProveedores dbProveedores=new DbProveedores(TablaProveedor.this);
        ListaProveedoresAdapter adapter=new ListaProveedoresAdapter(dbProveedores.mostrarProveedores());
        listaProveedor.setAdapter(adapter);
    }
}