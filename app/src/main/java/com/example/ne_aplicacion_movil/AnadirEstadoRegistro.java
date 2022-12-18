package com.example.ne_aplicacion_movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ne_aplicacion_movil.db.DbEstadoRegistro;
import com.example.ne_aplicacion_movil.db.DbProveedores;

public class AnadirEstadoRegistro extends AppCompatActivity {
    EditText txtEstadoRegistro;
    Button botonInsertarEstadoRegistro,botonCancelarAnadirER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_estado_registro);
        txtEstadoRegistro=findViewById(R.id.textNombreEstadoRegistro);
        botonInsertarEstadoRegistro=findViewById(R.id.botonInsertarEstadoRegistro);
        botonCancelarAnadirER=findViewById(R.id.botonCancelarAnadirER);
        botonInsertarEstadoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEstadoRegistro dbEstadoRegistro=new DbEstadoRegistro(AnadirEstadoRegistro.this);
                long id=dbEstadoRegistro.insertarEstadoRegistro(txtEstadoRegistro.getText().toString());
                if(id>0){
                    Toast.makeText(AnadirEstadoRegistro.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(AnadirEstadoRegistro.this,"ERROR AL GUARDAR REGISTRO",Toast.LENGTH_LONG).show();
                }
            }
        });
        botonCancelarAnadirER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    private void limpiar(){
        txtEstadoRegistro.setText("");
    }
}