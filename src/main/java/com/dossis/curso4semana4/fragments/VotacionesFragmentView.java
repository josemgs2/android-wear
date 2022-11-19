package com.dossis.curso4semana4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.adapter.MascotaAdapter;
import com.dossis.curso4semana4.interfaces.IConfigRecyclerView;
import com.dossis.curso4semana4.interfaces.IVotacionesFragmentView;
import com.dossis.curso4semana4.presenter.VotacionesFragmentPresenter;

public class VotacionesFragmentView extends Fragment implements IVotacionesFragmentView, IConfigRecyclerView {

    VotacionesFragmentPresenter presenter;
    private RecyclerView rvMascotas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View viewInflated = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        presenter = new VotacionesFragmentPresenter(this);

        configurarRecyclerView(viewInflated);

        return viewInflated;
    }

    @Override
    public void configurarRecyclerView(View view) {
        rvMascotas = view.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        presenter.crearAdapter();
    }


    @Override
    public void resultAdapter(MascotaAdapter adapter) {
        rvMascotas.setAdapter(adapter);
    }

}
