package br.com.ffit.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.model.Item;
import br.com.ffit.comanda.service.ContaService;
import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.to.FecharContaTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ParticipanteTO;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value="/abrirConta", method = RequestMethod.POST) 
	public @ResponseBody JSONResponse<ContaTO> abrirConta(@RequestBody AbrirContaTO abrirContaTO) {
		JSONResponse<ContaTO> jsonResponse = new JSONResponse<ContaTO>();		;
		jsonResponse.setObj(contaService.abrirConta(abrirContaTO));
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("Conta Criada");
		return jsonResponse;
	}
	
	@RequestMapping(value="/fecharConta", method = RequestMethod.POST) 
	public @ResponseBody JSONResponse<ContaTO> fecharConta(@RequestBody FecharContaTO fecharContaTO) {
		JSONResponse<ContaTO> jsonResponse = new JSONResponse<ContaTO>();		;
		contaService.fecharConta(fecharContaTO);
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("Conta Fechada");
		return jsonResponse;
	}
	
	
	@RequestMapping(value="{id}/getItens", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItens(@PathVariable Long id) {
		return contaService.findItensById(id);
	}
	
	@RequestMapping(value="/buscaParticipantes/{idConta}", method = RequestMethod.GET)
	public @ResponseBody JSONResponse<List<ParticipanteTO>> buscaParticipantes(@PathVariable Long idConta) {
		JSONResponse<List<ParticipanteTO>> jsonResponse = new JSONResponse<List<ParticipanteTO>>();
		jsonResponse.setObj(contaService.buscaParticipantes(idConta));
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}
	
	@RequestMapping(value="/buscaContas/{idUsuario}", method = RequestMethod.GET)
	public @ResponseBody JSONResponse<List<ContaTO>> buscaContas(@PathVariable Long idUsuario) {
		JSONResponse<List<ContaTO>> jsonResponse = new JSONResponse<List<ContaTO>>();
		jsonResponse.setObj(contaService.buscaContas(idUsuario));
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}
	
}
