package dinamica.mensajeriadinamica.controlador.rest;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import dinamica.resultados.exportar.Exportador;

@Path("/Resultados")
public class ServicioResultados {
	
	@GET	
	@Produces({"application/pdf"})
	@Path("/GenerarPDF")
	public StreamingOutput generarPDF(@QueryParam("identificacion") String identificacion)
	{
		final String id = identificacion;
		
		return new StreamingOutput() {
	        public void write(OutputStream output) throws IOException, WebApplicationException {
	            try {
	                Exportador exportador = new Exportador();
	                exportador.exportarPDF(output, id);
	            } catch (Exception e) {
	            	e.printStackTrace();
	                throw new WebApplicationException(e);
	            }
	        }
	    };
	}
}
