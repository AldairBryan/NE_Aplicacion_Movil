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
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTipoProveedorAdapter extends RecyclerView.Adapter<ListaTipoProveedorAdapter.TipoProveedorViewHolder> {
    ArrayList<TipoProveedores> listaTipoProveedores;
    ArrayList<TipoProveedores> listaTipoProveedoresOriginal;

    private static int checkedPosition=-1;
    public ListaTipoProveedorAdapter(ArrayList<TipoProveedores> listaTipoProveedores){
        this.listaTipoProveedores= listaTipoProveedores;
        listaTipoProveedoresOriginal= new ArrayList<TipoProveedores>();
        listaTipoProveedoresOriginal.addAll(listaTipoProveedores);
    }

    @NonNull
    @Override
    public TipoProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_tipo_proveedor,null,false);
        return new ListaTipoProveedorAdapter.TipoProveedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipoProveedorViewHolder holder, int position) {
        holder.viewIdTipoProveedor.setText("ID: "+listaTipoProveedores.get(position).getId()+"");
        holder.viewNombreTipoProveedor.setText("Tipo Prov: " +listaTipoProveedores.get(position).getNombre());
        holder.viewEstadoRegistroTipoProveedor.setText("Estado R: "+listaTipoProveedores.get(position).getEstadoRegistro()+"");
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
        return listaTipoProveedores.size();
    }

    public class TipoProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdTipoProveedor,viewNombreTipoProveedor,viewEstadoRegistroTipoProveedor;
        ImageView imageCheck;
        public TipoProveedorViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdTipoProveedor=itemView.findViewById(R.id.viewIdTipoProveedor);
            viewNombreTipoProveedor=itemView.findViewById(R.id.viewNombreTipoProveedor);
            viewEstadoRegistroTipoProveedor=itemView.findViewById(R.id.viewERTipoProveedor);
            imageCheck=itemView.findViewById(R.id.listaItemTipoProveedorCheck);

        }
    }
    public TipoProveedores getSelected(){
        if(checkedPosition != -1){
            return listaTipoProveedores.get(checkedPosition);
        }
        return null;
    }

    public void filtrado(String txtBuscar){
        int longitud=txtBuscar.length();
        if(longitud==0){
            listaTipoProveedores.clear();
            listaTipoProveedores.addAll(listaTipoProveedoresOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<TipoProveedores> collection=listaTipoProveedores.stream()
                        .filter( i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaTipoProveedores.clear();
                listaTipoProveedores.addAll(collection);
            } else {
                for (TipoProveedores e:listaTipoProveedoresOriginal){
                    if(e.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaTipoProveedores.add(e);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
