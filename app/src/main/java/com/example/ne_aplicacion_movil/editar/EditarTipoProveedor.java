package com.example.ne_aplicacion_movil.editar;

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
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.List;

public class EditarTipoProveedor extends AppCompatActivity {

    EditText txtTipoProveedor;
    Button botonInsertarTipoProveedor,botonCancelarAnadirTipoProveedor;
    Spinner spinnerAnadirTipoProveedorER;

    TipoProveedores tipoProveedores;
    int id=0;
    boolean correcto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_tipo_proveedor);

        txtTipoProveedor=findViewById(R.id.textNombreTipoProveedor);
        botonInsertarTipoProveedor=findViewById(R.id.botonInsertarTipoProveedor);
        botonCancelarAnadirTipoProveedor=findViewById(R.id.botonCancelarAnadirTipoProveedor);
        spinnerAnadirTipoProveedorER=findViewById(R.id.spinnerAnadirTipoProveedorER);
        loadData();

        botonInsertarTipoProveedor.setVisibility(View.GONE);
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

        DbTipoProveedores aux=new DbTipoProveedores(EditarTipoProveedor.this);
        tipoProveedores= aux.verTipoProveedorSeleccionado(id);

        if(tipoProveedores!=null){
            txtTipoProveedor.setText(tipoProveedores.getNombre());
            botonInsertarTipoProveedor.setVisibility(View.VISIBLE);
        }

        botonInsertarTipoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtTipoProveedor.getText().toString().equals("")){
                    EstadoRegistro test= (EstadoRegistro) spinnerAnadirTipoProveedorER.getSelectedItem();
                    correcto=aux.actualizarRegistro(id,txtTipoProveedor.getText().toString(), test.getCodigo());

                    if(correcto){
                        Toast.makeText(EditarTipoProveedor.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(EditarTipoProveedor.this,"ERRO AL MODIFICAR REGISTRO",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarTipoProveedor.this,"DEBE LLENAR LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });

        botonCancelarAnadirTipoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadData(){
        DbTipoProveedores dbTipoProveedores=new DbTipoProveedores(EditarTipoProveedor.this);
        List<EstadoRegistro> estadoRegistrosList=dbTipoProveedores.findAllEstadoRegistro();
        if(!estadoRegistrosList.isEmpty()){
            spinnerAnadirTipoProveedorER.setAdapter(new EstadoRegistroAdapterSpinner(getApplicationContext(),R.layout.estado_registro_spinner_layout,estadoRegistrosList));
        }
    }
}