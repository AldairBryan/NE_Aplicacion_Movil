package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.adaptadoresSpinner.EstadoRegistroAdapterSpinner;
import com.example.ne_aplicacion_movil.adaptadoresSpinner.PaisAdapterSpinner;
import com.example.ne_aplicacion_movil.adaptadoresSpinner.TipoProveedorAdapterSpinner;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.db.DbProveedores;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.List;

public class AnadirProveedor extends AppCompatActivity {
    EditText txtProveedor,txtRUC;
    Button botonInsertarProveedor,botonCancelarAnadirProveedor;
    Spinner spinnerAnadirProveedorTipoProveedor,spinnerAnadirProveedorPais,spinnerAnadirProveedorER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_proveedor);
        txtProveedor=findViewById(R.id.textNombreProveedor);
        txtRUC=findViewById(R.id.textRUCProveedor);
        botonInsertarProveedor=findViewById(R.id.botonInsertarProveedor);
        botonCancelarAnadirProveedor=findViewById(R.id.botonCancelarAnadirProveedor);

        spinnerAnadirProveedorTipoProveedor=findViewById(R.id.spinnerAnadirProveedorTipoP);
        spinnerAnadirProveedorPais=findViewById(R.id.spinnerAnadirProveedorPais);
        spinnerAnadirProveedorER=findViewById(R.id.spinnerAnadirProveedorEstadoR);
        loadData();


        botonInsertarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbProveedores dbProveedores=new DbProveedores(AnadirProveedor.this);

                TipoProveedores tipoProveedoresInsertar= (TipoProveedores) spinnerAnadirProveedorTipoProveedor.getSelectedItem();
                Paises paisInsertar= (Paises) spinnerAnadirProveedorPais.getSelectedItem();
                EstadoRegistro estadoRegistroInsertar= (EstadoRegistro) spinnerAnadirProveedorER.getSelectedItem();

                long id=dbProveedores.insertarProveedor(txtProveedor.getText().toString(),Integer.parseInt(txtRUC.getText().toString()),tipoProveedoresInsertar.getId(),paisInsertar.getId(),estadoRegistroInsertar.getCodigo());
                if(id>0){
                    Toast.makeText(AnadirProveedor.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(AnadirProveedor.this,"ERROR AL GUARDAR REGISTRO",Toast.LENGTH_LONG).show();
                }
            }
        });
        botonCancelarAnadirProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    private void limpiar(){
        txtProveedor.setText("");
        txtRUC.setText("");
    }
    private void loadData(){
        DbProveedores dbProveedores=new DbProveedores(AnadirProveedor.this);

        List<TipoProveedores> tipoProveedoresList=dbProveedores.findAllTipoProveedores();
        if(!tipoProveedoresList.isEmpty()){
            spinnerAnadirProveedorTipoProveedor.setAdapter(new TipoProveedorAdapterSpinner(getApplicationContext(),R.layout.informacion_spinner_layout,tipoProveedoresList));
        }
        List<Paises> paisesList=dbProveedores.findAllPaises();
        if(!paisesList.isEmpty()){
            spinnerAnadirProveedorPais.setAdapter(new PaisAdapterSpinner(getApplicationContext(),R.layout.informacion_spinner_layout,paisesList));
        }

        List<EstadoRegistro> estadoRegistrosList=dbProveedores.findAllEstadoRegistro();
        if(!estadoRegistrosList.isEmpty()){
            spinnerAnadirProveedorER.setAdapter(new EstadoRegistroAdapterSpinner(getApplicationContext(),R.layout.estado_registro_spinner_layout,estadoRegistrosList));
        }

    }
}