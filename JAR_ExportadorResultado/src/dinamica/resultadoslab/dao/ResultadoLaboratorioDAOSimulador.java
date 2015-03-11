package dinamica.resultadoslab.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dinamica.resultadoslab.dto.ItemResultadoLabDTO;
import dinamica.resultadoslab.dto.ResultadoLabDTO;

public class ResultadoLaboratorioDAOSimulador {
	public List<ResultadoLabDTO> extraerResultadosLab(String ciudad, String compania, String identificacion) throws Exception
	{
		List<ResultadoLabDTO> lista = new ArrayList<ResultadoLabDTO>();
				
		ResultadoLabDTO resultadoLab = null;
					
		resultadoLab = new ResultadoLabDTO();
		resultadoLab.setNombre_paciente("VIVIANA DEL CARMEN");
		resultadoLab.setApellidoMaterno("ZAPATA");
		resultadoLab.setApellidoPaterno("RESTREPO");
		resultadoLab.setIdentificacion_paciente("1035987654");
		resultadoLab.setNumero_orden(21987654);
		resultadoLab.setNumero_paciente(6520);
		resultadoLab.setFecha_servicio(new Date());
		resultadoLab.setFecha_nacimiento(new Date());
		resultadoLab.setSexo("FEMENINO");
		resultadoLab.setTelefono("6529865");
		resultadoLab.setTipo_servicio("P.O.S (PLAN OBLIGATORIO DE SALUD)");
		resultadoLab.setMedico_remitente("CRISTIAN FELIPE BETANCUR JARAMILLO");
		resultadoLab.setFecha_servicio(new Date());
		resultadoLab.setFecha_reporte(new Date());
		resultadoLab.setEmpresa("Dinámica IPS");
		resultadoLab.setHabitacion("");
		lista.add(resultadoLab);	
		
		List<ItemResultadoLabDTO> items = new ArrayList<ItemResultadoLabDTO>();
		ItemResultadoLabDTO item1 = new ItemResultadoLabDTO();
		item1.setItem("Valores de referencia definidos en ATPIII (2004)");
		item1.setResultado1("1");
		item1.setResultado2("2");
		item1.setLimiteInferior("0");
		item1.setLimiteSuperior("200");
		items.add(item1);
		
		
		
		return lista;
	}	
}
