package dinamica.resultados.exportar;

import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import dinamica.resultadoslab.dao.ResultadoLaboratorioDAOSimulador;
import dinamica.resultadoslab.dto.ResultadoLabDTO;


public class Exportador {
	
	public void exportarPDF(OutputStream os, String identificacion)
	{
		TransformerFactory tFactory = TransformerFactory.newInstance();               
		Transformer transformer;
		String xml = null;
		
		try 
		{		
			ResultadoLaboratorioDAOSimulador dao = new ResultadoLaboratorioDAOSimulador();
			ResultadoLabDTO resultado = dao.extraerResultadosLab("Medellin", "2800", identificacion).get(0);
			
			xml = resultado.convertirA_XML();
			
			StringWriter writer = new StringWriter();
	        StreamResult result = new StreamResult(writer);
			
			StreamSource plantilla = new StreamSource(getClass().getClassLoader().getResourceAsStream("plantilla.xsl"));			
			transformer = tFactory.newTransformer(plantilla);			
			StreamSource datos = new StreamSource(new StringReader(xml));
						
			transformer.transform(datos, result);
			
			
			System.out.println(writer.toString());
			String texto = writer.toString();
			//texto = StringEscapeUtils.unescapeHtml(texto);
			texto = StringEscapeUtils.unescapeHtml3(texto);
			System.out.println(texto);
			    
	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocumentFromString(texto);
	        
	        //renderer.setDocument(Documen, url)
	        renderer.layout();
	        renderer.createPDF(os);	        
	        os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
}
