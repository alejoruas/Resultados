package dinamica.resultados.exportar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;


public class ProfileImageReplacedElementFactory implements ReplacedElementFactory {

    private final ReplacedElementFactory superFactory;

    public ProfileImageReplacedElementFactory(ReplacedElementFactory superFactory) {
        this.superFactory = superFactory;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox,
            UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {

        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }

        String nodeName = element.getNodeName();
        String className = element.getAttribute("class");
        if ("div".equals(nodeName) && className.contains("profile_picture")) 
        {
            try {
            	BufferedImage imBuff = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Foto1.jpg"));
    			
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			ImageIO.write(imBuff, "jpg", baos);
    			byte[] bytes = baos.toByteArray();
    			baos.close();
    			
                Image image = Image.getInstance(bytes);
                FSImage fsImage = new ITextFSImage(image);

                if (fsImage != null) {
                    if ((cssWidth != -1) || (cssHeight != -1)) {
                        fsImage.scale(cssWidth, cssHeight);
                    }
                    return new ITextImageElement(fsImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadElementException e) {
            	e.printStackTrace();
            } finally {                
            }
        }

        return superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
    }

    @Override
    public void reset() {
        superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        superFactory.setFormSubmissionListener(listener);
    }
}