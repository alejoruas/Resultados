function buscarResultados()
{
	var datos = $("#formulario").serializeArray();
	
	var query = decodeURIComponent($.param(datos));
		
	query = "rest/Resultados/GenerarPDF?" + query;
	
	$("#pdf_view").attr("type", "application/pdf");
	$("#pdf_view").attr("data", query);	
}