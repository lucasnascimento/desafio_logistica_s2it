package br.com.s2it.logistica.web.dto;

import java.util.List;

public class MapaDTO {

	private String nomeMapa;
	private List<String> rotas;

	public MapaDTO() {
	}

	public String getNomeMapa() {
		return nomeMapa;
	}

	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}

	public List<String> getRotas() {
		return rotas;
	}

	public void setRotas(List<String> rotas) {
		this.rotas = rotas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomeMapa == null) ? 0 : nomeMapa.hashCode());
		result = prime * result + ((rotas == null) ? 0 : rotas.hashCode());
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
		MapaDTO other = (MapaDTO) obj;
		if (nomeMapa == null) {
			if (other.nomeMapa != null)
				return false;
		} else if (!nomeMapa.equals(other.nomeMapa))
			return false;
		if (rotas == null) {
			if (other.rotas != null)
				return false;
		} else if (!rotas.equals(other.rotas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mapa [nomeMapa=" + nomeMapa + ", rotas=" + rotas + "]";
	}

}
