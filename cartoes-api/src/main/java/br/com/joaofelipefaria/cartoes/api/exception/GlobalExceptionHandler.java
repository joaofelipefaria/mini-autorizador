package br.com.joaofelipefaria.cartoes.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.joaofelipefaria.cartoes.api.dto.CriarCartaoRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CartaoNaoEncontratoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleCartaoNaoEncontrado() {}
	
	@ExceptionHandler(CartaoJaExisteException.class)
	public ResponseEntity<CriarCartaoRequest> handleCartaoJaExiste(CartaoJaExisteException exception){
		return ResponseEntity
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(exception.getRequest());
	}
	
	@ExceptionHandler(ErroNaTransacaoException.class)
	public ResponseEntity<String> handleErroNaTransacao(ErroNaTransacaoException exception){
		return ResponseEntity
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(exception.getTipo().toString());
	}
}
