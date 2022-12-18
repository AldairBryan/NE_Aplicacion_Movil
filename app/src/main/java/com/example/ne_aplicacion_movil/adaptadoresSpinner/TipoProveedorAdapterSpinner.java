package com.example.ne_aplicacion_movil.adaptadoresSpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ne_aplicacion_movil.R;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.List;

public class TipoProveedorAdapterSpinner extends ArrayAdapter<TipoProveedores> {

    private int layout;
    private Context context;
    private List<TipoProveedores> tipoProveedoresList;

    public TipoProveedorAdapterSpinner(@NonNull Context context, int resource, @NonNull List<TipoProveedores> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.tipoProveedoresList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TipoProveedores tipoProveedores=tipoProveedoresList.get(position);
        View view= LayoutInflater.from(context).inflate(layout,null);
        TextView textInfoIdSpinner = view.findViewById(R.id.textInfoIdSpinner);
        TextView textInfoNombreSpinner = view.findViewById(R.id.textInfoNombreSpinner);
        TextView textInfoERSpinner = view.findViewById(R.id.textInfoEstadoRegistroSpinner);
        textInfoIdSpinner.setText(tipoProveedores.getId()+":");
        textInfoNombreSpinner.setText(tipoProveedores.getNombre());
        textInfoERSpinner.setText(tipoProveedores.getEstadoRegistro());
        return view;
    }
}
