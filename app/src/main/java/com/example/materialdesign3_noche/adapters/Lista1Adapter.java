package com.example.materialdesign3_noche.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.materialdesign3_noche.MainActivity;
import com.example.materialdesign3_noche.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lista1Adapter extends RecyclerView.Adapter<Lista1Adapter.MyViewHolder> {

    ArrayList<String> listaPeliculas;
    Context context;

    public Lista1Adapter(Context context, ArrayList<String> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
    }

    @NonNull
    @Override
    public Lista1Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista1_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Lista1Adapter.MyViewHolder view, int position) {

        int escalafon = position + 1;
        String pelicula = this.listaPeliculas.get(position);
        descargarDatosPelicula(context, pelicula, view);

        if (escalafon < 10)
            view.escalafon.setText("0" + escalafon);
        else
            view.escalafon.setText(String.valueOf(escalafon));

    }

    private void descargarDatosPelicula(Context context, String pelicula, Lista1Adapter.MyViewHolder view) {

        String apikey = "b51ff18b"; //Solicita una por correo en www.omdbapi.com
        String url = "https://www.omdbapi.com/?t=" + pelicula + "&apikey=" + apikey;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try {
                    String titulo = res.getString("Title");
                    String imageUrl = res.getString("Poster");

                    view.titulo.setText(titulo);

                    Glide.with(context)
                            .load(imageUrl)
                            .apply(new RequestOptions())
                            .into(view.imagen);

                    // int idImage = context.getResources().getIdentifier(item.imagen, "mipmap", context.getPackageName());
                    // view.imagen.setImageResource(idImage);

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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView escalafon;
        ImageView imagen;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            escalafon = itemView.findViewById(R.id.escalafon);
            imagen = itemView.findViewById(R.id.imagen);

        }
    }

}
