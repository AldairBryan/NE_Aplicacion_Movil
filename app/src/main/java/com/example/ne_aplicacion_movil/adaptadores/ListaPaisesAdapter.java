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
import java.util.List;
import java.util.stream.Collectors;

public class ListaPaisesAdapter extends RecyclerView.Adapter<ListaPaisesAdapter.PaisViewHolder> {

    ArrayList<Paises> listaPaises;
    ArrayList<Paises> listaPaisesOriginal;

    private static int checkedPosition=-1;
    public ListaPaisesAdapter(ArrayList<Paises> listaPaises){
        this.listaPaises= listaPaises;
        listaPaisesOriginal= new ArrayList<Paises>();
        listaPaisesOriginal.addAll(listaPaises);
    }

    @NonNull
    @Override
    public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_pais,null,false);
        return new PaisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {
        holder.viewIdPais.setText("ID: "+listaPaises.get(position).getId()+"");
        holder.viewNombrePais.setText("Pais: "+listaPaises.get(position).getNombre());
        holder.viewEstadoRegistroPais.setText("Estado R: "+listaPaises.get(position).getEstadoRegistro()+"");

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
        return listaPaises.size();
    }

    public class PaisViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdPais,viewNombrePais,viewEstadoRegistroPais;
        ImageView imageCheck;
        public PaisViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdPais=itemView.findViewById(R.id.viewPaisId);
            viewNombrePais=itemView.findViewById(R.id.viewNombrePais);
            viewEstadoRegistroPais=itemView.findViewById(R.id.viewERPais);
            imageCheck=itemView.findViewById(R.id.listaItemPaisCheck);
        }
    }

    public Paises getSelected(){
        if(checkedPosition != -1){
            return listaPaises.get(checkedPosition);
        }
        return null;
    }

    public void filtrado(String txtBuscar){
        int longitud=txtBuscar.length();
        if(longitud==0){
            listaPaises.clear();
            listaPaises.addAll(listaPaisesOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Paises> collection=listaPaises.stream()
                        .filter( i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaPaises.clear();
                listaPaises.addAll(collection);
            } else {
                for (Paises e:listaPaisesOriginal){
                    if(e.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaPaises.add(e);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
