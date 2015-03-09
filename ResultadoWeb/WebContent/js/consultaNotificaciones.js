ocultarSinRegistros();

cargarListas();

mygrid = new dhtmlXGridObject('gridbox');
iniciarGrid();

function mostrarCargando(){
    $("#progressbar").show();
}

function ocultarCargando(){
    $("#progressbar").hide();
}

function cargarListas()
{	
	$.ajax({
		type: 'GET',
		url: 'rest/reportes/listarRegionales',
		dataType: "json",        
		success: function(dato){
			if (dato.redirect != null) { window.location.replace(dato.redirect); }
			else cargarRegionales(dato);
			},
		error: function (x, status, error) {
	        if (x.status == 403) {
	            alert("Su sesión ha expirado.");
	            window.location.href ="/Inicio";
	        }
	        else {
	            alert("Ocurrió un error: " + status + " código de error: " + error);
	        }
	    }
	});
	
	$.ajax({
		type: 'GET',
		url: 'rest/reportes/listarAplicaciones',
		dataType: "json",        
		success: cargarAplicaciones		
	});
	
	$.ajax({
		type: 'GET',
		url: 'rest/reportes/listarTipos',
		dataType: "json",        
		success: cargarTipos		
	});
}

function cargarRegionales(data) {		
	$('#regionales option').remove();
	$.each(data.regionalDTO, function(index, regional) {
		$('#regionales')
        .append($("<option></option>")
        .attr("value",regional.prefijo)
        .text(regional.nombreRegional)); 		
	});
}

function cargarAplicaciones(data) {		
	$('#aplicaciones option').remove();
	
	if (data == null)
		return true;	
		
	if( Object.prototype.toString.call( data.aplicacionDTO ) === '[object Array]' ) 
		$.each(data.aplicacionDTO, function(index, aplicacion) {
			$('#aplicaciones')
	        .append($("<option></option>")
	        .attr("value",aplicacion.aplicacion)
	        .text(aplicacion.aplicacion)); 		
		});
	else
		$('#aplicaciones')
        .append($("<option></option>")
        .attr("value",data.aplicacionDTO.aplicacion)
        .text(data.aplicacionDTO.aplicacion));
}

function cargarTipos(data) {
	$('#tipos option').remove();
	
	if (data == null)
		return true;
	
	if( Object.prototype.toString.call( data.tipoDTO ) === '[object Array]' ) 
		$.each(data.tipoDTO, function(index, tipo) {
			$('#tipos')
	        .append($("<option></option>")
	        .attr("value",tipo.tipo)
	        .text(tipo.tipo)); 		
		});
	else
		$('#tipos')
        .append($("<option></option>")
        .attr("value",dato.tipoDTO.tipo)
        .text(dato.tipoDTO.tipo)); 	
}

$("#formulario").submit(function(e)
{
	mostrarCargando();	
	
	$("#pagina").val("1");
	var datos = $(this).serializeArray();	
	
	$.ajax({
		url : "rest/reportes/buscar",
		type : "POST",
		data : datos,
		dataType: "json",    
		success : function(dato){
			ocultarCargando();
			if (dato != null)
			{	
				if (dato.redirect != null) { window.location.replace(dato.redirect); }
				else cargarGrid(dato);
			}
			else
				cargarGrid(dato);
		},
		error : function(e){window.location.replace("/FinSession.html");}
	});
	
	e.preventDefault();
});


function iniciarGrid()
{	
	mygrid.setImagePath("imgs/");
	mygrid.setHeader("Reenviar,Orden,Remision,Consecutivo,Servicio,Enviado,Fecha Servicio,Fecha Envío,Error,Intentos",
			false,["font-size:12px","font-size:12px","font-size:12px","font-size:12px","font-size:12px","font-size:12px","font-size:12px","font-size:12px","font-size:12px","font-size:12px"]);
	mygrid.setInitWidths("*,*,*,*,*,*,*,*,*,*");
	mygrid.enableAutoWidth(true);
	mygrid.setColAlign("left,left,left,left,left,left,left,left,left,left");
	mygrid.setColTypes("link,ed,ed,ed,ed,ed,ed,ed,ed,ed");
	mygrid.setColSorting("str,str,str,str,str,str,str,str,str,str");
	mygrid.enableTooltips("false,true,true,true,true,true,true,true,true,true");
	mygrid.setSkin("dhx_skyblue");
	mygrid.setStyle("", "font-size:12px","", "");
	mygrid.enableLightMouseNavigation(true);	
	mygrid.init();	
	
	ocultarGrid();
}

function ocultarGrid()
{
	$( "#gridbox" ).hide();
	if( $('#toolbar').is(':visible') == true ) {
		$( "#toolbar" ).slideToggle("fast");
	}	
}

function mostrarGrid()
{
	if( $('#gridbox').is(':visible') == false ) {
		$( "#gridbox" ).slideToggle("fast");
	}	
	if( $('#toolbar').is(':visible') == false ) {
		$( "#toolbar" ).slideToggle("fast");
	}	
}

function mostrarSinRegistros()
{
	$("#estadoConsulta").show();
}

function ocultarSinRegistros()
{
	$("#estadoConsulta").hide();
}

