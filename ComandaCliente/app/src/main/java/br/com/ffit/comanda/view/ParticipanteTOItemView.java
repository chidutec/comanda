package br.com.ffit.comanda.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.NumberFormat;

import br.com.ffit.comanda.to.ParticipanteTO;
import ffit.com.br.comanda.R;

@EViewGroup(R.layout.participante_item)
public class ParticipanteTOItemView extends LinearLayout{

    @ViewById
    TextView vwNomeParticipante;

    @ViewById
    TextView vwParcialParticipante;

    @ViewById
    ImageView imgFotoParticipante;

    public ParticipanteTOItemView(Context context){
        super(context);
    }

    public void bind(ParticipanteTO participanteTO){
        vwNomeParticipante.setText(participanteTO.getNome());
        Picasso.with(this.getContext()).load(participanteTO.getFotoUrl()).into(imgFotoParticipante);
        vwParcialParticipante.setText(NumberFormat.getCurrencyInstance().format(participanteTO.getParcial()));

    }

}
