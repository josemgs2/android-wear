package com.dossis.curso4semana4.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.database.TablaMascotas;
import com.dossis.curso4semana4.interfaces.IFirebaseEndpoint;
import com.dossis.curso4semana4.pojo.Mascota;
import com.dossis.curso4semana4.restApi.Keys;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    Context context;
    ArrayList<Mascota> mascotas;
    boolean permitirLike;
    boolean versionReducida;
    final String TAG = "MascotaAdapter";

    public MascotaAdapter(ArrayList<Mascota> mascotas, boolean permitirLike, boolean versionReducida, Context context) {

        this.context = context;
        this.mascotas = mascotas;
        this.permitirLike = permitirLike;
        this.versionReducida = versionReducida;

    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Infla el layout y lo pasará al viewholder para que él obtenga los views
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        //Asocia cada elemento de la lista con cada view.
        Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgFoto.setImageResource(mascota.getIdFoto());
        mascotaViewHolder.tvNombreMascota.setText(mascota.getNombre());
        mascotaViewHolder.tvContadorLikes.setText(String.valueOf(mascota.getLikes()));

        //Uso el mismo adaptador para todos los recyclers.
        //Si tiene URL es de el fragment de perfil.
        if (mascota.getUrl() != null) {
            Picasso.with(context)
                    .load(mascota.getUrl())
                    .placeholder(R.drawable.huella)
                    .into(mascotaViewHolder.imgFoto);
        } else {
            mascotaViewHolder.imgFoto.setImageResource(mascota.getIdFoto());
        }


        if (permitirLike) {
            mascotaViewHolder.imgHuesoBlanco.setVisibility(View.VISIBLE);
            mascotaViewHolder.imgHuesoBlanco.setOnClickListener(v -> {

                TablaMascotas tablaMascotas = new TablaMascotas(v.getContext());
                int likes = tablaMascotas.addLike(v.getContext(), mascota.getId());
                mascotas = tablaMascotas.getMascotasOrderedId(v.getContext());
                notifyDataSetChanged();
                registerAndSendLike(mascota.getNombre(), likes);


            });
        } else {
            mascotaViewHolder.imgHuesoBlanco.setVisibility(View.INVISIBLE);
        }
        if (versionReducida) {
            mascotaViewHolder.tvNombreMascota.setVisibility(View.GONE);
        }
    }

    public void registerAndSendLike(String nombreMascota, int likes) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        sendLike(token, nombreMascota, likes);

                    }
                });
    }

    @SuppressLint("LongLogTag")
    private void sendLike(String token, String nombreMascota, int likes) {
        Log.d(TAG, "enviarTokenRegistro token:" + token);
        FirebaseAdapter firebaseAdapter = new FirebaseAdapter();
        IFirebaseEndpoint firebaseDB = firebaseAdapter.establecerConexionRestAPI();
        String idIG = Keys.USER_ID;

        Call<Void> usuarioResponseCall = firebaseDB.sendLike(token, idIG, nombreMascota, likes);
        usuarioResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "OK");
                } else {
                    Log.d(TAG, "Error ");
                }
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error usuarioResponse", t);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mascotas == null) {
            return 0;
        } else {
            return mascotas.size();
        }
    }

    public static class MascotaViewHolder extends ViewHolder {
        private final ImageView imgFoto;
        private final ImageView imgHuesoBlanco;
        private final TextView tvNombreMascota;
        private final TextView tvContadorLikes;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            imgHuesoBlanco = itemView.findViewById(R.id.imgHuesoBlanco);
            tvNombreMascota = itemView.findViewById(R.id.tvNombreMascota);
            tvContadorLikes = itemView.findViewById(R.id.tvContadorLikes);
        }
    }

}
