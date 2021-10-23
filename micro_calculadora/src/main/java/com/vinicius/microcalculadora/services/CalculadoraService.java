package com.vinicius.microcalculadora.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vinicius.microcalculadora.model.Calculadora;
import com.vinicius.microcalculadora.model.Tabela;

@Service
public class CalculadoraService {
	
	@Value("${micro_tabela.host}")
	private String microTabela;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Calculadora getCalculo(long tabelaId, int dias) {
		Map<String, String> urlParametros = new HashMap<>();
		urlParametros.put("id", "" +tabelaId);
		Tabela tabela = restTemplate.getForObject(microTabela + "/tabela/{id}", Tabela.class, urlParametros);
		return new Calculadora(tabela.getPessoa(),tabela.getPrecoPorDia(),dias);
	}
}
