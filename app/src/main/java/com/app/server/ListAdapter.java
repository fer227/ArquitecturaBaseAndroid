package com.app.server;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.server.models.Modelo;

import org.w3c.dom.Text;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<Modelo> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapter(List<Modelo> elementos, Context context){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){ return datos.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflador.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(datos.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(datos.get(position).toString());
                Intent intent = new Intent(context, Detalle.class);
                intent.putExtra("id", position);
                context.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView artista, duracion, cancion;

        ViewHolder(View itemView){
            super(itemView);
            artista = itemView.findViewById(R.id.text_artista);
            cancion = itemView.findViewById(R.id.text_cancion);
            duracion = itemView.findViewById(R.id.text_duracion);
        }

        void bindData(final Modelo item){
            artista.setText(item.getArtista());
            cancion.setText(item.getCancion());
            duracion.setText(item.getDuracion());
        }
    }
}
