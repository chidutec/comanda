package br.com.ffit.comanda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.to.ParticipanteTO;

public interface ParticipanteTORespository extends Repository<Conta, Long>{
	
	String QUERY_FIND_ALL = "SELECT new br.com.ffit.comanda.to.ParticipanteTO(usuario.id, usuario.nome, usuario.fotoUrl) "
			+ "FROM UsuarioConta as usuarioConta "
			+ "INNER JOIN usuarioConta.conta as conta "
			+ "INNER JOIN usuarioConta.usuario as usuario "			
			+ "WHERE conta.id = :idConta ";
	
	@Query(value = QUERY_FIND_ALL)
	List<ParticipanteTO> findAll(@Param("idConta") Long idConta);
		
}
