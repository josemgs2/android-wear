package com.dossis.curso4semana4.presenter;

import com.dossis.curso4semana4.Interactors.VotacionesFragmentInteractor;
import com.dossis.curso4semana4.adapter.MascotaAdapter;
import com.dossis.curso4semana4.fragments.VotacionesFragmentView;
import com.dossis.curso4semana4.interfaces.IVotacionesFragmentPresenter;

public class VotacionesFragmentPresenter implements IVotacionesFragmentPresenter {


    VotacionesFragmentView votacionesFragmentView;
    VotacionesFragmentInteractor votacionesFragmentInteractor;

    public VotacionesFragmentPresenter(VotacionesFragmentView votacionesFragmentView) {
        this.votacionesFragmentView = votacionesFragmentView;
        votacionesFragmentInteractor = new VotacionesFragmentInteractor(this, this.votacionesFragmentView.getContext());
    }


    @Override
    public void crearAdapter() {
        votacionesFragmentInteractor.crearAdapter();
    }


    @Override
    public void resultAdapter(MascotaAdapter adapter) {
        votacionesFragmentView.resultAdapter(adapter);
    }
}
