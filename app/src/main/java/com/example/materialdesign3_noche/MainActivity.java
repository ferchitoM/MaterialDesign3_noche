package com.example.materialdesign3_noche;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.materialdesign3_noche.adapters.Lista1Adapter;
import com.example.materialdesign3_noche.adapters.Lista2Adapter;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.UncontainedCarouselStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView lista1;
    Lista1Adapter adapter1;
    ArrayList<String> topPeliculas;

    RecyclerView lista2;
    Lista2Adapter adapter2;
    ArrayList<String> estrenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configuramos la lista de peliculas del top 10
        lista1 = findViewById(R.id.lista1);
        lista1.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topPeliculas = new ArrayList<>();
        topPeliculas.add("Furiosa: A Mad Max Saga"); //1
        topPeliculas.add("Kung Fu Panda 4"); //2
        topPeliculas.add("Godzilla x Kong"); //3
        topPeliculas.add("Alien romulus"); //4
        topPeliculas.add("Deadpool & Wolverine"); //5
        topPeliculas.add("Dune: Part Two"); //6
        topPeliculas.add("Kingdom of the Planet"); //7
        topPeliculas.add("The Fall Guy"); //8
        topPeliculas.add("Despicable Me 4"); //9
        topPeliculas.add("Madame Web"); //10

        adapter1 = new Lista1Adapter(this, topPeliculas);
        lista1.setAdapter(adapter1);


        //Configuramos la lista de películas en estreno
        lista2 = findViewById(R.id.lista2);
        lista2.setLayoutManager( new CarouselLayoutManager(new UncontainedCarouselStrategy()) ); //Generamos el carrusel

        estrenos = new ArrayList<>();
        estrenos.add("Crow");
        estrenos.add("Borderlands");
        estrenos.add("Jackpot");
        estrenos.add("Dune: Part Two");
        estrenos.add("Madame Web");
        estrenos.add("Deadpool & Wolverine");
        estrenos.add("Godzilla x Kong");
        estrenos.add("Kingdom of the Planet");
        estrenos.add("Furiosa: A Mad Max Saga");
        estrenos.add("Alien romulus");

        adapter2 = new Lista2Adapter(this, estrenos);
        lista2.setAdapter(adapter2);

    }


}