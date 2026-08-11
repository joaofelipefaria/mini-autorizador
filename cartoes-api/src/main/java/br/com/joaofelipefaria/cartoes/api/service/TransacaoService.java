package br.com.joaofelipefaria.cartoes.api.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import br.com.joaofelipefaria.cartoes.api.dto.TransacaoRequest;
import br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException;
import br.com.joaofelipefaria.cartoes.api.model.Cartao;
import br.com.joaofelipefaria.cartoes.api.repository.CartaoRepository;

import static br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException.Tipo.CARTAO_INEXISTENTE;
import static br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException.Tipo.SALDO_INSUFICIENTE;
import static br.com.joaofelipefaria.cartoes.api.exception.ErroNaTransacaoException.Tipo.SENHA_INVALIDA;

@Service
@AllArgsConstructor
public class TransacaoService {
	private final CartaoRepository repository;
	
	public String realizar(TransacaoRequest request) {
		Cartao cartao = repository.findById(request.numeroCartao())
				.orElseThrow(() -> new ErroNaTransacaoException(CARTAO_INEXISTENTE));
		if(!cartao.getSenha().equals(request.senhaCartao())) {
			throw new ErroNaTransacaoException(SENHA_INVALIDA);
		}
		if(cartao.getSaldo().compareTo(request.valor()) > 0) {
			throw new ErroNaTransacaoException(SALDO_INSUFICIENTE);
		}
		return "OK";
	}
}