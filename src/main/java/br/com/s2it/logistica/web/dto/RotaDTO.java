package br.com.s2it.logistica.web.dto;

public class RotaDTO {

	private String rota;
	private Double custo;

	public RotaDTO() {
	}

	public RotaDTO(String rota, Double custo) {
		super();
		this.rota = rota;
		this.custo = custo;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custo == null) ? 0 : custo.hashCode());
		result = prime * result + ((rota == null) ? 0 : rota.hashCode());
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
		RotaDTO other = (RotaDTO) obj;
		if (custo == null) {
			if (other.custo != null)
				return false;
		} else if (!custo.equals(other.custo))
			return false;
		if (rota == null) {
			if (other.rota != null)
				return false;
		} else if (!rota.equals(other.rota))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RotaDTO [rota=" + rota + ", custo=" + custo + "]";
	}

}
