package br.com.ffit.comanda.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import br.com.ffit.comanda.activity.fragment.ProdutoFragment;
import br.com.ffit.comanda.activity.fragment.ProdutoFragment_;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_dash_board_restaurante)
@OptionsMenu({R.menu.dash_board_restaurante})
public class DashBoardRestauranteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProdutoFragment.OnFragmentInteractionListener{

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Extra("estabelecimentoTO")
    EstabelecimentoTO estabelecimentoTO;

    @ViewById(R.id.navDrawerEmail)
    TextView navDrawerEmail;

    @ViewById(R.id.navDrawerNome)
    TextView navDrawerNome;


    @AfterViews
    public void afterViews() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navDrawerNome.setText(estabelecimentoTO.getNome());
        navDrawerEmail.setText(estabelecimentoTO.getLogin());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OptionsItem({R.id.action_settings})
    public void menuSettings() {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment selected = new Fragment();
        int id = item.getItemId();

        if (id == R.id.nav_produto) {
            selected = ProdutoFragment_.builder().estabelecimentoTO(estabelecimentoTO).build();
        } else if (id == R.id.nav_logout) {
            finish();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.dashboardContainer, selected)
                .commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(this, "Carregando Tela", Toast.LENGTH_SHORT).show();
    }
}
