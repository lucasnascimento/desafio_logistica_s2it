package br.com.s2it.logistica.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.transaction.annotation.Transactional;

@NodeEntity
public class Cidade {

	@GraphId
	private Long id;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "cidade")
	private String nomeCidade;
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "mapa")
	private String nomeMapa;

	@RelatedToVia(type = "ROTA_PARA", direction = Direction.OUTGOING)
	private Set<Rota> rotas = new HashSet<Rota>();
	
	@SuppressWarnings("deprecation")
	@Transactional
	public Rota criarRota(Cidade cidadeDestino, Integer distancia, GraphDatabaseService graphDatabaseService) {
		Rota rota = new Rota(this, cidadeDestino, distancia);
		Transaction transaction = graphDatabaseService.beginTx();
		rotas.add(rota);
		transaction.success();
		transaction.finish();
		return rota;
	}

	public Cidade() {
	}

	public Cidade(String nomeCidade, String nomeMapa) {
		super();
		this.nomeCidade = nomeCidade;
		this.nomeMapa = nomeMapa;
	}

	public Long getId() {
		return id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Set<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(Set<Rota> rotas) {
		this.rotas = rotas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeCidade == null) ? 0 : nomeCidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCidade == null) {
			if (other.nomeCidade != null)
				return false;
		} else if (!nomeCidade.equals(other.nomeCidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeCidade;
	}

	public String getNomeMapa() {
		return nomeMapa;
	}

	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}

}
