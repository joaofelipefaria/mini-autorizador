package br.com.joaofelipefaria.cartoes.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.joaofelipefaria.cartoes.api.dto.CriarCartaoRequest;
import br.com.joaofelipefaria.cartoes.api.exception.CartaoJaExisteException;
import br.com.joaofelipefaria.cartoes.api.exception.CartaoNaoEncontratoException;
import br.com.joaofelipefaria.cartoes.api.model.Cartao;
import br.com.joaofelipefaria.cartoes.api.repository.CartaoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartaoService {
	private final CartaoRepository repository;

	public List<Cartao> getAll() {
		return repository.findAll();
	}

	public Cartao criar(CriarCartaoRequest request) {
		if (repository.existsById(request.numeroCartao())) {
			throw new CartaoJaExisteException(request);
		}
		Cartao cartao = new Cartao(request.numeroCartao(), request.senha());
		cartao.setSaldo(new BigDecimal(50.0));
		return repository.save(cartao);
	}

	public BigDecimal obterSaldo(String numeroCartao) {
		return repository.findById(numeroCartao).map(Cartao::getSaldo)
				.orElseThrow(() -> new CartaoNaoEncontratoException(numeroCartao));
	}
}