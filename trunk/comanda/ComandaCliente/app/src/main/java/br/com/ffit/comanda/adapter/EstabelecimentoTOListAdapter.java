package br.com.ffit.comanda.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import br.com.ffit.comanda.activity.DashBoardClienteActivity;
import br.com.ffit.comanda.activity.fragment.ProdutoFragment_;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.UsuarioTO;
import br.com.ffit.comanda.view.EstabelecimentoTOItemView;
import br.com.ffit.comanda.view.EstabelecimentoTOItemView_;
import ffit.com.br.comanda.R;

@EBean
public class EstabelecimentoTOListAdapter extends BaseAdapter {

    private List<EstabelecimentoTO> estabelecimentoTOs;

    private UsuarioTO usuarioTO;

    @RootContext
    Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EstabelecimentoTOItemView estabelecimentoTOItemView;
        if (convertView == null) {
            estabelecimentoTOItemView = EstabelecimentoTOItemView_.build(context);
        } else {
            estabelecimentoTOItemView = (EstabelecimentoTOItemView) convertView;
        }

        estabelecimentoTOItemView.bind(getItem(position));

        estabelecimentoTOItemView.setOnClickListener(item -> {
            FragmentManager fragmentManager = ((DashBoardClienteActivity) context).getFragmentManager();
            Fragment fragment = ProdutoFragment_.builder().estabelecimentoTO(((EstabelecimentoTOItemView) item).getEstabelecimentoTO()).usuarioTO(usuarioTO).build();
            fragmentManager.beginTransaction().replace(R.id.dashboardContainer, fragment).commit();
        });

        return estabelecimentoTOItemView;
    }


    @Override
    public int getCount() {
        return estabelecimentoTOs.size();
    }

    @Override
    public EstabelecimentoTO getItem(int position) {
        return estabelecimentoTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<EstabelecimentoTO> getEstabelecimentoTOs() {
        return estabelecimentoTOs;
    }

    public void setEstabelecimentoTOs(List<EstabelecimentoTO> estabelecimentoTOs) {
        this.estabelecimentoTOs = estabelecimentoTOs;
    }

    public UsuarioTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UsuarioTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

}
