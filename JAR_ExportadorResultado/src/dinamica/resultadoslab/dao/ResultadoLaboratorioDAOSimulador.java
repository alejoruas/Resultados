package dinamica.resultadoslab.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		resultadoLab.setEmpresa("Din�mica IPS");
		resultadoLab.setHabitacion("");
		lista.add(resultadoLab);			
		
		return lista;
	}	
}
