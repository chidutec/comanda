package br.com.ffit.comanda.service;

import java.math.BigDecimal;
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
import br.com.ffit.comanda.repository.ParticipanteTORespository;
import br.com.ffit.comanda.repository.UsuarioItemTORepository;
import br.com.ffit.comanda.repository.UsuarioRepository;
import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.to.ParticipanteTO;
import br.com.ffit.comanda.to.UsuarioItemTO;


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
	
	@Autowired
	private ParticipanteTORespository participanteTORespository;
	
	@Autowired
	private UsuarioItemTORepository usuarioItemTORepository;
	
	public List<Item> findItensById(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		return itemRepository.findByConta(conta);
	}

	public ContaTO abrirConta(AbrirContaTO abrirContaTO) {
		Usuario usuario = usuarioRepository.findById(abrirContaTO.getIdUsuario());
		Estabelecimento estabelecimento = estabelecimentoRepository.findById(abrirContaTO.getIdEstabelecimento());
		
		Conta conta = new Conta();
		conta.setDataAbertura(new Date());
		conta.setEstabelecimento(estabelecimento);
		
		//Adicionando por agora o usuario que criou a conta apenas
		Set<Usuario> usuarios = new HashSet<Usuario>();
		usuarios.add(usuario);
		
		conta.setUsuarios(usuarios);
		conta = contaRepository.save(conta);
		
		ContaTO contaTO = new ContaTO();
		contaTO.setId(conta.getId());
		contaTO.setValorTotal(BigDecimal.ZERO);
		
		return contaTO;
	}

	public List<ParticipanteTO> buscaParticipantes(Long idConta) {
		List<ParticipanteTO> participanteTOs = participanteTORespository.findAll(idConta);
		
		//Somando items para preencher a parcial
		for(ParticipanteTO participanteTO : participanteTOs) {
			participanteTO.setParcial(getParcial(idConta, participanteTO.getIdUsuario()));
		}
		
		return participanteTOs;
	}
	
	public BigDecimal getParcial(Long idConta, Long idUsuario) {
		BigDecimal parcial = BigDecimal.ZERO;
		List<UsuarioItemTO> usuarioItemTOs = usuarioItemTORepository.findAll(idConta, idUsuario);
		for(UsuarioItemTO usuarioItemTO : usuarioItemTOs) {
			parcial = parcial.add(usuarioItemTO.getQtdUsuario().multiply(BigDecimal.valueOf(usuarioItemTO.getQtdTotal())).multiply(usuarioItemTO.getPreco()));
		}
		return parcial;
	}

}
