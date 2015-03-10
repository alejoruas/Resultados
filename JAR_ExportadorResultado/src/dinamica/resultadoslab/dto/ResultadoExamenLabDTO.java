package dinamica.resultadoslab.dto;

import java.util.List;

public class ResultadoExamenLabDTO {
	private String servicio;
	private List<ItemResultadoLabDTO> items;
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public List<ItemResultadoLabDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemResultadoLabDTO> items) {
		this.items = items;
	}
	
	
}
