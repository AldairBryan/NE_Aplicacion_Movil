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
import android.widget.Toast;

import com.example.ne_aplicacion_movil.adaptadores.ListaEstadoRegistroAdapter;
import com.example.ne_aplicacion_movil.adaptadores.ListaPaisesAdapter;
import com.example.ne_aplicacion_movil.adaptadores.ListaTipoProveedorAdapter;
import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.db.DbTipoProveedores;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;
import com.example.ne_aplicacion_movil.utils.SpacingItemDecoder;

import java.util.ArrayList;

public class TablaTipoProveedor extends AppCompatActivity {
    RecyclerView listaTipoProveedor;
    ArrayList<TipoProveedores> listaArrayTipoProveedor;
    Button botonTablaTipoProveedorHabilitar,
            botonTablaTipoProveedorInhabilitar,
            botonTablaTipoProveedorEditar,
            botonTablaTipoProveedorEliminar,
            botonTablaTipoProveedorCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_tipo_proveedor);

        botonTablaTipoProveedorHabilitar=findViewById(R.id.botonTablaTipoProveedorHabilitar);
        botonTablaTipoProveedorInhabilitar=findViewById(R.id.botonTablaTipoProveedorInhabilitar);
        botonTablaTipoProveedorEditar=findViewById(R.id.botonTablaTipoProveedorEditar);
        botonTablaTipoProveedorEliminar=findViewById(R.id.botonTablaTipoProveedorEliminar);
        botonTablaTipoProveedorCancelar=findViewById(R.id.botonTablaTipoProveedorCancelar);

        listaTipoProveedor=findViewById(R.id.listaTipoProveedores);
        listaTipoProveedor.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecoder= new SpacingItemDecoder(10);
        listaTipoProveedor.addItemDecoration(itemDecoder);
        DbTipoProveedores dbTipoProveedores=new DbTipoProveedores(TablaTipoProveedor.this);
        listaArrayTipoProveedor=new ArrayList<TipoProveedores>();
        ListaTipoProveedorAdapter adapter=new ListaTipoProveedorAdapter(dbTipoProveedores.mostrarTiposProveedores());
        listaTipoProveedor.setAdapter(adapter);

        botonTablaTipoProveedorHabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    Log.d("INFO",adapter.getSelected().toString());
                    dbTipoProveedores.habilitarRegistro(adapter.getSelected().getId());
                } else {
                    Toast.makeText(TablaTipoProveedor.this, "No selection",Toast.LENGTH_LONG).show();
                }

                actualizarDatos();
            }
        });

        botonTablaTipoProveedorInhabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    Log.d("INFO",adapter.getSelected().toString());
                    dbTipoProveedores.inhabilitarRegistro(adapter.getSelected().getId());
                } else {
                    Toast.makeText(TablaTipoProveedor.this, "No selection",Toast.LENGTH_LONG).show();
                }
                actualizarDatos();
            }
        });

        botonTablaTipoProveedorEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarDatos();
            }
        });

        botonTablaTipoProveedorEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    Log.d("INFO",adapter.getSelected().toString());
                    dbTipoProveedores.eliminarRegistro(adapter.getSelected().getId());
                } else {
                    Toast.makeText(TablaTipoProveedor.this, "No selection",Toast.LENGTH_LONG).show();
                }
                actualizarDatos();
            }
        });

        botonTablaTipoProveedorCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tabla_tipo_proveedor,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuTipoProveedorAnadir:
                Log.d("INFO","ANADIR ESTADO REGISTRO");
                anadirRegistroTipoProveedor();
                return true;
            default:
                Log.d("INFO","NO ENTRASTE PERRO");
                return super.onOptionsItemSelected(item);
        }
    }
    private void anadirRegistroTipoProveedor(){
        Intent intent=new Intent(this, AnadirTipoProveedor.class);
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
        DbTipoProveedores dbTipoProveedores=new DbTipoProveedores(TablaTipoProveedor.this);
        ListaTipoProveedorAdapter adapter=new ListaTipoProveedorAdapter(dbTipoProveedores.mostrarTiposProveedores());
        listaTipoProveedor.setAdapter(adapter);
    }
}