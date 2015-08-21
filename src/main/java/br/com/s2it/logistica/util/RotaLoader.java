package br.com.s2it.logistica.util;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.s2it.logistica.domain.Cidade;
import br.com.s2it.logistica.domain.CidadeRepository;

@Component
public class RotaLoader {
	
	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private GraphDatabaseService graphDatabaseService;
	
	@Transactional
	public boolean carregaRota(String rota, String nomeMapa){
		String[] rotaArray = rota.split(" ");
		
		String cidadeOrigem = rotaArray[0];
		String cidadeDestino = rotaArray[1];
		Integer distancia = Integer.parseInt(rotaArray[2]);
		
		Cidade cidadeOrigemEntity = criaOuRecupera(cidadeOrigem, nomeMapa);
		Cidade cidadeDestinoEntity = criaOuRecupera(cidadeDestino, nomeMapa);
		
		cidadeOrigemEntity.criarRota(cidadeDestinoEntity, distancia, graphDatabaseService);
		repository.save(cidadeOrigemEntity);

		return true;
	}

	@Transactional
	private Cidade criaOuRecupera(String cidade, String nomeMapa) {
		Cidade cidadeEntity = repository.findByNomeCidadeAndNomeMapa(cidade, nomeMapa);
		if (cidadeEntity == null){
			cidadeEntity = new Cidade(cidade, nomeMapa);
			repository.save(cidadeEntity);
		}
		return cidadeEntity;
	}

}
