package br.com.joaofelipefaria.cartoes.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.joaofelipefaria.cartoes.api.model.Cartao;
import jakarta.persistence.LockModeType;

public interface CartaoRepository extends JpaRepository<Cartao, String>{
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT c FROM Cartao c WHERE c.numeroCartao = :numeroCartao")
	Optional<Cartao> findByIdForUpdate(@Param("numeroCartao") String numeroCartao);
}