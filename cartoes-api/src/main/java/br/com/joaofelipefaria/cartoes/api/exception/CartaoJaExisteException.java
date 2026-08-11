package br.com.joaofelipefaria.cartoes.api.exception;

import br.com.joaofelipefaria.cartoes.api.dto.CriarCartaoRequest;

public class CartaoJaExisteException extends RuntimeException {
	private final CriarCartaoRequest request;
	
	public CartaoJaExisteException(CriarCartaoRequest request) {
		this.request = request;
	}

	public CriarCartaoRequest getRequest() {
		return request;
	}
}