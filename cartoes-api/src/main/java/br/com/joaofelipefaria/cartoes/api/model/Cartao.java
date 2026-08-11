package br.com.joaofelipefaria.cartoes.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cartoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cartao {
	@Id
	@Column(name = "numero_cartao", length=16)
	private String numeroCartao;
	
	@Column(nullable = false, length = 4)
	private String senha;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal saldo = BigDecimal.ZERO;
	
	public Cartao(String numeroCartao, String senha) {
		this.numeroCartao = numeroCartao;
		this.senha = senha;
	}
}