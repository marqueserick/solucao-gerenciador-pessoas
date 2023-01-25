package com.marqueserick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marqueserick.dto.AdicionarPessoaDto;
import com.marqueserick.dto.PessoaDto;
import com.marqueserick.service.PessoaService;

@RestController
@RequestMapping("/api/v1/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<PessoaDto>> listarPessoas() {
		return ResponseEntity.ok(service.listarPessoas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> listarPorId(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.listarPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<PessoaDto> adicionarPessoa(@RequestBody AdicionarPessoaDto dto){
		return ResponseEntity.ok(service.adicionarPessoa(dto));
	}
	
	@PutMapping
	public ResponseEntity<PessoaDto> editarPessoa(@RequestBody AdicionarPessoaDto dto){
		return ResponseEntity.ok(service.editarPessoa(dto));
	}
}
