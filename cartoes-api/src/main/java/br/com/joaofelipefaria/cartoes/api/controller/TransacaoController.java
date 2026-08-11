package br.com.joaofelipefaria.cartoes.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaofelipefaria.cartoes.api.dto.TransacaoRequest;
import br.com.joaofelipefaria.cartoes.api.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	private final TransacaoService service;
	
	public TransacaoController(TransacaoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<String> realizar(
			@RequestBody TransacaoRequest request){
		return ResponseEntity
				.status(201)
				.body(service.realizar(request));
	}
}