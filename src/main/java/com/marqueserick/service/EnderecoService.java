package com.marqueserick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marqueserick.dto.EnderecoDto;
import com.marqueserick.dto.Enderecos;
import com.marqueserick.model.Endereco;
import com.marqueserick.model.Pessoa;
import com.marqueserick.repository.EnderecoRepository;
import com.marqueserick.repository.PessoaRepositoy;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired 
	PessoaRepositoy pessoaRepository;

	public Enderecos listarEnderecos(Long id) {
		return new Enderecos(repository.buscarPorPessoa(id));
	}
	
	public Enderecos adicionarEndereco(Long idPessoa, EnderecoDto dto) {
		Pessoa pessoa = buscarPessoaPorId(idPessoa);
		Endereco endereco = new Endereco(dto, pessoa);
		repository.save(endereco);
		return listarEnderecos(idPessoa);
	}
	
	@Transactional
	public Enderecos definirPrincipal(Long id) {
		Endereco endereco = buscarEnderecoPorId(id);
		Long idPessoa = endereco.getPessoa().getId();
		
		removerEnderecoPrincipal(idPessoa);
		
		endereco.setPrincipal(true);
		repository.save(endereco);
		
		return listarEnderecos(idPessoa);
	}
	
	private void removerEnderecoPrincipal(Long idPessoa) {
		List<Endereco> enderecosPrincipais = repository.buscarEnderecoPrincipal(idPessoa);
		for(Endereco end : enderecosPrincipais) {
			end.setPrincipal(false);
			repository.save(end);
		}
		
	}

	private Endereco buscarEnderecoPorId(Long id) {
		Optional<Endereco> endereco = repository.findById(id);
		if(endereco.isEmpty()) throw new RuntimeException("Endereco não encontrado na base de dados");
		return endereco.get();
	}
	
	private Pessoa buscarPessoaPorId(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if(pessoa.isEmpty()) throw new RuntimeException("Pessoa não encontrada na base de dados");
		return pessoa.get();
	}
	
	

}
