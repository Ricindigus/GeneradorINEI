package pe.com.ricindigus.generadorinei.activities.creacion;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pe.com.ricindigus.generadorinei.R;
import pe.com.ricindigus.generadorinei.fragments.creacion.EventosFragment;
import pe.com.ricindigus.generadorinei.fragments.creacion.ModulosFragment;
import pe.com.ricindigus.generadorinei.fragments.creacion.PaginasFragment;
import pe.com.ricindigus.generadorinei.fragments.creacion.PreguntasFragment;

public class CrearEncuestaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_encuesta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ModulosFragment modulosFragment = new ModulosFragment(CrearEncuestaActivity.this);
        fragmentTransaction.replace(R.id.fragment_crear_encuesta, modulosFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.crear_encuesta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.volver_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_modulos) {
            ModulosFragment modulosFragment = new ModulosFragment(CrearEncuestaActivity.this);
            fragmentTransaction.replace(R.id.fragment_crear_encuesta, modulosFragment,"modulos");
        } else if (id == R.id.nav_paginas) {
            PaginasFragment paginasFragment = new PaginasFragment(CrearEncuestaActivity.this);
            fragmentTransaction.replace(R.id.fragment_crear_encuesta, paginasFragment,"paginas");
        } else if (id == R.id.nav_preguntas) {
            PreguntasFragment preguntasFragment = new PreguntasFragment(CrearEncuestaActivity.this);
            fragmentTransaction.replace(R.id.fragment_crear_encuesta, preguntasFragment,"preguntas");
        } else if (id == R.id.nav_eventos) {
            EventosFragment eventosFragment = new EventosFragment(CrearEncuestaActivity.this);
            fragmentTransaction.replace(R.id.fragment_crear_encuesta, eventosFragment,"eventos");
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
