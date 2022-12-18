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

import com.example.ne_aplicacion_movil.adaptadores.ListaEstadoRegistroAdapter;
import com.example.ne_aplicacion_movil.adaptadores.ListaPaisesAdapter;
import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.utils.SpacingItemDecoder;

import java.util.ArrayList;

public class TablaEstadoRegistro extends AppCompatActivity {
    RecyclerView listaEstadoRegistro;
    ArrayList<EstadoRegistro> listaArrayEstadoRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_estado_registro);
        listaEstadoRegistro=findViewById(R.id.listaEstadoRegistro);
        listaEstadoRegistro.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecoder= new SpacingItemDecoder(10);
        listaEstadoRegistro.addItemDecoration(itemDecoder);
        DbEstadoRegistro dbEstadoRegistro=new DbEstadoRegistro(TablaEstadoRegistro.this);
        listaArrayEstadoRegistro=new ArrayList<EstadoRegistro>();
        ListaEstadoRegistroAdapter adapter=new ListaEstadoRegistroAdapter(dbEstadoRegistro.mostrarEstadosRegistros());
        listaEstadoRegistro.setAdapter(adapter);
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
        ListaEstadoRegistroAdapter adapter=new ListaEstadoRegistroAdapter(dbEstadoRegistro.mostrarEstadosRegistros());
        listaEstadoRegistro.setAdapter(adapter);
    }
}