package com.example.mincaturismo;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.squareup.picasso.Picasso;

import  java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.viewHolder>{

    ArrayList<PaqueteMinca> listaDeDatos;

    public Adaptador(ArrayList<PaqueteMinca> listaDeDatos) {
        this.listaDeDatos = listaDeDatos;
    }


    @NonNull
    @Override
    public Adaptador.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vistaItemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista,parent,false);
        return new viewHolder(vistaItemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.viewHolder holder, int position){
        holder.actualizarDatosDeItem(listaDeDatos.get(position));
    }

    @Override
    public int getItemCount(){
        return listaDeDatos.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView nombreTuristico,descripcion;
        ImageView fotoSitio;

        public viewHolder(@NonNull View itemView){
            super(itemView);
            nombreTuristico=itemView.findViewById(R.id.nombreTuristico);
            descripcion=itemView.findViewById(R.id.descripcion);
            fotoSitio=itemView.findViewById(R.id.fotoSitio);
        }

        public void actualizarDatosDeItem( PaqueteMinca turismo) {
            nombreTuristico.setText(turismo.getNombreTuristico());
            descripcion.setText(turismo.getDescripcion());

            Picasso.with(itemView.getContext())
                    .load(turismo.getFotoSitio())
                    .into(fotoSitio);
        }
    }
}