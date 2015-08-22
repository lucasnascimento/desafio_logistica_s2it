package br.com.s2it.logistica.test.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.s2it.logistica.domain.Cidade;
import br.com.s2it.logistica.domain.CidadeRepository;
import br.com.s2it.logistica.test.ApplicationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationTest.class })
@Configuration
@ComponentScan("br.com.s2it.logistica")
public class CidadeRepositoryTest {

	@Autowired
	CidadeRepository repository;
	@Autowired
	GraphDatabaseService graphDatabaseService;
	
	@Before
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
	@Autowired
	Neo4jOperations neo4jOperations;

	@Test
	@Transactional
	public void findByNameTest() {
		Cidade cidadeA = repository.findByNomeCidadeAndNomeMapa("A", "SP");
		Assert.assertEquals(2,cidadeA.getRotas().size());
	}

}
