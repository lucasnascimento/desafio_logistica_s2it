package br.com.s2it.logistica.test;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "br.com.s2it.logistica.domain")
@EnableTransactionManagement
@ComponentScan("br.com.s2it.logistica")
public class ApplicationTest extends Neo4jConfiguration {

	public ApplicationTest() {
		setBasePackage("br.com.s2it.logistica.domain");
	}

	@Bean
	GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory()
				.newEmbeddedDatabase("data/graphTest.db");
	}

}