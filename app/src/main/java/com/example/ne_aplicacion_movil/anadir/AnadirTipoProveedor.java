package com.example.ne_aplicacion_movil.anadir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.R;
import com.example.ne_aplicacion_movil.adaptadoresSpinner.EstadoRegistroAdapterSpinner;
import com.example.ne_aplicacion_movil.db.DbTipoProveedores;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;

import java.util.List;

public class AnadirTipoProveedor extends AppCompatActivity {
    EditText txtTipoProveedor;
    Button botonInsertarTipoProveedor,botonCancelarAnadirTipoProveedor;
    Spinner spinnerAnadirTipoProveedorER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_tipo_proveedor);
        txtTipoProveedor=findViewById(R.id.textNombreTipoProveedor);
        botonInsertarTipoProveedor=findViewById(R.id.botonInsertarTipoProveedor);
        botonCancelarAnadirTipoProveedor=findViewById(R.id.botonCancelarAnadirTipoProveedor);

        spinnerAnadirTipoProveedorER=findViewById(R.id.spinnerAnadirTipoProveedorER);
        loadData();

        botonInsertarTipoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbTipoProveedores dbTipoProveedores=new DbTipoProveedores(AnadirTipoProveedor.this);
                EstadoRegistro test= (EstadoRegistro) spinnerAnadirTipoProveedorER.getSelectedItem();
                long id=dbTipoProveedores.insertarTipoProveedor(txtTipoProveedor.getText().toString(),test.getCodigo());
                if(id>0){
                    Toast.makeText(AnadirTipoProveedor.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(AnadirTipoProveedor.this,"ERROR AL GUARDAR REGISTRO",Toast.LENGTH_LONG).show();
                }
            }
        });
        botonCancelarAnadirTipoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    private void limpiar(){
        txtTipoProveedor.setText("");
    }
    private void loadData(){
        DbTipoProveedores dbTipoProveedores=new DbTipoProveedores(AnadirTipoProveedor.this);
        List<EstadoRegistro> estadoRegistrosList=dbTipoProveedores.findAllEstadoRegistro();
        if(!estadoRegistrosList.isEmpty()){
            spinnerAnadirTipoProveedorER.setAdapter(new EstadoRegistroAdapterSpinner(getApplicationContext(),R.layout.estado_registro_spinner_layout,estadoRegistrosList));
        }
    }
}