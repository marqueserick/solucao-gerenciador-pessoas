package com.marqueserick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marqueserick.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	@Query(value = "SELECT * FROM TB002_ENDERECO WHERE pessoa_id = ?1", nativeQuery=true)
	List<Endereco> buscarPorPessoa(Long idPessoa);
	
	@Query(value = "SELECT * FROM TB002_ENDERECO WHERE pessoa_id = ?1 AND is_principal = true ", nativeQuery = true)
	List<Endereco> buscarEnderecoPrincipal(Long idPessoa);

}
