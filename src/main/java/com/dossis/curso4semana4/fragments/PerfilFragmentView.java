package com.dossis.curso4semana4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.adapter.MascotaAdapter;
import com.dossis.curso4semana4.interfaces.IConfigRecyclerView;
import com.dossis.curso4semana4.interfaces.IPerfilFragmentView;
import com.dossis.curso4semana4.presenter.PerfilFragmentPresenter;

public class PerfilFragmentView extends Fragment implements IPerfilFragmentView, IConfigRecyclerView {


    PerfilFragmentPresenter presenter;
    View viewInflater;
    RecyclerView rvPerfil;

    public PerfilFragmentView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewInflater = inflater.inflate(R.layout.fragment_perfil, container, false);

        presenter = new PerfilFragmentPresenter(this);

        configurarRecyclerView(viewInflater);


        return viewInflater;
    }


    public void resultAdapter(MascotaAdapter adapter) {
        rvPerfil.setAdapter(adapter);
    }

    @Override
    public void configurarRecyclerView(View view) {

        rvPerfil = view.findViewById(R.id.rvPerfil);
        GridLayoutManager glm = new GridLayoutManager(view.getContext(), 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPerfil.setLayoutManager(glm);
        presenter.crearAdapter();

    }

    @Override
    public void getNombreMiMascota(String nombre) {

        TextView tvNombreMascotaPerfil = viewInflater.findViewById(R.id.tvNombreMascotaPerfil);
        tvNombreMascotaPerfil.setText(nombre);
        tvNombreMascotaPerfil.setVisibility(View.VISIBLE);

    }

    @Override
    public void getIdFotoMiMascota(int idFoto) {

        ImageView imgFotoCircular = viewInflater.findViewById(R.id.imgFotoCircular);
        imgFotoCircular.setImageResource(idFoto);

    }

}