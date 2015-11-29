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
import br.com.ffit.comanda.activity.fragment.ContaFragment_;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.view.ContaTOItemView;
import br.com.ffit.comanda.view.ContaTOItemView_;
import ffit.com.br.comanda.R;

@EBean
public class ContaTOListAdapter extends BaseAdapter {

    private List<ContaTO> contaTOs;

    @RootContext
    Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContaTOItemView contaTOItemView;
        if (convertView == null) {
            contaTOItemView = ContaTOItemView_.build(context);
        } else {
            contaTOItemView = (ContaTOItemView) convertView;
        }

        contaTOItemView.bind(getItem(position));

        contaTOItemView.setOnClickListener(item -> {
            FragmentManager fragmentManager = ((DashBoardClienteActivity) context).getFragmentManager();
            Fragment fragment = ContaFragment_.builder().contaTO(((ContaTOItemView) item).getContaTO()).build();
            fragmentManager.beginTransaction().replace(R.id.dashboardContainer, fragment).commit();
        });

        return contaTOItemView;
    }


    @Override
    public int getCount() {
        return contaTOs.size();
    }

    @Override
    public ContaTO getItem(int position) {
        return contaTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<ContaTO> getContaTOs() {
        return contaTOs;
    }

    public void setContaTOs(List<ContaTO> contaTOs) {
        this.contaTOs = contaTOs;
    }

}
