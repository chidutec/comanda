package br.com.ffit.comanda.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Usuario findByIdFacebook(Long idFacebook);

}
