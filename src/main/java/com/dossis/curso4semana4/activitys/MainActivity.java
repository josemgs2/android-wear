package com.dossis.curso4semana4.activitys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.adapter.PageAdapter;
import com.dossis.curso4semana4.database.TablaMascotas;
import com.dossis.curso4semana4.fragments.PerfilFragmentView;
import com.dossis.curso4semana4.fragments.VotacionesFragmentView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (toolbar == null) {
            setActionBar(this, false);
        }

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();

        new TablaMascotas(this).crearTabla();


    }


    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new VotacionesFragmentView());
        fragments.add(new PerfilFragmentView());
        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));

        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.star);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.dog);
    }


}