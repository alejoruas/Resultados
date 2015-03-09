<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes"/>
<xsl:template match="/">
  <html> 
  <head>
  <style type="text/css">
	@page {
   		size: 8.5in 11in;
   		margin: 0mm 0mm 0mm 0mm;
   	}
   	.textoNormal
   	{
   		font-family: Arial, Helvetica, sans-serif;
   		font-size: 11px;
   	}
   	.tablaCabecera
   	{
   		font-family: Arial, Helvetica, sans-serif;
   		font-size: 11px;   		
   	}
   	.celda
   	{
   		border-bottom: dotted #C5C5C5;
   	}
   	.celdaTitulo
   	{   
   		border-top: 1px solid #000000;   		
   		border-bottom: 1px solid #000000;
   	}
   </style>
  </head>
  <body class="textoNormal">
  Cra 65 Nro. 34A - 73 Tel: 351 03 33<br></br>
  www.dinamicaips.com.co
  <hr></hr>
    <table width="100%" class="tablaCabecera" cellpadding="0px">      
      <xsl:for-each select="ResultadoLabDTO">
      <tr>
        <td class="celda">Paciente: <xsl:value-of select="apellidoPaterno"/> &#160; <xsl:value-of select="apellidoMaterno"/> &#160; <xsl:value-of select="nombre_paciente"/></td>
        <td class="celda">Identificación: <xsl:value-of select="identificacion_paciente"/></td>
        <td class="celda">Nro. Servicio: <xsl:value-of select="numero_orden"/></td>
        <td class="celda">No. Paciente: <xsl:value-of select="numero_paciente"/></td>        
      </tr>
      <tr>
        <td class="celda">Sexo/Edad: <xsl:value-of select="sexo"/>/<xsl:value-of select="edad"/></td>
        <td class="celda">Teléfono: <xsl:value-of select="telefono"/></td>
        <td class="celda" colspan="2">Tipo servicio: <xsl:value-of select="tipo_servicio"/></td>                
      </tr>
      <tr>
        <td class="celda">Médico: <xsl:value-of select="medico_remitente"/></td>
        <td class="celda">Fecha servicio: <xsl:value-of select="fecha_servicio_texto"/></td>
        <td class="celda">Fecha reporte: <xsl:value-of select="fecha_reporte_texto"/></td>
        <td class="celda"></td>        
      </tr>
      <tr>
        <td class="celda" colspan="2">Empresa: <xsl:value-of select="empresa"/></td>
        <td class="celda" colspan="2">Habitación: <xsl:value-of select="habitacion"/></td>                
      </tr>
      </xsl:for-each>
    </table>  
    <table width="100%" cellpadding="0px" cellspacing="0px">
    	<tr>
    		<td class="celdaTitulo" width="40%">Análisis</td>
    		<td class="celdaTitulo" width="10%">Resultado</td>
    		<td class="celdaTitulo" width="10%"></td>
    		<td class="celdaTitulo" width="20%">Valores de Referencia</td>
    		<td class="celdaTitulo" width="20%">Unidad</td>
    	</tr>
    </table>
      
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
