package br.com.s2it.logistica;

import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@SpringBootApplication
public class Application{
	
	@Configuration
	@EnableNeo4jRepositories(basePackages = "br.com.s2it.logistica.domain")
	static class ApplicationConfig extends Neo4jConfiguration {

		public ApplicationConfig() {
			setBasePackage("br.com.s2it.logistica.domain");
		}

		@Bean
		GraphDatabaseService graphDatabaseService() {
			return new  GraphDatabaseFactory().newEmbeddedDatabase("data/graph.db");
		}
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

}