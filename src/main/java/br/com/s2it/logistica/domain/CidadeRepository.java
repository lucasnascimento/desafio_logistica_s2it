package br.com.s2it.logistica.domain;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface CidadeRepository extends GraphRepository<Cidade> {
	
	Cidade findByNomeCidadeAndNomeMapa(String nomeCidade, String nomeMapa);

}
