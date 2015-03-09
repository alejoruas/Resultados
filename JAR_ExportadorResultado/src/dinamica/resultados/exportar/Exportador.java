package dinamica.resultados.exportar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import sun.misc.BASE64Encoder;
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
			
			BufferedImage imBuff = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Fondo1.jpg"));
			
			resultado.setFondo1(encodeToString(imBuff, "jpeg"));
			//System.out.println(resultado.getFondo1());
			
			xml = resultado.convertirA_XML();
			
			StringWriter writer = new StringWriter();
	        StreamResult result = new StreamResult(writer);
			
			StreamSource plantilla = new StreamSource(getClass().getClassLoader().getResourceAsStream("plantilla.xsl"));			
			transformer = tFactory.newTransformer(plantilla);		
			
			StreamSource datos = new StreamSource(new StringReader(xml));
			
			transformer.setParameter("fondo1", resultado.getFondo1());
			//transformer.setParameter("fondo1", "#00FF00");
			System.out.println(transformer.toString());
			//transformer.setOutputProperty("fondo1", "325235235");
			//transformer.set("fondo1", "4545454");
			transformer.transform(datos, result);
			
			
			
			//System.out.println(writer.toString());
			String texto = writer.toString();
			//texto = StringEscapeUtils.unescapeHtml(texto);
			texto = StringEscapeUtils.unescapeHtml3(texto);
			System.out.println(texto);
			    
	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocumentFromString(texto);
	        
	        //renderer.setDocument(Documen, url)
	        renderer.layout();
	        renderer.createPDF(os);	 
	        renderer.finishPDF();
	        os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	/**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
