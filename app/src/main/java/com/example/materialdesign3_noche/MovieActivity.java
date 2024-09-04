package com.example.materialdesign3_noche;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

public class MovieActivity extends AppCompatActivity {

    TextView titulo;
    ImageView imagen;
    TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        titulo = findViewById(R.id.titulo);
        imagen = findViewById(R.id.imagen);
        descripcion = findViewById(R.id.descripcion);

        Intent intent = getIntent();
        String detallePelicula = intent.getStringExtra("detallePelicula");

        InfoPelicula info = new Gson().fromJson(detallePelicula, InfoPelicula.class);

        titulo.setText(info.Title);
        descripcion.setText(info.Plot);
        Glide.with(this)
                .load(info.Poster)
                .apply(new RequestOptions())
                .into(imagen); //Establecer la imagen desde la URL en el ImageView
    }

    class InfoPelicula {
        String Title;
        String Poster;
        String Plot;

        public InfoPelicula(String titulo, String imagenUrl, String descripcion) {
            this.Title = titulo;
            this.Poster = imagenUrl;
            this.Plot = descripcion;
        }
    }
}