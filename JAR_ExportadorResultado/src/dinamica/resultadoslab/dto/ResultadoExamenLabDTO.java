package dinamica.resultadoslab.dto;

import java.util.List;

public class ResultadoExamenLabDTO {
	private String servicio;
	private List<ItemResultadoLab> items;
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public List<ItemResultadoLab> getItems() {
		return items;
	}
	public void setItems(List<ItemResultadoLab> items) {
		this.items = items;
	}
	
	
}
