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

import java.util.List;

public class PaisAdapterSpinner extends ArrayAdapter<Paises> {

    private int layout;
    private Context context;
    private List<Paises> paisesList;

    public PaisAdapterSpinner(@NonNull Context context, int resource, @NonNull List<Paises> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.paisesList=objects;
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
        Paises paises=paisesList.get(position);
        View view= LayoutInflater.from(context).inflate(layout,null);
        TextView textInfoIdSpinner = view.findViewById(R.id.textInfoIdSpinner);
        TextView textInfoNombreSpinner = view.findViewById(R.id.textInfoNombreSpinner);
        TextView textInfoERSpinner = view.findViewById(R.id.textInfoEstadoRegistroSpinner);
        textInfoIdSpinner.setText(paises.getId()+":");
        textInfoNombreSpinner.setText(paises.getNombre());
        textInfoERSpinner.setText(paises.getEstadoRegistro());
        return view;
    }
}
