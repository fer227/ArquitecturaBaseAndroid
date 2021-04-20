package com.app.server;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText productores   = (EditText) findViewById(R.id.text_productores);

        enviarVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        enviarRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}