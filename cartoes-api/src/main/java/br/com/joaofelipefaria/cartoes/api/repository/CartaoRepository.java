package br.com.joaofelipefaria.cartoes.api.repository;

import br.com.joaofelipefaria.cartoes.api.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String>{
}