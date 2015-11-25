package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_conta)
public class ContaFragment extends Fragment {

    @ViewById(R.id.tabLayout)
    TabLayout tabLayout;

    @AfterViews
    public void addTabs(){

        TabLayout.Tab tabParticipantes = tabLayout.newTab().setText("Participantes");
        tabParticipantes.setText("Participantes");

        TabLayout.Tab tabMesa = tabLayout.newTab().setText("Mesa");
        tabMesa.setText("Mesa");

        TabLayout.Tab tabPedido = tabLayout.newTab().setText("Pedido");
        tabPedido.setText("Pedido");

        tabLayout.addTab(tabParticipantes);
        tabLayout.addTab(tabMesa);
        tabLayout.addTab(tabPedido);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    Fragment fragment = ParticipantesFragment_.builder().build();
                    fragmentManager.beginTransaction().replace(R.id.contaContainer, fragment).commit();
                } else if (tab.getPosition() == 1) {
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    Fragment fragment = MesaFragment_.builder().build();
                    fragmentManager.beginTransaction().replace(R.id.contaContainer, fragment).commit();
                } else if (tab.getPosition() == 2) {
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    Fragment fragment = PedidoFragment_.builder().build();
                    fragmentManager.beginTransaction().replace(R.id.contaContainer, fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabMesa.select();
    }

}


