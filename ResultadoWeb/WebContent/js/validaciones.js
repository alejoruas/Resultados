function validarFecha(fecha){ 
	var fechaArr = fecha.split('/');
	var aho = fechaArr[0];
	var mes = fechaArr[1];
	var dia = fechaArr[2];
 
	var fecha = new Date(aho, mes - 1, dia);

	if(!fecha || fecha.getFullYear() == aho && fecha.getMonth() == mes -1 && fecha.getDate() == dia)
	{
		return true;
	}
	
	return false;
}

function validarEntero(valor) 
{    
    var re = /^(-)?[0-9]*$/;
    if (!re.test(valor)) {
        return false;
    }
    return true;
}

function validarEnteroPositivo(valor) 
{    
    var re = /^(-)?[0-9]*$/;
    if (!re.test(valor)) {
        return false;
    }
    else
    {
    	if (valor < 0)
    		return false;
    }
    return true;
}

function trim(valor)
{
	return valor.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

function soloNumeros(e)
{
	var keynum = window.event ? window.event.keyCode : e.which;
	if ((keynum == 8) || (keynum == 46))
		return true;
	 
	return /\d/.test(String.fromCharCode(keynum));
}