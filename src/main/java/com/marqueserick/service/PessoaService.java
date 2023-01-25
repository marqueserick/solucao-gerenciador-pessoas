package com.marqueserick.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marqueserick.dto.AdicionarPessoaDto;
import com.marqueserick.dto.PessoaDto;
import com.marqueserick.model.Pessoa;
import com.marqueserick.repository.PessoaRepositoy;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepositoy repository;
	
	public List<PessoaDto> listarPessoas(){
		List<Pessoa> pessoas = repository.findAll();
		return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
	}
	
	public PessoaDto listarPorId(Long id) {
		return new PessoaDto(buscarPorId(id));
	}
	
	public PessoaDto editarPessoa(AdicionarPessoaDto dto) {
		Pessoa pessoa = buscarPorId(dto.getId());
		pessoa.setNome(dto.getNome());
		pessoa.setDataNascimento(dto.getDataNascimento());
		repository.save(pessoa);
		return new PessoaDto(pessoa);
	}
	
	public PessoaDto adicionarPessoa(AdicionarPessoaDto dto) {
		Pessoa pessoa = new Pessoa(dto.getNome(), dto.getDataNascimento());
		repository.save(pessoa);
		return new PessoaDto(pessoa);
	}
	
	private Pessoa buscarPorId(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada na base de dados");
		return pessoa.get();
		
	}

}
