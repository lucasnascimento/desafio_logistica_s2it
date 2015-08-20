package br.com.s2it.logistica.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity
public class Rota {

	@GraphId
	private Long id;
	@StartNode
	private Cidade cidadeOrigem;
	@EndNode
	private Cidade cidadeDestino;
	private Integer distancia;
	
	public Rota(){
		super();
	}

	public Rota(Cidade cidadeOrigem, Cidade cidadeDestino, Integer distancia) {
		this.cidadeOrigem = cidadeOrigem;
		this.cidadeDestino = cidadeDestino;
		this.distancia = distancia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cidade getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(Cidade cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public Cidade getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(Cidade cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cidadeDestino == null) ? 0 : cidadeDestino.hashCode());
		result = prime * result
				+ ((cidadeOrigem == null) ? 0 : cidadeOrigem.hashCode());
		result = prime * result
				+ ((distancia == null) ? 0 : distancia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Rota other = (Rota) obj;
		if (cidadeDestino == null) {
			if (other.cidadeDestino != null)
				return false;
		} else if (!cidadeDestino.equals(other.cidadeDestino))
			return false;
		if (cidadeOrigem == null) {
			if (other.cidadeOrigem != null)
				return false;
		} else if (!cidadeOrigem.equals(other.cidadeOrigem))
			return false;
		if (distancia == null) {
			if (other.distancia != null)
				return false;
		} else if (!distancia.equals(other.distancia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rota [id=" + id + ", cidadeOrigem=" + cidadeOrigem
				+ ", cidadeDestino=" + cidadeDestino + ", distancia="
				+ distancia + "]";
	}

}
