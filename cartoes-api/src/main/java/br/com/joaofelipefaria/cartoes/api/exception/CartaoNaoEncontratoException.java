package br.com.joaofelipefaria.cartoes.api.exception;

public class CartaoNaoEncontratoException extends RuntimeException {
	public CartaoNaoEncontratoException(
			String numeroCartao) {
		super ("Cartão não encontrado: " +
			numeroCartao);
	}
}
