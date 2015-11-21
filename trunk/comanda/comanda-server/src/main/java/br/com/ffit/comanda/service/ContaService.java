package br.com.ffit.comanda.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Item;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.repository.ContaRepository;
import br.com.ffit.comanda.repository.EstabelecimentoRepository;
import br.com.ffit.comanda.repository.ItemRepository;
import br.com.ffit.comanda.repository.UsuarioRepository;
import br.com.ffit.comanda.to.AbrirContaTO;


@Service
public class ContaService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	public List<Item> findItensById(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		return itemRepository.findByConta(conta);
	}

	public void abrirConta(AbrirContaTO abrirContaTO) {
		Usuario usuario = usuarioRepository.findById(abrirContaTO.getIdUsuario());
		Estabelecimento estabelecimento = estabelecimentoRepository.findById(abrirContaTO.getIdEstabelecimento());
		
		Conta conta = new Conta();
		conta.setDataAbertura(new Date());
		conta.setEstabelecimento(estabelecimento);
		
		//Adicionando por agora o usuario que criou a conta apenas
		Set<Usuario> usuarios = new HashSet<Usuario>();
		usuarios.add(usuario);
		
		conta.setUsuarios(usuarios);
		contaRepository.save(conta);
	}

}
