package com.dossis.curso4semana4.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.interfaces.IContactoActivity;
import com.dossis.curso4semana4.services.JavaMailAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ContactoActivity extends BaseActivity implements JavaMailAPI.OnTaskCompleted, IContactoActivity {

    TextView tvNombre;
    TextView tvEmail;
    TextView tvMensaje;
    View viewclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        if (toolbar == null) {
            setActionBar(this, true);
        }
        setupFab();

    }


    @Override
    public void onTaskCompleted(boolean result) {
        String mensaje;
        if (result) {
            mensaje = getString(R.string.mensaje_enviado);
        } else {
            mensaje = getString(R.string.mensaje_no_enviado);
        }
        Snackbar.make(viewclick, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


    }

    @Override
    public void setupFab() {
        tvMensaje = findViewById(R.id.tvMensaje);
        tvEmail = findViewById(R.id.tvEmail);
        tvNombre = findViewById(R.id.tvNombre);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            viewclick = view;
            String nombre = tvNombre.getText().toString();
            String email = tvEmail.getText().toString();
            String mensaje = tvMensaje.getText().toString();
            JavaMailAPI jm = new JavaMailAPI(ContactoActivity.this, email, getString(R.string.asunto_contacto) + nombre, mensaje);
            jm.execute();
        });
    }
}