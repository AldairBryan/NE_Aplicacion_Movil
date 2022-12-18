package com.example.ne_aplicacion_movil.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ne_aplicacion_movil.R;
import com.example.ne_aplicacion_movil.entidades.Paises;

import java.util.ArrayList;

public class ListaPaisesAdapter extends RecyclerView.Adapter<ListaPaisesAdapter.PaisViewHolder> {

    ArrayList<Paises> listaPaises;
    private int checkedPosition=-1;
    public ListaPaisesAdapter(ArrayList<Paises> listaPaises){
        this.listaPaises= listaPaises;
    }

    @NonNull
    @Override
    public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_pais,null,false);
        return new PaisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {
        holder.viewIdPais.setText(listaPaises.get(position).getId()+"");
        holder.viewNombrePais.setText(listaPaises.get(position).getNombre());
        holder.viewEstadoRegistroPais.setText(listaPaises.get(position).getEstadoRegistro()+"");

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
}
