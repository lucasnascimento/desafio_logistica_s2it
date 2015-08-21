package br.com.s2it.logistica.web.dto;

public class MapaRequestDTO {

	private Double autonomia;
	private Double valorCombustivel;

	public MapaRequestDTO() {
		super();
	}

	public Double getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Double autonomia) {
		this.autonomia = autonomia;
	}

	public Double getValorCombustivel() {
		return valorCombustivel;
	}

	public void setValorCombustivel(Double valorCombustivel) {
		this.valorCombustivel = valorCombustivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autonomia == null) ? 0 : autonomia.hashCode());
		result = prime
				* result
				+ ((valorCombustivel == null) ? 0 : valorCombustivel.hashCode());
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
		MapaRequestDTO other = (MapaRequestDTO) obj;
		if (autonomia == null) {
			if (other.autonomia != null)
				return false;
		} else if (!autonomia.equals(other.autonomia))
			return false;
		if (valorCombustivel == null) {
			if (other.valorCombustivel != null)
				return false;
		} else if (!valorCombustivel.equals(other.valorCombustivel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MapaRequestDTO [autonomia=" + autonomia + ", valorCombustivel="
				+ valorCombustivel + "]";
	}

}
