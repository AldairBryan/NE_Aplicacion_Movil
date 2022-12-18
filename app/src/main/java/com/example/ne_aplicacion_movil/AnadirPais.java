package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.adaptadores.ListaPaisesAdapter;
import com.example.ne_aplicacion_movil.adaptadoresSpinner.EstadoRegistroAdapterSpinner;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.db.DbProveedores;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;

import java.util.List;

public class AnadirPais extends AppCompatActivity {
    EditText txtPais;
    Button botonInsertarPais,botonCancelarAnadirPais;
    Spinner spinnerAnadirPaisER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_pais);
        Log.d("INFO","ENTRASTE");
        txtPais=findViewById(R.id.textNombrePais);
        botonInsertarPais=findViewById(R.id.botonInsertarPais);
        botonCancelarAnadirPais=findViewById(R.id.botonCancelarAnadirPais);
        spinnerAnadirPaisER=findViewById(R.id.spinnerAnadirPaisER);
        loadData();

        botonInsertarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbPaises dbPaises=new DbPaises(AnadirPais.this);
                EstadoRegistro test= (EstadoRegistro) spinnerAnadirPaisER.getSelectedItem();
                long id=dbPaises.insertarPais(txtPais.getText().toString(),test.getCodigo());
                if(id>0){
                    Toast.makeText(AnadirPais.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(AnadirPais.this,"ERROR AL GUARDAR REGISTRO",Toast.LENGTH_LONG).show();
                }
            }
        });
        botonCancelarAnadirPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void limpiar(){
        txtPais.setText("");
    }
    private void loadData(){
        DbPaises dbPaises=new DbPaises(AnadirPais.this);
        List<EstadoRegistro> estadoRegistrosList=dbPaises.findAllEstadoRegistro();
        if(!estadoRegistrosList.isEmpty()){
            spinnerAnadirPaisER.setAdapter(new EstadoRegistroAdapterSpinner(getApplicationContext(),R.layout.estado_registro_spinner_layout,estadoRegistrosList));
        }
    }
}