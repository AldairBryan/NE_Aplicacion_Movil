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
import com.example.ne_aplicacion_movil.entidades.Proveedores;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListaProveedoresAdapter extends RecyclerView.Adapter<ListaProveedoresAdapter.ProveedoresViewHolder> {

    ArrayList<Proveedores> listaProveedores;
    private static int checkedPosition=-1;
    public ListaProveedoresAdapter(ArrayList<Proveedores> listaProveedores){
        this.listaProveedores= listaProveedores;
    }
    @NonNull
    @Override
    public ProveedoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_proveedor,null,false);
        return new ListaProveedoresAdapter.ProveedoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProveedoresViewHolder holder, int position) {
        holder.viewIdProveedor.setText("ID: "+listaProveedores.get(position).getId()+"");
        holder.viewNombreProveedor.setText("Proveedor: "+listaProveedores.get(position).getNombre());
        holder.viewRUCProveedor.setText("RUC: "+listaProveedores.get(position).getRUC()+"");
        holder.viewFKTPProveedor.setText("Tipo Prov: "+listaProveedores.get(position).getTipoProveedor()+"");
        holder.viewFKPaisProveedor.setText("Pais: "+listaProveedores.get(position).getPais()+"");
        holder.viewERProveedor.setText("Estado R: "+listaProveedores.get(position).getEstadoRegistro()+"");
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
        return listaProveedores.size();
    }

    public class ProveedoresViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdProveedor,viewNombreProveedor,viewRUCProveedor,viewFKTPProveedor,viewFKPaisProveedor,viewERProveedor;
        ImageView imageCheck;
        public ProveedoresViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdProveedor=itemView.findViewById(R.id.viewIdProveedor);
            viewNombreProveedor=itemView.findViewById(R.id.viewNombreProveedor);
            viewRUCProveedor=itemView.findViewById(R.id.viewRUCProveedor);
            viewFKTPProveedor=itemView.findViewById(R.id.viewFKTPProveedor);
            viewFKPaisProveedor=itemView.findViewById(R.id.viewFKPaisProveedor);
            viewERProveedor=itemView.findViewById(R.id.viewERProveedor);
            imageCheck=itemView.findViewById(R.id.listaItemProveedorCheck);

        }
    }

    public Proveedores getSelected(){
        if(checkedPosition != -1){
            return listaProveedores.get(checkedPosition);
        }
        return null;
    }
}
