package tamagocc.util;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import tamagocc.logger.TamagoCCLogger;

/**
 * 
 * @author Hakim BELHAOUARI & Frederic Peschanski 
 */
public class TamagoCCValidateHandler extends DefaultHandler implements ContentHandler, ErrorHandler,
        EntityResolver, DTDHandler {

    /**
     * 
     */
    public TamagoCCValidateHandler() {
        super();
    }

    /**
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    public void endDocument() throws SAXException {
        //System.err.println("End Document");
        TamagoCCLogger.infoln(6,"Validate Handler : End Document");

    }

    /**
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        //System.err.println("Start Document");
        TamagoCCLogger.infoln(6,"Validate Handler : Start Document");
    }

   

    /**
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {
       //System.err.println("End element : "+arg0+":"+arg1+":"+arg2);
        TamagoCCLogger.infoln(6,"Validate Handler -> End Element : "+arg1);
    }

    /**
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String arg0, String arg1, String arg2,
            Attributes arg3) throws SAXException {
        //System.err.println("Start element : "+arg0+":"+arg1+":"+arg2+"\t"+arg3.toString());
        TamagoCCLogger.infoln(6,"Validate Handler -> Start Element : "+arg1);
    }

    /**
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException arg0) throws SAXException {
    	TamagoCCLogger.infoln(1,"Validate Handler -> ERROR [Line : "+arg0.getLineNumber()+" ; Column : "+arg0.getColumnNumber()+"], REASON : "+arg0.getMessage());
        
        throw arg0;
    }

    /**
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    public void fatalError(SAXParseException arg0) throws SAXException {
        TamagoCCLogger.infoln(1,"Validate Handler -> FATAL ERROR [Line : "+arg0.getLineNumber()+" ; Column : "+arg0.getColumnNumber()+"], REASON : "+arg0.getMessage());
        throw arg0;
    }

    /**
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException arg0) throws SAXException {
        System.err.println("\nWARNING [Line : "+arg0.getLineNumber()+" ; Column : "+arg0.getColumnNumber()+"] ->");
        arg0.printStackTrace(System.err);
        TamagoCCLogger.infoln(3,"Validate Handler -> WARNING [Line : "+arg0.getLineNumber()+" ; Column : "+arg0.getColumnNumber()+"], REASON : "+arg0.getMessage());
    }

   
}
