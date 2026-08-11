package br.com.joaofelipefaria.cartoes.api.dto;

public record CriarCartaoRequest(
		String numeroCartao, 
		String senha) {
}