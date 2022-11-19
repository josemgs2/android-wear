package com.dossis.curso4semana4.adapter;

import com.dossis.curso4semana4.interfaces.IFirebaseEndpoint;
import com.dossis.curso4semana4.restApi.KeysFirebaseRestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseAdapter {
    public IFirebaseEndpoint establecerConexionRestAPI(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(KeysFirebaseRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IFirebaseEndpoint.class);
    }
}
