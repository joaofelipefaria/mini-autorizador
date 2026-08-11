package br.com.joaofelipefaria.cartoes.api.service;

import org.springframework.stereotype.Service;

import br.com.joaofelipefaria.cartoes.api.dto.TransacaoRequest;

@Service
public class TransacaoService {
	public String realizar(TransacaoRequest request) {
		return "OK";
	}
}