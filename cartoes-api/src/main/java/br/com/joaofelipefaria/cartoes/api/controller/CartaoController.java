package br.com.joaofelipefaria.cartoes.api.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaofelipefaria.cartoes.api.dto.CriarCartaoRequest;
import br.com.joaofelipefaria.cartoes.api.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	private final CartaoService service;
	
	public CartaoController(CartaoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<CriarCartaoRequest> criar(
			@RequestBody CriarCartaoRequest request){
		return ResponseEntity.status(201).body(service.criar(request));
	}
	
	@GetMapping("/{numeroCartao}")
	public ResponseEntity<BigDecimal> obterSaldo(
			@PathVariable String numeroCartao){
		return ResponseEntity.ok(service.obterSaldo(numeroCartao));
	}
}