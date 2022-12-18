package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.adaptadoresSpinner.EstadoRegistroAdapterSpinner;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;

import java.util.List;

public class EditarPais extends AppCompatActivity {

    EditText txtPais;
    Button botonInsertarPais,botonCancelarAnadirPais;
    Spinner spinnerAnadirPaisER;

    Paises pais;
    int id=0;
    boolean correcto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_pais);

        txtPais=findViewById(R.id.textNombrePais);
        botonInsertarPais=findViewById(R.id.botonInsertarPais);
        botonCancelarAnadirPais=findViewById(R.id.botonCancelarAnadirPais);
        spinnerAnadirPaisER=findViewById(R.id.spinnerAnadirPaisER);
        loadData();

        botonInsertarPais.setVisibility(View.GONE);
        if(savedInstanceState == null){
            Bundle extras= getIntent().getExtras();
            if(extras==null){
                id= Integer.parseInt(null);
            } else {
                id=extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbPaises aux=new DbPaises(EditarPais.this);
        pais= aux.verPaisSeleccionado(id);

        if(pais!=null){
            txtPais.setText(pais.getNombre());
            botonInsertarPais.setVisibility(View.VISIBLE);
        }

        botonInsertarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPais.getText().toString().equals("")){
                    EstadoRegistro test= (EstadoRegistro) spinnerAnadirPaisER.getSelectedItem();
                    correcto=aux.actualizarRegistro(id,txtPais.getText().toString(), test.getCodigo());

                    if(correcto){
                        Toast.makeText(EditarPais.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(EditarPais.this,"ERRO AL MODIFICAR REGISTRO",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarPais.this,"DEBE LLENAR LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });
        botonCancelarAnadirPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadData(){
        DbPaises dbPaises=new DbPaises(EditarPais.this);
        List<EstadoRegistro> estadoRegistrosList=dbPaises.findAllEstadoRegistro();
        if(!estadoRegistrosList.isEmpty()){
            spinnerAnadirPaisER.setAdapter(new EstadoRegistroAdapterSpinner(getApplicationContext(),R.layout.estado_registro_spinner_layout,estadoRegistrosList));
        }
    }

}