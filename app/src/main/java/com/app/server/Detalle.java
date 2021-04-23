package com.app.server;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.server.api.RetrofitAPI;
import com.app.server.models.Modelo;
import com.app.server.utils.Utils;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.getUrlBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI servicioAPI = retrofit.create(RetrofitAPI.class);

        servicioAPI.getCancion(id).enqueue(new Callback<Modelo>() {
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
    }
}