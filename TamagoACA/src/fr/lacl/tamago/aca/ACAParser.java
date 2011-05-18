/**
 * 
 */
package fr.lacl.tamago.aca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import fr.lacl.tamago.aca.visitor.ACAPrettyPrint;
import fr.lacl.tamago.aca.xmlterm.Processus;

/**
 * Construit l'automate de Tamago-CDL � partir d'une sp�cification ACA (pouvant donner de l'EB3)
 * 
 */
public class ACAParser {

	
	
	/**
	 * 
	 */
	public ACAParser() {
	
	}
	
	public static Processus unmarshall(InputStream input) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance("fr.lacl.tamago.aca.xmlterm");
		Unmarshaller u = context.createUnmarshaller();
		SchemaFactory schemafac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI );
		Schema s =  schemafac.newSchema(new File("TamagoACA.xsd"));
		u.setSchema(s);
		u.setEventHandler(new ValidationEventHandler() {
			
			@Override
			public boolean handleEvent(ValidationEvent event) {
				System.err.println("POSITION:"+event.getLocator().getLineNumber()+"x"+event.getLocator().getColumnNumber());
				System.err.println("SEVERITY:"+event.getSeverity());
				System.err.println(event.getMessage());
				return (event.getSeverity()==ValidationEvent.WARNING);
			}
		});
		@SuppressWarnings("unchecked")
		JAXBElement<Processus> doc = (JAXBElement<Processus>)u.unmarshal( input);
	    return doc.getValue();
	}
	
	
	public static void main(String[] args) {
		/*try {
			FileInputStream fis = new FileInputStream("Syntax.xml");
			Processus prop = unmarshall(fis);
			
			System.out.println(ACAPrettyPrint.getInstance().vProcessus(prop).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			FileInputStream fis = new FileInputStream("TamagoACA.xml");
			Processus prop = unmarshall(fis);
			System.out.println(ACAPrettyPrint.getInstance().vProcessus(prop).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
