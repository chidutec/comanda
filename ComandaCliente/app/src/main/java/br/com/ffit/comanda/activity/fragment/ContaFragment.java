package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.com.ffit.comanda.activity.DashBoardClienteActivity_;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_conta)
public class ContaFragment extends Fragment {

    @ViewById(R.id.tabLayout)
    TabLayout tabLayout;

    @ViewById(R.id.contaContainer)
    ViewPager contaContainer;

    @AfterViews
    public void addTabs(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(((DashBoardClienteActivity_)getActivity()).getSupportFragmentManager());
        adapter.addFragment(ParticipantesFragment_.builder().build(), "Participantes");
        adapter.addFragment(MesaFragment_.builder().build(), "Mesa");
        adapter.addFragment(PedidoFragment_.builder().build(), "Pedido");
        contaContainer.setAdapter(adapter);
        tabLayout.setupWithViewPager(contaContainer);
        tabLayout.getTabAt(1).select();
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}


