package br.com.joaofelipefaria.cartoes.api.service;

import static br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException.Tipo.CARTAO_INEXISTENTE;
import static br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException.Tipo.SALDO_INSUFICIENTE;
import static br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException.Tipo.SENHA_INVALIDA;

import org.springframework.stereotype.Service;

import br.com.joaofelipefaria.cartoes.api.dto.TransacaoRequest;
import br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException;
import br.com.joaofelipefaria.cartoes.api.model.Cartao;
import br.com.joaofelipefaria.cartoes.api.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransacaoService {
	private final CartaoRepository repository;

	@Transactional
	public String realizar(TransacaoRequest request) {

//	    System.out.println(
//	        ">>> [" + Thread.currentThread().getName() +
//	        "] INICIANDO transação - cartão: " + request.numeroCartao() +
//	        ", valor: " + request.valor()
//	    );

	    Cartao cartao = repository.findByIdForUpdate(request.numeroCartao())
	        .orElseThrow(() -> new ErroNaTransacaoException(CARTAO_INEXISTENTE));

//	    System.out.println(
//	        ">>> [" + Thread.currentThread().getName() +
//	        "] findById() retornou - saldo atual: " + cartao.getSaldo()
//	    );

	    if (!cartao.getSenha().equals(request.senhaCartao())) {
//	    	System.out.println(">>> [Senha Invalida]");
	        throw new ErroNaTransacaoException(SENHA_INVALIDA);
	    }

	    if (cartao.getSaldo().compareTo(request.valor()) < 0) {
//	    	System.out.println(">>> [Saldo Insuficiente]");
	        throw new ErroNaTransacaoException(SALDO_INSUFICIENTE);
	    }

//	    System.out.println(
//	        ">>> [" + Thread.currentThread().getName() +
//	        "] VALIDAÇÕES OK - aguardando 10 segundos antes do setSaldo()"
//	    );

//	    try {
//	        Thread.sleep(10_000);
//	    } catch (InterruptedException e) {
//	        Thread.currentThread().interrupt();
//	        throw new RuntimeException("Thread interrompida", e);
//	    }

//	    System.out.println(
//	        ">>> [" + Thread.currentThread().getName() +
//	        "] EXECUTANDO setSaldo() - saldo antes: " + cartao.getSaldo() +
//	        ", débito: " + request.valor()
//	    );

	    cartao.setSaldo(
	        cartao.getSaldo().subtract(request.valor())
	    );

//	    System.out.println(
//	        ">>> [" + Thread.currentThread().getName() +
//	        "] setSaldo() EXECUTADO - novo saldo: " + cartao.getSaldo()
//	    );

	    return "OK";
	}
}