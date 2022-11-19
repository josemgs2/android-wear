package com.dossis.curso4semana4.presenter;

import com.dossis.curso4semana4.Interactors.PerfilFragmentInteractor;
import com.dossis.curso4semana4.adapter.MascotaAdapter;
import com.dossis.curso4semana4.fragments.PerfilFragmentView;
import com.dossis.curso4semana4.interfaces.IPerfilFragmentPresenter;
import com.dossis.curso4semana4.pojo.Mascota;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {
    PerfilFragmentView perfilFragmentView;
    PerfilFragmentInteractor perfilFragmentInteractor;

    public PerfilFragmentPresenter(PerfilFragmentView perfilFragmentView) {
        this.perfilFragmentView = perfilFragmentView;
        perfilFragmentInteractor = new PerfilFragmentInteractor(this, this.perfilFragmentView.getContext());

        perfilFragmentInteractor.crearMascotaPerfilFake();

    }

    @Override
    public void crearAdapter() {

        perfilFragmentInteractor.crearAdapter();

    }

    @Override
    public void resultAdapter(MascotaAdapter adapter) {
        perfilFragmentView.resultAdapter(adapter);
    }


    @Override
    public void getMascotaPerfil(Mascota mascota) {
        perfilFragmentView.getNombreMiMascota(mascota.getNombre());
        perfilFragmentView.getIdFotoMiMascota(mascota.getIdFoto());

    }


}
