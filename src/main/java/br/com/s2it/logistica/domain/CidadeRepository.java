package br.com.s2it.logistica.domain;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface CidadeRepository extends GraphRepository<Cidade> {
	
	Cidade findByNomeCidadeAndNomeMapa(String nomeCidade, String nomeMapa);
	
	List<Cidade> findAllByNomeMapa(String nomeMapa);

}
