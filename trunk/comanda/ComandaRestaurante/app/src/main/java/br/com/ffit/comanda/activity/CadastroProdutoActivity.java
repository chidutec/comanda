package br.com.ffit.comanda.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;

import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.ProdutoTO;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_cadastro_produto)
public class CadastroProdutoActivity extends AppCompatActivity {

        @ViewById(R.id.inputNome)
        EditText inputNome;

        @ViewById(R.id.inputDesc)
        EditText inputDesc;

        @ViewById(R.id.inputPreco)
        EditText inputPreco;

        @Bean
        EstabelecimentoService estabelecimentoService;

        @Click
        public void btnCadastrarProduto() {
            ProdutoTO produtoTO = new ProdutoTO();
            produtoTO.setNome(inputNome.getText().toString());
            produtoTO.setDescricao(inputDesc.getText().toString());
            produtoTO.setPreco(new BigDecimal(inputPreco.getText().toString()));
            produtoTO.setIdEstabelecimento(1L);
            estabelecimentoService.inserirProduto(produtoTO);
        }

}