function cargarGrid(data)
{	
	var totalPaginas = 0;
	var cuantosRegistros = 0;
	var totalRegistros = 0;
	
	mygrid.selectAll();
	mygrid.deleteSelectedRows();
	
	if (data == null)
	{
		mostrarSinRegistros();
		ocultarGrid();
		return true;
	}
	
	ocultarSinRegistros();
		
	if( Object.prototype.toString.call( data.notificacionDTO ) === '[object Array]' ) {
		$.each(data.notificacionDTO, function(index, registro) {		
			mygrid.addRow(mygrid.uid(), ["Reenviar^javascript:reenviar(" + registro.nmordenservicio + "," + registro.nmconsecutivointerno + "," + registro.cdservicio + ");",registro.nmordenservicio,registro.remision,registro.nmconsecutivointerno,registro.cdservicio,registro.snenviado,registro.feservicio,registro.feenvio,registro.error,registro.intentos]);
			totalPaginas = registro.totalPaginas;
			cuantosRegistros = cuantosRegistros + 1;
			totalRegistros = registro.totalRegistros;
		});		
	}
	else {
		var registro = data.notificacionDTO;
		mygrid.addRow(mygrid.uid(), ["Reenviar^javascript:reenviar(" + registro.nmordenservicio + "," + registro.nmconsecutivointerno + "," + registro.cdservicio + ");",registro.nmordenservicio,registro.remision,registro.nmconsecutivointerno,registro.cdservicio,registro.snenviado,registro.feservicio,registro.feenvio,registro.error,registro.intentos], 0);
		totalPaginas = registro.totalPaginas;
		cuantosRegistros = 1;
		totalRegistros = registro.totalRegistros;
	}	
	
	if (cuantosRegistros > 0)
	{
		mostrarGrid();		
		$("#registrosMostrados").text(cuantosRegistros);
		$("#totalRegistros").text(totalRegistros);
	}
	else
		ocultarGrid();
	
	$("#totalPaginas").val(totalPaginas);
	
	if (totalPaginas > 0)
	{		
		$("#paginas option").remove();
		for (var i = 1; i <= totalPaginas; i++)
		{
			$('#paginas')
		        .append($("<option></option>")
		        .attr("value", i)
		        .text(i)); 				
		}
		
		$("#paginas option").filter(function() {	   
		    return $(this).text() == $("#pagina").val(); 
		}).prop('selected', true);
		
		var pagina = $("#pagina").val();
		
		if (pagina == totalPaginas)
		{
			$("#siguiente").attr("disabled", "disabled");
			$("#final").attr("disabled", "disabled");
		}
		else
		{
			$("#siguiente").removeAttr("disabled");
			$("#final").removeAttr("disabled");
		}
		
		if (pagina == 1)
		{
			$("#inicio").attr("disabled", "disabled");
			$("#anterior").attr("disabled", "disabled");
		}
		else
		{
			$("#inicio").removeAttr("disabled");
			$("#anterior").removeAttr("disabled");
		}
	}	
}

$("#inicio").click(function(e)
{	
	cambioPagina(1);
});

$("#anterior").click(function(e)
{	
	var pagina = parseInt($("#pagina").val());	
	cambioPagina(pagina - 1);
});

$("#siguiente").click(function(e)
{
	var pagina = parseInt($("#pagina").val());	
	cambioPagina(pagina + 1);
});

$("#final").click(function(e)
{
	var ultima = parseInt($("#totalPaginas").val());	
	cambioPagina(ultima);
});

function cambioPagina(numPagina)
{	
	mostrarCargando();
	$("#pagina").val(numPagina);
	var datos = $("#formulario").serializeArray();	
	
	$.ajax({
		url : "rest/reportes/buscar",
		type : "POST",
		data : datos,
		dataType: "json",    
		success : function(dato){
			if (dato.redirect != null) { window.location.replace(dato.redirect); }
			else
			{
				cargarGrid(dato);	
				ocultarCargando();
			}
		},
		error: function (x, status, error) {
            if (x.status == 403) {
                alert("Su sesión ha expirado.");
                window.location.href ="/Inicio";
            }
            else {
                alert("Ocurrió un error: " + status + " código de error: " + error);
            }
        }
		//error : function(e){window.location.replace("/FinSession.html");}
	});
}

function reenviar(ordenServicio, consec, serv)
{
	mostrarCargando();
	var prefijo = $("#regionales").val();
	var aplicacion = $("#aplicaciones").val();
	var tip = $("#tipos").val();
	
	var datos = [{name: "fi", value: $("#dpFechaInicial").val()},
	             {name: "ff", value:$("#dpFechaFinal").val()},
	             {name: "reg", value: prefijo},
	             {name: "app", value: aplicacion},
	             {name: "tipo", value: tip},
	             {name: "remision", value: $("#remision").val()},
	             {name: "ordenFiltro", value: $("#orden").val()},
	             {name: "orden", value: ordenServicio},
	             {name: "pagina", value: $("#pagina").val()},
	             {name: "consecutivo", value: consec},
	             {name: "servicio", value: serv}];
	
	$.ajax({
		url : "rest/reportes/reenviar",
		type : "POST",
		data : datos,
		dataType: "json",    
		success : function(dato){
			ocultarCargando();
			if (dato.redirect != null) { window.location.replace(dato.redirect); }
			else cargarGrid(dato);
		},
		error : function(e){window.location.replace("/FinSession.html");}
	});
}
