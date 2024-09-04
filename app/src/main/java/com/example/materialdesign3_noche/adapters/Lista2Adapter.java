package com.example.materialdesign3_noche.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.materialdesign3_noche.MovieActivity;
import com.example.materialdesign3_noche.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lista2Adapter extends RecyclerView.Adapter<Lista2Adapter.Peliculas> {

    Context context;
    ArrayList<String> listaPeliculas;

    public Lista2Adapter(Context context, ArrayList<String> listaPeliculas) {
        this.context = context;
        this.listaPeliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public Lista2Adapter.Peliculas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carrusel_item_layout, parent, false);
        return new Lista2Adapter.Peliculas(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Lista2Adapter.Peliculas view, int position) {

        String pelicula = this.listaPeliculas.get(position);
        descargarDatosPelicula(context, pelicula, view);

        view.itemView.setOnClickListener(v -> {
            Log.e("msg", view.json);

            Intent intent = new Intent(context, MovieActivity.class);
            intent.putExtra("detallePelicula", view.json);
            context.startActivity(intent);
        });

    }

    private void descargarDatosPelicula(Context context, String pelicula, Lista2Adapter.Peliculas view) {

        String apikey = "b51ff18b"; //Solicita una por correo en www.omdbapi.com
        String url = "https://www.omdbapi.com/?t=" + pelicula + "&apikey=" + apikey;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try {
                    //String titulo = res.getString("Title");
                    String imageUrl = res.getString("Poster");
                    //view.titulo.setText(titulo);

                    view.json = res.toString(); //guardamos el json como string para poder enviarlo a MovieActivity

                    Glide.with(context)
                            .load(imageUrl)
                            .apply(new RequestOptions())
                            .into(view.imagen);

                } catch (JSONException e) {
                    Log.e("msg", "Error la descargar los datos de la película: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error al obtener los datos de www.omdbapi.com", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    public int getItemCount() {
        return this.listaPeliculas.size();
    }

    public class Peliculas extends RecyclerView.ViewHolder {

        ImageView imagen;
        String json;

        public Peliculas(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagen);
            json = "";
        }
    }
}
