package br.com.joaofelipefaria.cartoes.api.dto;

import java.math.BigDecimal;

public record TransacaoRequest (
		String numeroCartao, 
		String senhaCartao, 
		BigDecimal valor){
}