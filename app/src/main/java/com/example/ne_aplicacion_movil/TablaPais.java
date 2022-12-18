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
import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.utils.SpacingItemDecoder;

import java.util.ArrayList;

public class TablaPais extends AppCompatActivity {

    RecyclerView listaPaises;
    ArrayList<Paises> listaArrayPaises;
    Button botonTablaPaisHabilitar,
            botonTablaPaisInhabilitar,
            botonTablaPaisEditar,
            botonTablaPaisEliminar,
            botonTablaPaisCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_pais);

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
        ListaPaisesAdapter adapter=new ListaPaisesAdapter(dbPaises.mostrarPaises());

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

                actualizarDatos();
            }
        });

        botonTablaPaisEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != null){
                    dbPaises.eliminarRegistro(adapter.getSelected().getId());
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
        ListaPaisesAdapter adapter=new ListaPaisesAdapter(dbPaises.mostrarPaises());
        listaPaises.setAdapter(adapter);
    }

}