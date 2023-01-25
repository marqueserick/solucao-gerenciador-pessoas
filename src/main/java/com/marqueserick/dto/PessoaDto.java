package com.marqueserick.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.marqueserick.model.Pessoa;

import lombok.Getter;

@Getter
public class PessoaDto {
	
	private Long id;
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/YY")
	private LocalDate dataNascimento;
	private Enderecos enderecos;
	
	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.dataNascimento = pessoa.getDataNascimento();
		this.enderecos = new Enderecos(pessoa.getEndereco());
	}
	
	public PessoaDto(Long id, String nome, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	
}
