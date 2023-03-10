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
import com.example.ne_aplicacion_movil.entidades.Proveedores;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaProveedoresAdapter extends RecyclerView.Adapter<ListaProveedoresAdapter.ProveedoresViewHolder> {

    ArrayList<Proveedores> listaProveedores;
    ArrayList<Proveedores> listaProveedoresOriginal;

    private static int checkedPosition=-1;
    public ListaProveedoresAdapter(ArrayList<Proveedores> listaProveedores){
        this.listaProveedores= listaProveedores;
        listaProveedoresOriginal= new ArrayList<Proveedores>();
        listaProveedoresOriginal.addAll(listaProveedores);
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
        holder.viewFKTPProveedor.setText("ID: "+listaProveedores.get(position).getTipoProveedor()+
                "   Tipo Prov: "+listaProveedores.get(position).getTipoProveedorNombre()+"");
        holder.viewFKPaisProveedor.setText("ID: "+listaProveedores.get(position).getPais()+
                "   Pais: "+listaProveedores.get(position).getPaisNombre()+"");
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

    public void filtrado(String txtBuscar){
        int longitud=txtBuscar.length();
        if(longitud==0){
            listaProveedores.clear();
            listaProveedores.addAll(listaProveedoresOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Proveedores> collection=listaProveedores.stream()
                        .filter( i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaProveedores.clear();
                listaProveedores.addAll(collection);
            } else {
                for (Proveedores e:listaProveedoresOriginal){
                    if(e.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaProveedores.add(e);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
