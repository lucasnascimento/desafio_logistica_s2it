package br.com.s2it.logistica;


import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableNeo4jRepositories (basePackages = "br.com.s2it.logistica.repository")
@EnableTransactionManagement(mode=AdviceMode.ASPECTJ)
public class Application extends Neo4jConfiguration{
	
	Application() {
        setBasePackage("br.com.s2it.logistica.model");
    }
	
	@Bean
	GraphDatabaseService graphDatabaseService() {
        return  new GraphDatabaseFactory().newEmbeddedDatabase("data/graphDatabase.db");// new EmbeddedGraphDatabase("graphDatabase.db");
    }
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}
	
}