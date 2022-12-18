package com.example.ne_aplicacion_movil.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ne_aplicacion_movil.R;
import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;

import java.util.ArrayList;

public class ListaEstadoRegistroAdapter extends RecyclerView.Adapter<ListaEstadoRegistroAdapter.EstadoRegistroViewHolder> {

    ArrayList<EstadoRegistro> listaEstadoRegistro;
    private int checkedPosition=-1;
    public ListaEstadoRegistroAdapter(ArrayList<EstadoRegistro> listaEstadoRegistro){
        this.listaEstadoRegistro= listaEstadoRegistro;
    }

    @NonNull
    @Override
    public EstadoRegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_estado_registro,null,false);
        return new EstadoRegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstadoRegistroViewHolder holder, int position) {
        holder.viewCodigoEstadoRegistro.setText(listaEstadoRegistro.get(position).getCodigo());
        if(checkedPosition == -1){
            holder.imageCheck.setVisibility(View.GONE);
        } else {
            if (checkedPosition == holder.getAdapterPosition()){
                holder.imageCheck.setVisibility(View.VISIBLE);
            } else {
                holder.imageCheck.setVisibility(View.GONE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imageCheck.setVisibility(View.VISIBLE);
                if(checkedPosition != holder.getAdapterPosition()){
                    notifyItemChanged(checkedPosition);
                    checkedPosition= holder.getAdapterPosition();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEstadoRegistro.size();
    }

    public class EstadoRegistroViewHolder extends RecyclerView.ViewHolder {
        TextView viewCodigoEstadoRegistro;
        ImageView imageCheck;
        public EstadoRegistroViewHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigoEstadoRegistro=itemView.findViewById(R.id.viewCodigoEstadoRegistro);
            imageCheck=itemView.findViewById(R.id.listaItemEstadoRegistroCheck);

        }
    }

    public EstadoRegistro getSelected(){
        if(checkedPosition != -1){
            return listaEstadoRegistro.get(checkedPosition);
        }
        return null;
    }

}
