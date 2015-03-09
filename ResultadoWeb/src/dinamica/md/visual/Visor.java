package dinamica.md.visual;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class Visor {

	public void generarPDF()
	{		
		/* Create a TransformerFactory object */           
        TransformerFactory tFactory = TransformerFactory.newInstance();
        /* Get the incoming XSLT file */
        Transformer transformer;
		try {
			transformer = tFactory.newTransformer(new StreamSource("plantilla.xsl"));
			transformer.transform(new StreamSource("datos.xml"),new StreamResult(new FileOutputStream("prueba.html")));
			
			
			String File_To_Convert = "prueba.html";
	        String url = new File(File_To_Convert).toURI().toURL().toString();
	        System.out.println(""+url);
	        String HTML_TO_PDF = "ConvertedFile.pdf";
	        OutputStream os = new FileOutputStream(HTML_TO_PDF);       
	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocument(url);      
	        renderer.layout();
	        renderer.createPDF(os);        
	        os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
        
        
	}	
}
