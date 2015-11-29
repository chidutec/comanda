package br.com.ffit.comanda.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Item;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.model.UsuarioConta;
import br.com.ffit.comanda.repository.ContaRepository;
import br.com.ffit.comanda.repository.EstabelecimentoRepository;
import br.com.ffit.comanda.repository.ItemRepository;
import br.com.ffit.comanda.repository.ParticipanteTORespository;
import br.com.ffit.comanda.repository.UsuarioContaRepository;
import br.com.ffit.comanda.repository.UsuarioItemTORepository;
import br.com.ffit.comanda.repository.UsuarioRepository;
import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.to.FecharContaTO;
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
	
	@Autowired
	private UsuarioContaRepository usuarioContaRespository;
	
	public List<Item> findItensById(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		return itemRepository.findByConta(conta);
	}

	@Transactional
	public ContaTO abrirConta(AbrirContaTO abrirContaTO) {
		Usuario usuario = usuarioRepository.findById(abrirContaTO.getIdUsuario());
		Estabelecimento estabelecimento = estabelecimentoRepository.findById(abrirContaTO.getIdEstabelecimento());
		
		Date agora = new Date();
		Conta conta = new Conta();
		conta.setDataAbertura(agora);
		conta.setEstabelecimento(estabelecimento);
		
		
		conta = contaRepository.save(conta);
		
		//Adicionando por agora o usuario que criou a conta apenas
		UsuarioConta usuarioConta = new UsuarioConta();
		usuarioConta.setDataAbertura(agora);
		usuarioConta.setUsuario(usuario);
		usuarioConta.setConta(conta);
		
		usuarioContaRespository.save(usuarioConta);

		
		ContaTO contaTO = new ContaTO();
		contaTO.setId(conta.getId());
		contaTO.setValorTotal(BigDecimal.ZERO);
		
		return contaTO;
	}
	
	@Transactional
	public void fecharConta(FecharContaTO fecharContaTO) {
		Usuario usuario = new Usuario();
		usuario.setId(fecharContaTO.getIdUsuario());
		
		Conta conta = new Conta();
		conta.setId(fecharContaTO.getIdConta());
		
		UsuarioConta usuarioConta = new UsuarioConta();
		usuarioConta.setUsuario(usuario);
		usuarioConta.setConta(conta);
		usuarioConta = usuarioContaRespository.findByUsuarioAndConta(usuario, conta);
		
		Date agora = new Date();
		usuarioConta.setDataFechamento(agora);
		
		usuarioContaRespository.save(usuarioConta);
		
		List<UsuarioConta> usuariosAbertosNaConta = usuarioContaRespository.findByContaAndDataFechamentoIsNull(conta);
		
		if(usuariosAbertosNaConta.isEmpty()) {
			conta = contaRepository.findOne(conta.getId());
			conta.setDataFechamento(agora);
			contaRepository.save(conta);
		}
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

	public List<ContaTO> buscaContas(Long idUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		List<UsuarioConta> usuarioContas = usuarioContaRespository.findByUsuarioAndDataFechamentoIsNull(usuario);
		
		List<ContaTO> contaTOs = new ArrayList<ContaTO>();
		for(UsuarioConta usuarioConta: usuarioContas) {
			ContaTO contaTO = new ContaTO();
			contaTO.setId(usuarioConta.getConta().getId());
			contaTO.setDataAberturaUsuario(usuarioConta.getDataAbertura());
			contaTO.setNomeRestaurante(usuarioConta.getConta().getEstabelecimento().getNome());
			contaTO.setValorParcial(getParcial(contaTO.getId(), idUsuario));
			contaTOs.add(contaTO);
		}
		return contaTOs;
	}
}
