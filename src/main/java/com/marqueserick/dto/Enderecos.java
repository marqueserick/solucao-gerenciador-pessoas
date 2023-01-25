package com.marqueserick.dto;

import java.util.ArrayList;
import java.util.List;

import com.marqueserick.model.Endereco;

import lombok.Getter;

@Getter
public class Enderecos {
	
	private List<EnderecoDto> outros = new ArrayList<EnderecoDto>();
	private EnderecoDto principal;
	
	public Enderecos(List<Endereco> enderecos) {
		if(enderecos != null) enderecos.stream().forEach(this::classificar);
	}

	private void classificar(Endereco endereco) {
		EnderecoDto enderecoDto = new EnderecoDto(endereco);
		if(endereco.isPrincipal()) this.principal = enderecoDto;
		else outros.add(enderecoDto);
	}

}
