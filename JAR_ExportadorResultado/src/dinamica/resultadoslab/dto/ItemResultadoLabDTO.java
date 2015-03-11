package dinamica.resultadoslab.dto;

public class ItemResultadoLabDTO {
	private String item;
	private String resultado1;
	private String resultado2;
	private String valoresRef;
	private String unidad;
	private String limiteSuperior;
	private String limiteInferior;
		
	public String getLimiteSuperior() {
		return limiteSuperior;
	}
	public void setLimiteSuperior(String limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
	public String getLimiteInferior() {
		return limiteInferior;
	}
	public void setLimiteInferior(String limiteInferior) {
		this.limiteInferior = limiteInferior;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getResultado1() {
		return resultado1;
	}
	public void setResultado1(String resultado1) {
		this.resultado1 = resultado1;
	}
	public String getResultado2() {
		return resultado2;
	}
	public void setResultado2(String resultado2) {
		this.resultado2 = resultado2;
	}
	public String getValoresRef() {
		return valoresRef;
	}
	public void setValoresRef(String valoresRef) {
		this.valoresRef = valoresRef;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}
