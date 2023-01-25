package com.marqueserick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marqueserick.model.Pessoa;

public interface PessoaRepositoy extends JpaRepository<Pessoa, Long> {

}
