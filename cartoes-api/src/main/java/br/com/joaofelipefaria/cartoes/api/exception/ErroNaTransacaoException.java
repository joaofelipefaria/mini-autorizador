package br.com.joaofelipefaria.cartoes.api.exception;

public class ErroNaTransacaoException extends RuntimeException {
	public enum Tipo{
		SALDO_INSUFICIENTE,
		SENHA_INVALIDA,
		CARTAO_INEXISTENTE
	}
	
	private final Tipo tipo;
	
	public ErroNaTransacaoException(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
}