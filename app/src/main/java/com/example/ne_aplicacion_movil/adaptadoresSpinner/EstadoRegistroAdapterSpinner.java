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

import java.util.List;

public class EstadoRegistroAdapterSpinner extends ArrayAdapter<EstadoRegistro> {
    private int layout;
    private Context context;
    private List<EstadoRegistro> estadoRegistroList;

    public EstadoRegistroAdapterSpinner(@NonNull Context context, int resource, @NonNull List<EstadoRegistro> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.estadoRegistroList=objects;
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
        EstadoRegistro estadoRegistro=estadoRegistroList.get(position);
        View view= LayoutInflater.from(context).inflate(layout,null);
        TextView textEstadoRegistroSpinner = view.findViewById(R.id.textEstadoRegistroSpinner);
        textEstadoRegistroSpinner.setText(estadoRegistro.getCodigo());
        return view;
    }
}
