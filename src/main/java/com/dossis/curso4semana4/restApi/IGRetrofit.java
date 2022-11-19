package com.dossis.curso4semana4.restApi;

import android.content.Context;
import android.util.Log;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.adapter.MascotaAdapter;
import com.dossis.curso4semana4.interfaces.IIGApi;
import com.dossis.curso4semana4.pojo.IGMediaItem;
import com.dossis.curso4semana4.pojo.Mascota;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IGRetrofit {

    Context context;
    public IGRetrofit(Context context) {
    this.context=context;
    }

    public void callMedia(MascotaAdapter adapter, ArrayList<Mascota> fotosPerfil) {
        try {

            Gson gsonMediaRecent = buildGsonDeserializeMediaRecent();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Keys.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonMediaRecent))
                    .build();


            IIGApi IIGApi = retrofit.create(IIGApi.class);
            Call<IGMediaResponse> call = IIGApi.getRecentMedia();


            call.enqueue(new Callback<IGMediaResponse>() {
                @Override
                public void onResponse(Call<IGMediaResponse> call, Response<IGMediaResponse> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error", "Error " + response.code());
                    //    Toast.makeText(context, "Error response is not successful", Toast.LENGTH_SHORT).show();

                    } else {

                        ArrayList<IGMediaItem> newIGMediaItems = new ArrayList<>();
                        IGMediaResponse IGMediaResponse = response.body();
                        newIGMediaItems = IGMediaResponse.getMediaItems();


                        for (int i = 0; i < newIGMediaItems.size(); i++) {
                            Mascota nuevaMascota = new Mascota();
                            IGMediaItem item = newIGMediaItems.get(i);
                            nuevaMascota.setId(i + 1);
                            nuevaMascota.setLikes(item.getLikes());
                            nuevaMascota.setNombre("nombre " + nuevaMascota.getId());
                            nuevaMascota.setUrl(item.getUrlPetPic());
                            nuevaMascota.setIdFoto(R.drawable.huella);

                            fotosPerfil.add(nuevaMascota);
                        }

                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<IGMediaResponse> call, Throwable t) {
                    Log.e("Error", "Error ", t);
                  //  Toast.makeText(context, "Error onFailure", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.e("Error", "Error ", e);
          //  Toast.makeText(context, "Error Exception", Toast.LENGTH_SHORT).show();
        }
    }

    public Gson buildGsonDeserializeMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(IGMediaResponse.class, new IGMediaDeserialyzer());
        return gsonBuilder.create();
    }
}
