package br.com.ffit.comanda.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;

import br.com.ffit.comanda.service.EstabelecimentoService;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_cadastro_produto)
public class CadastroProdutoActivity extends AppCompatActivity {

        @ViewById(R.id.inputNome)
        private EditText inputNome;

        @ViewById(R.id.inputDesc)
        private EditText inputDesc;

        @ViewById(R.id.inputPreco)
        private EditText inputPreco;

        @Bean
        EstabelecimentoService estabelecimentoService;

        @Click
        public void btnCadastrarProduto() {
            String nome = inputNome.getText().toString();
            String desc = inputDesc.getText().toString();
            BigDecimal preco = new BigDecimal(inputPreco.getText().toString());
            estabelecimentoService.inserirProduto(nome, desc, preco);
        }

}
