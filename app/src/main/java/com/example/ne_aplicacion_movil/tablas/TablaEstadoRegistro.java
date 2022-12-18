package com.example.ne_aplicacion_movil.tablas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.anadir.AnadirEstadoRegistro;
import com.example.ne_aplicacion_movil.editar.EditarEstadoRegistro;
import com.example.ne_aplicacion_movil.R;
import com.example.ne_aplicacion_movil.adaptadores.ListaEstadoRegistroAdapter;
import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.utils.SpacingItemDecoder;

import java.util.ArrayList;

public class TablaEstadoRegistro extends AppCompatActivity implements SearchView.OnQueryTextListener{
    RecyclerView listaEstadoRegistro;
    ArrayList<EstadoRegistro> listaArrayEstadoRegistro;

    Button botonTablaEstadoRegistroEditar,
            botonTablaEstadoRegistroEliminar,
            botonTablaEstadoRegistroCancelar;

    SearchView txtBuscarEstadoRegistro;
    ListaEstadoRegistroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_estado_registro);

        txtBuscarEstadoRegistro=findViewById(R.id.txtBuscarEstadoRegistro);
        botonTablaEstadoRegistroEditar=findViewById(R.id.botonTablaEstadoRegistroEditar);
        botonTablaEstadoRegistroEliminar=findViewById(R.id.botonTablaEstadoRegistroEliminar);
        botonTablaEstadoRegistroCancelar=findViewById(R.id.botonTablaEstadoRegistroCancelar);

        listaEstadoRegistro=findViewById(R.id.listaEstadoRegistro);
        listaEstadoRegistro.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecoder= new SpacingItemDecoder(10);
        listaEstadoRegistro.addItemDecoration(itemDecoder);
        DbEstadoRegistro dbEstadoRegistro=new DbEstadoRegistro(TablaEstadoRegistro.this);
        listaArrayEstadoRegistro=new ArrayList<EstadoRegistro>();
        adapter=new ListaEstadoRegistroAdapter(dbEstadoRegistro.mostrarEstadosRegistros());
        listaEstadoRegistro.setAdapter(adapter);


        botonTablaEstadoRegistroEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    Log.d("INFO",adapter.getSelected().toString());
                    Context context= view.getContext();
                    Intent intent=new Intent(context, EditarEstadoRegistro.class);
                    intent.putExtra("ID",adapter.getSelected().getCodigo());
                    startActivityForResult(intent,0);
                } else {
                    Toast.makeText(TablaEstadoRegistro.this, "No selection",Toast.LENGTH_LONG).show();
                }
            }
        });

        botonTablaEstadoRegistroEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    AlertDialog.Builder builder= new AlertDialog.Builder(TablaEstadoRegistro.this);
                    builder.setMessage("¿Desea eliminar este Estado de Registro?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(dbEstadoRegistro.eliminarRegistro(adapter.getSelected().getCodigo())){
                                        actualizarDatos();
                                    }
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                } else {
                    Toast.makeText(TablaEstadoRegistro.this, "No selection",Toast.LENGTH_LONG).show();
                }
                actualizarDatos();
            }
        });

        botonTablaEstadoRegistroCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtBuscarEstadoRegistro.setOnQueryTextListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tabla_estado_registro,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuEstadoRegistroAnadir:
                Log.d("INFO","ANADIR ESTADO REGISTRO");
                anadirRegistroEstadoRegistro();
                return true;
            default:
                Log.d("INFO","NO ENTRASTE PERRO");
                return super.onOptionsItemSelected(item);
        }
    }
    private void anadirRegistroEstadoRegistro(){
        Intent intent=new Intent(this, AnadirEstadoRegistro.class);
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
        DbEstadoRegistro dbEstadoRegistro=new DbEstadoRegistro(TablaEstadoRegistro.this);
        adapter=new ListaEstadoRegistroAdapter(dbEstadoRegistro.mostrarEstadosRegistros());
        listaEstadoRegistro.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}