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

import com.example.ne_aplicacion_movil.anadir.AnadirPais;
import com.example.ne_aplicacion_movil.editar.EditarPais;
import com.example.ne_aplicacion_movil.R;
import com.example.ne_aplicacion_movil.adaptadores.ListaPaisesAdapter;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.utils.SpacingItemDecoder;

import java.util.ArrayList;

public class TablaPais extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView listaPaises;
    ArrayList<Paises> listaArrayPaises;
    Button botonTablaPaisHabilitar,
            botonTablaPaisInhabilitar,
            botonTablaPaisEditar,
            botonTablaPaisEliminar,
            botonTablaPaisCancelar;

    SearchView txtBuscarPais;
    ListaPaisesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_pais);

        txtBuscarPais=findViewById(R.id.txtBuscarPais);
        botonTablaPaisHabilitar=findViewById(R.id.botonTablaPaisHabilitar);
        botonTablaPaisInhabilitar=findViewById(R.id.botonTablaPaisInhabilitar);
        botonTablaPaisEditar=findViewById(R.id.botonTablaPaisEditar);
        botonTablaPaisEliminar=findViewById(R.id.botonTablaPaisEliminar);
        botonTablaPaisCancelar=findViewById(R.id.botonTablaPaisCancelar);

        listaPaises=findViewById(R.id.listaPaises);
        listaPaises.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecoder= new SpacingItemDecoder(10);
        listaPaises.addItemDecoration(itemDecoder);

        DbPaises dbPaises=new DbPaises(TablaPais.this);
        listaArrayPaises=new ArrayList<Paises>();
        adapter=new ListaPaisesAdapter(dbPaises.mostrarPaises());

        listaPaises.setAdapter(adapter);

        botonTablaPaisHabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.getSelected() != null){
                    Log.d("INFO",adapter.getSelected().toString());
                    dbPaises.habilitarRegistro(adapter.getSelected().getId());
                } else {
                    Toast.makeText(TablaPais.this, "No selection",Toast.LENGTH_LONG).show();
                }
                actualizarDatos();
            }
        });

        botonTablaPaisInhabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    dbPaises.inhabilitarRegistro(adapter.getSelected().getId());
                } else {
                    Toast.makeText(TablaPais.this, "No selection",Toast.LENGTH_LONG).show();
                }
                actualizarDatos();
            }
        });

        botonTablaPaisEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.getSelected() != null){
                    Log.d("INFO",adapter.getSelected().toString());
                    Context context= view.getContext();
                    Intent intent=new Intent(context, EditarPais.class);
                    intent.putExtra("ID",adapter.getSelected().getId());
                    startActivityForResult(intent,0);
                } else {
                    Toast.makeText(TablaPais.this, "No selection",Toast.LENGTH_LONG).show();
                }
            }
        });

        botonTablaPaisEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){

                    AlertDialog.Builder builder= new AlertDialog.Builder(TablaPais.this);
                    builder.setMessage("¿Desea eliminar este Pais?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(dbPaises.eliminarRegistro(adapter.getSelected().getId())){
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
                    Toast.makeText(TablaPais.this, "No selection",Toast.LENGTH_LONG).show();
                }
                actualizarDatos();
            }
        });

        botonTablaPaisCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtBuscarPais.setOnQueryTextListener(this);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tabla_pais,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuPaisAnadir:
                Log.d("INFO","ANADIR PAIS");
                anadirRegistroPais();
                return true;
            default:
                Log.d("INFO","NO ENTRASTE PERRO");
                return super.onOptionsItemSelected(item);
        }
    }
    private void anadirRegistroPais(){
        Intent intent=new Intent(this, AnadirPais.class);
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
        DbPaises dbPaises=new DbPaises(TablaPais.this);
        adapter=new ListaPaisesAdapter(dbPaises.mostrarPaises());
        listaPaises.setAdapter(adapter);
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