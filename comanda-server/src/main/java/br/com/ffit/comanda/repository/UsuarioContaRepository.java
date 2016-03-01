package br.com.ffit.comanda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.model.UsuarioConta;

public interface UsuarioContaRepository extends CrudRepository<UsuarioConta, Long>{
	
	UsuarioConta findByUsuarioAndConta(Usuario usuario, Conta conta);
	
	List<UsuarioConta> findByContaAndDataFechamentoIsNull(Conta conta);
	
	List<UsuarioConta> findByUsuarioAndDataFechamentoIsNull(Usuario usuario);

}
