package br.com.ffit.comanda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.to.UsuarioItemTO;

public interface UsuarioItemTORepository extends Repository<Conta, Long> {
	
	String QUERY_FIND_ITEMS = "select new br.com.ffit.comanda.to.UsuarioItemTO(produto.preco, item.quantidade, ui.quantidade) "
			+ "from UsuarioItem as ui "
			+ "inner join ui.usuario as usuario "
			+ "inner join ui.item as item "
			+ "inner join item.produto as produto "
			+ "inner join item.conta as conta "
			+ "where usuario.id = :idUsuario "
			+ "and conta.id = :idConta ";
	
	@Query(value = QUERY_FIND_ITEMS)
	List<UsuarioItemTO> findAll(@Param("idConta") Long idConta, @Param("idUsuario") Long idUsuario);
}