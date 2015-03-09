/*
 * Convierte un valor string en formato dd/mm/aaaa a un objeto tipo date
 */
function stringToDate(strDate)
{		
	var fechaArr = strDate.split('/');
	var ano = fechaArr[0];
	var mes = fechaArr[1];
	var dia = fechaArr[2];
	
	var fecha = new Date(ano, mes - 1, dia);
	
	return fecha;
}

function diferenciaDias(fecha1, fecha2)
{
	var fin = fecha1.getTime() - fecha2.getTime();
	var dias = Math.floor(fin / (1000 * 60 * 60 * 24)) ;
	return dias;
}