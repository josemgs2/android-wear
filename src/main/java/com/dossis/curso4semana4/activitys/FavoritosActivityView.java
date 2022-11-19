package com.dossis.curso4semana4.activitys;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.adapter.MascotaAdapter;
import com.dossis.curso4semana4.interfaces.IConfigRecyclerView;
import com.dossis.curso4semana4.interfaces.IFavoritosActivityView;
import com.dossis.curso4semana4.presenter.FavoritosPresenter;


public class FavoritosActivityView extends BaseActivity implements IFavoritosActivityView, IConfigRecyclerView {

    FavoritosPresenter presenter;
    private RecyclerView rvMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        if (toolbar == null) {
            setActionBar(this, true);
        }

        presenter = new FavoritosPresenter(this);

        configurarRecyclerView(null);

    }

    @Override
    public void configurarRecyclerView(View view) {
        rvMascotas = findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        presenter.crearAdapter();
    }

    @Override
    public void resultAdapter(MascotaAdapter adapter) {
        rvMascotas.setAdapter(adapter);
    }

}