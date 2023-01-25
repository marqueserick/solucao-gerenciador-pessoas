package com.marqueserick.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdicionarPessoaDto {
	
	
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	
	public AdicionarPessoaDto(String nome, LocalDate dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

}
