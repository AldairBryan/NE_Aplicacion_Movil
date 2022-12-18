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
import com.example.ne_aplicacion_movil.adaptadoresSpinner.PaisAdapterSpinner;
import com.example.ne_aplicacion_movil.adaptadoresSpinner.TipoProveedorAdapterSpinner;
import com.example.ne_aplicacion_movil.db.DbProveedores;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.Proveedores;
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.List;

public class EditarProveedor extends AppCompatActivity {

    EditText textNombreProveedor,textRUCProveedor;
    Button botonInsertarProveedor,botonCancelarAnadirProveedor;
    Spinner spinnerAnadirProveedorTipoP,spinnerAnadirProveedorPais,spinnerAnadirProveedorEstadoR;

    Proveedores proveedores;
    int id=0;
    boolean correcto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_proveedor);

        textNombreProveedor=findViewById(R.id.textNombreProveedor);
        textRUCProveedor=findViewById(R.id.textRUCProveedor);
        botonInsertarProveedor=findViewById(R.id.botonInsertarProveedor);
        botonCancelarAnadirProveedor=findViewById(R.id.botonCancelarAnadirProveedor);
        spinnerAnadirProveedorTipoP=findViewById(R.id.spinnerAnadirProveedorTipoP);
        spinnerAnadirProveedorPais=findViewById(R.id.spinnerAnadirProveedorPais);
        spinnerAnadirProveedorEstadoR=findViewById(R.id.spinnerAnadirProveedorEstadoR);
        loadData();

        botonInsertarProveedor.setVisibility(View.GONE);
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

        DbProveedores aux=new DbProveedores(EditarProveedor.this);
        proveedores= aux.verProveedorSeleccionado(id);

        if(proveedores!=null){
            textNombreProveedor.setText(proveedores.getNombre());
            textRUCProveedor.setText(proveedores.getRUC()+"");
            botonInsertarProveedor.setVisibility(View.VISIBLE);
        }

        botonInsertarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textNombreProveedor.getText().toString().equals("")  && !textRUCProveedor.getText().toString().equals("")){
                    EstadoRegistro er= (EstadoRegistro) spinnerAnadirProveedorEstadoR.getSelectedItem();
                    TipoProveedores tp= (TipoProveedores) spinnerAnadirProveedorTipoP.getSelectedItem();
                    Paises pas= (Paises) spinnerAnadirProveedorPais.getSelectedItem();

                    correcto=aux.actualizarRegistro(id,textNombreProveedor.getText().toString(),
                            Integer.parseInt(textRUCProveedor.getText().toString()),
                            tp.getId(), pas.getId(), er.getCodigo());

                    if(correcto){
                        Toast.makeText(EditarProveedor.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(EditarProveedor.this,"ERRO AL MODIFICAR REGISTRO",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarProveedor.this,"DEBE LLENAR LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });

        botonCancelarAnadirProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadData(){
        DbProveedores dbProveedores=new DbProveedores(EditarProveedor.this);

        List<TipoProveedores> tipoProveedoresList=dbProveedores.findAllTipoProveedores();
        if(!tipoProveedoresList.isEmpty()){
            spinnerAnadirProveedorTipoP.setAdapter(new TipoProveedorAdapterSpinner(getApplicationContext(),R.layout.informacion_spinner_layout,tipoProveedoresList));
        }

        List<Paises> paisesList= dbProveedores.findAllPaises();
        if(!paisesList.isEmpty()){
            spinnerAnadirProveedorPais.setAdapter(new PaisAdapterSpinner(getApplicationContext(),R.layout.informacion_spinner_layout,paisesList));
        }

        List<EstadoRegistro> estadoRegistrosList=dbProveedores.findAllEstadoRegistro();
        if(!estadoRegistrosList.isEmpty()){
            spinnerAnadirProveedorEstadoR.setAdapter(new EstadoRegistroAdapterSpinner(getApplicationContext(),R.layout.estado_registro_spinner_layout,estadoRegistrosList));
        }
    }
}