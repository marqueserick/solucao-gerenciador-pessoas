package com.marqueserick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marqueserick.dto.EnderecoDto;
import com.marqueserick.dto.Enderecos;
import com.marqueserick.service.EnderecoService;

@RestController
@RequestMapping("/api/v1/endereco")
public class EnderecoController{
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public ResponseEntity<Enderecos> listarEnderecos(@RequestParam("idPessoa") Long id){
		return ResponseEntity.ok(service.listarEnderecos(id));
	}
	
	@PostMapping
	public ResponseEntity<Enderecos> adicionarEndereco(@RequestParam("idPessoa")Long id, @RequestBody EnderecoDto dto){
		return ResponseEntity.ok(service.adicionarEndereco(id, dto));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Enderecos> definirPrincipal(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.definirPrincipal(id));
	}

}
