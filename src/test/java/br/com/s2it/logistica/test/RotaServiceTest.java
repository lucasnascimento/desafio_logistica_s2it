package br.com.s2it.logistica.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.s2it.logistica.Application;
import br.com.s2it.logistica.domain.Cidade;
import br.com.s2it.logistica.domain.CidadeRepository;
import br.com.s2it.logistica.service.RotaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@Configuration
@ComponentScan("br.com.s2it.logistica")
public class RotaServiceTest {

	@Autowired
	CidadeRepository repository;
	@Autowired
	RotaService service;
	@Autowired
	GraphDatabaseService graphDatabaseService;
	
	@Before
	@Transactional
	public void setupTest(){
		Cidade cidadeA = new Cidade("A", "SP");
		repository.save(cidadeA);
		Cidade cidadeB = new Cidade("B", "SP");
		repository.save(cidadeB);
		Cidade cidadeC = new Cidade("C", "SP");
		repository.save(cidadeC);
		Cidade cidadeD = new Cidade("D", "SP");
		repository.save(cidadeD);
		Cidade cidadeE = new Cidade("E", "SP");
		repository.save(cidadeE);		
		
		cidadeA.criarRota(cidadeB, 10, graphDatabaseService);
		cidadeB.criarRota(cidadeD, 15, graphDatabaseService);
		cidadeA.criarRota(cidadeC, 20, graphDatabaseService);
		cidadeC.criarRota(cidadeD, 30, graphDatabaseService);
		cidadeB.criarRota(cidadeE, 50, graphDatabaseService);
		cidadeD.criarRota(cidadeE, 30, graphDatabaseService);
		
		repository.save(cidadeA);
		repository.save(cidadeB);
		repository.save(cidadeC);
		repository.save(cidadeD);
		repository.save(cidadeE);
	}

	@Test
	@Transactional
	public void calculaRotaTest() {
		Assert.assertEquals(new Double(6.25), service.calcularCusto("A", "D", "SP", 10.0, 2.5));
		
		List<Cidade> cidadesCaminhoMaisCurto = service.recuperarCaminhoMaisCurto("A", "D", "SP");
		int index = 0;
		for (String cidade : new String[]{"A","B","D"}){
			Assert.assertEquals(cidade, cidadesCaminhoMaisCurto.get(index++).getNomeCidade());
		}		
	}

}
