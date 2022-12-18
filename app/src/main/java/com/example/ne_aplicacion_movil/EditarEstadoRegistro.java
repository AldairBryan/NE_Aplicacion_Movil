package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;

public class EditarEstadoRegistro extends AppCompatActivity {

    EditText txtEstadoRegistro;
    Button botonInsertarEstadoRegistro,botonCancelarAnadirER;

    EstadoRegistro estadoRegistro;
    String codigo ="";
    boolean correcto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_estado_registro);

        txtEstadoRegistro=findViewById(R.id.textNombreEstadoRegistro);
        botonInsertarEstadoRegistro=findViewById(R.id.botonInsertarEstadoRegistro);
        botonCancelarAnadirER=findViewById(R.id.botonCancelarAnadirER);

        botonInsertarEstadoRegistro.setVisibility(View.GONE);
        if(savedInstanceState == null){
            Bundle extras= getIntent().getExtras();
            if(extras==null){
                codigo= null;
            } else {
                codigo=extras.getString("ID");
            }
        } else {
            codigo =savedInstanceState.getSerializable("ID")+"";
        }

        DbEstadoRegistro aux=new DbEstadoRegistro(EditarEstadoRegistro.this);
        estadoRegistro= aux.verEstadoRegistroSeleccionado(codigo);

        if(estadoRegistro!=null){
            txtEstadoRegistro.setText(estadoRegistro.getCodigo());
            botonInsertarEstadoRegistro.setVisibility(View.VISIBLE);
        }

        botonInsertarEstadoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtEstadoRegistro.getText().toString().equals("")){
                    correcto=aux.actualizarRegistro(txtEstadoRegistro.getText().toString(),codigo);

                    if(correcto){
                        Toast.makeText(EditarEstadoRegistro.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(EditarEstadoRegistro.this,"ERRO AL MODIFICAR REGISTRO",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarEstadoRegistro.this,"DEBE LLENAR LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });
        botonCancelarAnadirER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}