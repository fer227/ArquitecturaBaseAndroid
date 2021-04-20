package com.app.server;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Formulario extends AppCompatActivity {

    String url = "";
    protected void enviarToast(String msg){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = null;
        toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

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
        EditText productores   = (EditText) findViewById(R.id.text_productores);

        RequestQueue queueVolley = Volley.newRequestQueue(this);
        enviarVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject parametros = new JSONObject();
                try {
                    parametros.put("artista", artista);
                    parametros.put("cancion", cancion);
                    parametros.put("duracion", duracion);
                    parametros.put("productores", productores);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parametros,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    enviarToast(response.getString("msg"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                enviarToast("No se pudo mandar la petición");
                            }
                        }
                );
                queueVolley.add(request);
                startActivity(new Intent(Formulario.this, MainActivity.class));
            }
        });

        enviarRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}