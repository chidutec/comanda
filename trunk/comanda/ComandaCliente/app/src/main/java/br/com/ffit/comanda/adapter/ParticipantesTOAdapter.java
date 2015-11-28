package br.com.ffit.comanda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import br.com.ffit.comanda.to.ParticipanteTO;
import br.com.ffit.comanda.view.ParticipanteTOItemView;
import br.com.ffit.comanda.view.ParticipanteTOItemView_;

@EBean
public class ParticipantesTOAdapter extends BaseAdapter{

    private List<ParticipanteTO> participanteTOs;

    @RootContext
    Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ParticipanteTOItemView participanteTOItemView;
        if (convertView == null) {
            participanteTOItemView = ParticipanteTOItemView_.build(context);
        } else {
            participanteTOItemView = (ParticipanteTOItemView) convertView;
        }

        participanteTOItemView.bind(getItem(position));

        return participanteTOItemView;
    }


    @Override
    public int getCount() {
        return participanteTOs.size();
    }

    @Override
    public ParticipanteTO getItem(int position) {
        return participanteTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<ParticipanteTO> getParticipanteTOs() {
        return participanteTOs;
    }

    public void setParticipanteTOs(List<ParticipanteTO> participanteTOs) {
        this.participanteTOs = participanteTOs;
    }

}
