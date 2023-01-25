package com.marqueserick.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.marqueserick.dto.PessoaDto;
import com.marqueserick.model.Endereco;
import com.marqueserick.model.Pessoa;
import com.marqueserick.service.PessoaService;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {
	
	List<PessoaDto> dto;

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService service;

    private final String path = "/api/v1/pessoa";
    
    @BeforeEach
    void init() {
    	Pessoa p1 = new Pessoa();
    	p1.setId(1L);
    	p1.setNome("Nome");
    	p1.setDataNascimento(LocalDate.of(1995, 8, 28));
    	
    	
    	Endereco end = new Endereco();
    	end.setId(1L);
    	end.setLogradouro("logradouro");
    	end.setNumero("111");
    	end.setCep("11000-000");
    	end.setCidade("cidade");
    	end.setPrincipal(true);
    	
    	List<Endereco> enderecos = List.of(end);
    	p1.setEndereco(enderecos);
    	
    	List<Pessoa> pessoas = List.of(p1);
    	dto = pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
    }
    
    @Test
    void deveListarTodasAsPessoas() throws Exception {
    	
    	when(service.listarPessoas()).thenReturn(dto);
    	
    	this.mockMvc.perform(MockMvcRequestBuilders
                .get(path)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
    @Test
    void deveBuscarPessoaPorID() throws Exception {
    	
    	PessoaDto pessoaDto = dto.get(0);

        when(service.listarPorId(any())).thenReturn(pessoaDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(path + "/{id}", pessoaDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deveCriarNovaPessoa() throws Exception {

        when(service.adicionarPessoa(any())).thenReturn(dto.get(0));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Nome\",\"dataNascimento\":\"1995-08-28\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
