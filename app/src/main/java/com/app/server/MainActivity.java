package com.app.server;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.server.models.Modelo;
import com.app.server.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    static ListAdapter listAdapter;
    List<Modelo> elementos = new ArrayList<Modelo>();
    //Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lanzar_lista();
        fab = (FloatingActionButton) findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Formulario.class));
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        lanzar_lista();
    }

    public void lanzar_lista(){
        RequestQueue queueVolley = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Utils.getUrlBase(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            elementos.clear();

                            System.out.println(response);
                            System.out.println("---------------------");

                            int size = response.getInt("size");
                            JSONObject canciones = response.getJSONObject("canciones");
                            for(int i = 0; i < size; i++){
                                JSONObject cancion = canciones.getJSONObject(Integer.toString(i));
                                elementos.add(new Modelo(cancion.getString("artista"), cancion.getString("cancion"), cancion.getString("duracion"), cancion.getString("productores")));
                            }
                            listAdapter.notifyDataSetChanged();
                            Utils.enviarToast("Lista de canciones recibidas", getApplicationContext());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Utils.enviarToast("Lista de canciones recibidas", getApplicationContext());
                        System.out.println(error.toString());
                    }
                }
        );
        queueVolley.add(request);

        listAdapter = new ListAdapter(elementos, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}