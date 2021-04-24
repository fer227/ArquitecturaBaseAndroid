package com.app.server;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.server.api.RetrofitAPI;
import com.app.server.api.RetrofitService;
import com.app.server.models.Modelo;
import com.app.server.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        int id = getIntent().getExtras().getInt("id");

        TextView artista = findViewById(R.id.detalle_artista);
        TextView cancion = findViewById(R.id.detalle_cancion);
        TextView duracion = findViewById(R.id.detalle_productores);
        TextView productores = findViewById(R.id.detalle_productores);
        FloatingActionButton fab = findViewById(R.id.fab_delete);

        RetrofitService.getInstance().getCancion(id).enqueue(new Callback<Modelo>() {
            @Override
            public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                System.out.println(response.body());
                if(response.code() == 200){
                    Modelo modelo = response.body();
                    artista.setText(modelo.getArtista());
                    cancion.setText(modelo.getCancion());
                    duracion.setText(modelo.getDuracion());
                    productores.setText(modelo.getProductores());
                }
            }

            @Override
            public void onFailure(Call<Modelo> call, Throwable t) {
                Utils.enviarToast("No se pudo recibir los datos", getApplicationContext());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Detalle.this);
                builder.setMessage("¿Seguro que quieres eliminar esta canción del catálogo?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RetrofitService.getInstance().deleteCancion(id).enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Utils.enviarToast("Canción eliminada", Detalle.this);
                                        MainActivity.listAdapter.notifyDataSetChanged();
                                        Detalle.this.onBackPressed();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Utils.enviarToast("La canción no se pudo eliminar", Detalle.this);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}