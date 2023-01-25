package com.marqueserick.model;

import com.marqueserick.dto.EnderecoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="TB002_ENDERECO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	private boolean isPrincipal;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public Endereco(EnderecoDto dto, Pessoa pessoa) {
		this.logradouro = dto.getLogradouro();
		this.cep = dto.getCep();
		this.numero = dto.getNumero();
		this.cidade = dto.getCidade();
		this.isPrincipal = pessoa.getEndereco().size() == 0;
		this.pessoa = pessoa;
	}
}
