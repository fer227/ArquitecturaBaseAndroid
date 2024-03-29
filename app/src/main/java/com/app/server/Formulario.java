package com.app.server;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.server.api.RetrofitAPI;
import com.app.server.api.RetrofitService;
import com.app.server.models.Modelo;
import com.app.server.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Formulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Formulario");

        Button enviarVolley = (Button) findViewById(R.id.enviar_volley);
        Button enviarRetrofit = (Button) findViewById(R.id.enviar_retrofit);
        EditText artista   = (EditText) findViewById(R.id.text_artista);
        EditText cancion   = (EditText) findViewById(R.id.text_cancion);
        EditText duracion   = (EditText) findViewById(R.id.text_duracion);
        EditText productores   = (EditText) findViewById(R.id.detalle_productores);

        RequestQueue queueVolley = Volley.newRequestQueue(this);
        enviarVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject parametros = new JSONObject();
                try {
                    parametros.put("artista", artista.getText().toString());
                    parametros.put("cancion", cancion.getText().toString());
                    parametros.put("duracion", duracion.getText().toString());
                    parametros.put("productores", productores.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Utils.getUrlBase(), parametros,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Utils.enviarToast(response.getString("msg"), getApplicationContext());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Utils.enviarToast("No se pudo mandar la petición", getApplicationContext());
                            }
                        }
                );
                queueVolley.add(request);
                //startActivity(new Intent(Formulario.this, MainActivity.class));
                onBackPressed();
            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Utils.getUrlBase())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RetrofitAPI servicioAPI = retrofit.create(RetrofitAPI.class);

        enviarRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService.getInstance().postCancion(artista.getText().toString(), cancion.getText().toString(), duracion.getText().toString(), productores.getText().toString()).enqueue(
                        new Callback<Modelo>() {
                            @Override
                            public void onResponse(Call<Modelo> call, retrofit2.Response<Modelo> response) {
                                if(response.code() == 201){
                                    Utils.enviarToast(response.message(), getApplicationContext());
                                    onBackPressed();
                                }
                            }

                            @Override
                            public void onFailure(Call<Modelo> call, Throwable t) {
                                Utils.enviarToast("No se pudo mandar la petición", getApplicationContext());
                            }
                        }
                );
            }
        });
    }
}