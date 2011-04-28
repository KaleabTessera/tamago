package org.tamago.eclipse.cdl.editor.scanner;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Conteneur des attributs de texte des differentes entites de l'editeur.
 * @author Hakim Belhaouari
 */
public class CDLTextAttributeProvider {
	public static final String ENTITY_ATTRIBUTE = "__pos_cdl_entity_attribute";
	public static final String COMMENT_ATTRIBUTE = "__pos_cdl_comment_attribute";
	public static final String DEFAULT_ATTRIBUTE = "__pos_cdl_default_attribute";
	public static final String LITTERAL_ATTRIBUTE = "__pos_cdl_litteral_attribute";
	public static final String STRING_ATTRIBUTE = "__pos_cdl_string_attribute";
	public static final String ACCESS_ATTRIBUTE = "__pos_cdl_access_attribute";
	public static final String TYPE_ATTRIBUTE = "__pos_cdl_type_attribute";
	public static final String KEYWORD_ATTRIBUTE = "__pos_cdl_type_attribute";
	public static final String PROPERTY_ATTRIBUTE = "__pos_cdl_property_attribute";
	
	private Map<String,TextAttribute> fAttributes = new HashMap<String,TextAttribute>();

	public CDLTextAttributeProvider() {
		// Les commentaires en gris clair
		fAttributes.put(COMMENT_ATTRIBUTE,
				new TextAttribute(new Color(Display.getCurrent(), 
									new RGB(0, 128, 0))));
		// Par defaut en noir
		fAttributes.put(DEFAULT_ATTRIBUTE,
				new TextAttribute(new Color(Display.getCurrent(), 
									new RGB(0, 0, 0))));
		
		// Nombres decimaux en bleu"
		fAttributes.put(LITTERAL_ATTRIBUTE,
				new TextAttribute(new Color(Display.getCurrent(),
									new RGB(0, 0, 255))));
		
		fAttributes.put(STRING_ATTRIBUTE,
				new TextAttribute(new Color(Display.getCurrent(),
									new RGB(0, 0, 255)),null,SWT.ITALIC));
		
		fAttributes.put(KEYWORD_ATTRIBUTE,
				new TextAttribute(new Color(Display.getCurrent(), 
						new RGB(127, 0, 85)), null, SWT.BOLD));
		
		fAttributes.put(PROPERTY_ATTRIBUTE,
				new TextAttribute(new Color(Display.getCurrent(), 
						new RGB(127, 127, 0)), null, SWT.ITALIC));
	}
	
	public TextAttribute getAttribute(String type) {
		TextAttribute attr = (TextAttribute)fAttributes.get(type);
		if(attr == null) {
			attr = (TextAttribute) fAttributes.get(DEFAULT_ATTRIBUTE);
		}
		return attr;
	}
}
