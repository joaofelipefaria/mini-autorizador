package br.com.joaofelipefaria.cartoes.api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.joaofelipefaria.cartoes.api.dto.CriarCartaoRequest;

@Service
public class CartaoService {
	public CriarCartaoRequest criar(CriarCartaoRequest request) {
		return request;
	}
	
	public BigDecimal obterSaldo(String numeroCartao) {
		return new BigDecimal("495.15");
	}
}