package com.marqueserick.dto;

import com.marqueserick.model.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnderecoDto {

	private Long id;
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	
	public EnderecoDto(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
	}
}
