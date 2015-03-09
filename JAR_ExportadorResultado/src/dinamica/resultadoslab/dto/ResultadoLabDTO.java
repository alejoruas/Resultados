package dinamica.resultadoslab.dto;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public class ResultadoLabDTO {
	private String nombre_paciente;
	private String identificacion_paciente;
	private long numero_orden;
	private int numero_paciente;
	private String sexo;
	private Date fecha_nacimiento;
	private String telefono;
	private String tipo_servicio;
	private String medico_remitente;
	private Date fecha_servicio;
	private Date fecha_reporte;
	private String habitacion;
	private String empresa;
	private String profesional;
	private int edad;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fecha_servicio_texto;
	private String fecha_reporte_texto;
	
	
	public String getFecha_servicio_texto() {
		return fecha_servicio_texto;
	}
	public void setFecha_servicio_texto(String fecha_servicio_texto) {
		this.fecha_servicio_texto = fecha_servicio_texto;
	}
	public String getFecha_reporte_texto() {
		//return Utilidades.formatearFecha(new Date(), "dd/MM/yyyy hh:mm");
		return (new Date()).toString();
	}
	public void setFecha_reporte_texto(String fecha_reporte_texto) {
		this.fecha_reporte_texto = fecha_reporte_texto;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre_paciente() {
		return nombre_paciente;
	}
	public void setNombre_paciente(String nombre_paciente) {
		this.nombre_paciente = nombre_paciente;
	}
	public String getIdentificacion_paciente() {
		return identificacion_paciente;
	}
	public void setIdentificacion_paciente(String identificacion_paciente) {
		this.identificacion_paciente = identificacion_paciente;
	}
	public long getNumero_orden() {
		return numero_orden;
	}
	public void setNumero_orden(long numero_orden) {
		this.numero_orden = numero_orden;
	}
	public int getNumero_paciente() {
		return numero_paciente;
	}
	public void setNumero_paciente(int numero_paciente) {
		this.numero_paciente = numero_paciente;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		Calendar cal_nacimiento = Calendar.getInstance();
		Calendar cal_servicio = Calendar.getInstance();
		cal_nacimiento.setTime(fecha_nacimiento);
		cal_servicio.setTime(fecha_servicio);
		
		edad = cal_servicio.get(Calendar.YEAR) - cal_nacimiento.get(Calendar.YEAR); 
		
		int mesNacimiento = cal_nacimiento.get(Calendar.MONTH);
		if (mesNacimiento == cal_servicio.get(Calendar.MONTH)) { 
			if (cal_nacimiento.get(Calendar.DAY_OF_MONTH) <= cal_servicio.get(Calendar.DAY_OF_MONTH))
			{
				edad = edad;
			}
			else
				edad = edad - 1;
		}
		else if(mesNacimiento > cal_servicio.get(Calendar.MONTH))
			edad = edad - 1;
				
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipo_servicio() {
		return tipo_servicio;
	}
	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}
	public String getMedico_remitente() {
		return medico_remitente;
	}
	public void setMedico_remitente(String medico_remitente) {
		this.medico_remitente = medico_remitente;
	}
	public Date getFecha_servicio() {
		return fecha_servicio;
	}
	public void setFecha_servicio(Date fecha_servicio) {
		//fecha_servicio_texto = Utilidades.formatearFecha(fecha_servicio, "dd/MM/yyyy");
		fecha_servicio_texto = fecha_servicio.toString();
		this.fecha_servicio = fecha_servicio;
	}
	public Date getFecha_reporte() {
		return new Date();
	}
	public void setFecha_reporte(Date fecha_reporte) {
		//fecha_reporte_texto = Utilidades.formatearFecha(fecha_reporte, "dd/MM/yyyy hh:mm");
		fecha_reporte_texto = fecha_reporte.toString();
		this.fecha_reporte = fecha_reporte;
	}
	public String getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getProfesional() {
		return profesional;
	}
	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}	
	
	public String convertirA_XML()
	{
		String xml = "";
		try
		{
			JAXBContext jc = JAXBContext.newInstance(this.getClass());
	
			StringWriter st = new StringWriter();

	        JAXBElement<ResultadoLabDTO> je2 = new JAXBElement<ResultadoLabDTO>(new QName("ResultadoLabDTO"), ResultadoLabDTO.class, this);
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(je2, st);
	        
	        xml = st.toString();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return xml;
	}
}
