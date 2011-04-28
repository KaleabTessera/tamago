package tamagocc;
import tamagocc.api.*;
import tamagocc.api.TExpression.ExprType;
import tamagocc.exception.*;
import tamagocc.impl.*;
import tamagocc.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import javax.xml.parsers.*;
import org.xml.sax.*;
//import org.xml.sax.helpers.*;
//import org.w3c.dom.*;
import java.io.*;
import java.util.*;
import java.net.*;
import tamagocc.logger.*;

/**
 * This class represents the XML parser to generate the abstract syntax tree, and 
 * register it, in the current pool.
 * 
 * @author Hakim BELHAOUARI
 */

public class TamagoCCParser {

	/* DECLARATION STATIQUE DES OUTILS COMMUNS */
	/**
	 * Return the default parser.
	 */
	public static TamagoCCParser getDefaultParser() {
		return defaultParser;
	}

	private static TamagoCCParser defaultParser = new TamagoCCParser();

	static {
		// on est obliger d'utiliser ce bloc car le pool et le parser sont fortement couple
		// (l'un ayant obligatoirement besoin de l'autre pour fonctionner)
		defaultParser.setTamagoCCPool(TamagoCCPool.getDefaultPool());
	}	

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	static final String XSD_FILENAME = "TamagoNS.xsd";

	/**
	 * Contains information about the URL of the XSchema. This variable can be changed for another URL.
	 * <br>
	 * <em>Caution:</em> This variable needn't be modified in the JAR release of Tamago.
	 */
	public static String XSD_LOCATION = "http://www-poleia.lip6.fr/~belhaouari/TamagoNS.xsd";
	//static final String XSD_LOCATION = "file:///home/aliquando/Tamago/TamagoCC/TamagoNS.xsd";



	private TamagoCCPool pool;



	/**
	 * Constructor for the parser. A default parser is available for user.
	 */
	public TamagoCCParser() {
		super();
	}    

	/**
	 * Change the pool for the parser, where the parser will storage construted tree of contracts.
	 * @param p : a TamagoCCPool object
	 */
	public void setTamagoCCPool(TamagoCCPool p) {
		pool = p;
	}

	private void valideur(String xml) throws SAXException,IOException,ParserConfigurationException,TamagoCCException 
	{

		SAXParserFactory spf = SAXParserFactory.newInstance();

		// Set namespaceAware to true to get a parser that corresponds to
		// the default SAX2 namespace feature setting.  This is necessary
		// because the default value from JAXP 1.0 was defined to be false.
		spf.setNamespaceAware(true);
		spf.setValidating(true);

		SAXParser saxParser = spf.newSAXParser();
		
		// Set the schema language if necessary
		try {
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
		} catch (SAXNotRecognizedException x) {
			// This can happen if the parser does not support JAXP 1.2
			TamagoCCLogger.info(1,"Erreur SAXNotRecognizedException");
			throw new TamagoCCException(x);
		}
		try {
			// On va d'abord chercher si on a une ressource accessible
			InputStream xsdstream =  getClass().getResourceAsStream("/"+XSD_FILENAME);
			if(xsdstream != null) {
				// on a trouve une version on la prend
				// pratique dans la version jar
				TamagoCCLogger.info(1,"We find the XSD schema in the jar file");	    
				saxParser.setProperty(JAXP_SCHEMA_SOURCE,xsdstream);
			}
			else {
				// on va la chercher sur le NET ou autre
				TamagoCCLogger.println(1,"Connection to the site ...");
				TamagoCCLogger.info(3,"Used URL = "+XSD_LOCATION);
				TamagoCCLogger.println(3,"\tNo Proxy");
				URL url = new URL(XSD_LOCATION);
				URLConnection connection = url.openConnection();
				TamagoCCLogger.println(1,"The XSD schema has been finded.");
				saxParser.setProperty(JAXP_SCHEMA_SOURCE,connection.getInputStream());

			}
			TamagoCCLogger.infoln(1,"The XSD schema has been binded to the parser");

			// TODO : ajouter une detection pour savoir si c'est une uri
			try {
				File file = new File(xml);
				TamagoCCLogger.infoln(1,"Start validation of "+file+"...");
				saxParser.parse(file,new TamagoCCValidateHandler());
				TamagoCCLogger.infoln(1,"Validation finish with success of "+xml);
			}
			catch(Exception e) {
					TamagoCCLogger.infoln(1,"*Warning* : The argument is not a correct URI. I will deal as a direct string");
					try {
						//StringBufferInputStream sb = new StringBufferInputStream(xml);
						StringReader sr = new StringReader(xml);
						InputSource is = new InputSource(sr);
						saxParser.parse(is,new TamagoCCValidateHandler());
						TamagoCCLogger.infoln(1,"Validation finish with *one warning*");
					}
					catch(Exception e2) {
						throw new TamagoCCException(e);
					}
			}

		}
		catch(IOException use) {
			throw new TamagoCCException(use);
		}
	}


	/**
	 * This function validate and parse the CDL file or CDL stream passed in argument. 
	 * @param xml : represents the address of the CDL file or represents a CDL string
	 * @return Return an AST structure depending of the argument xml.
	 * @throws TamagoCCException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public TTamagoEntity parse(String xml)
	throws TamagoCCException,SAXException,IOException,ParserConfigurationException
	{
		valideur(xml);

		TamagoCCLogger.infoln(1,"Parsing of "+xml+"...");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = null;
		try {
			File file = new File(xml);
			//new URI(xml); // on test si c'est une syntaxe correct
			d = db.parse(file);
		}
		catch(Exception e) {


			TamagoCCLogger.infoln(1,"*Warning* : The argument is not a correct URI. I will deal as a string");
			try {
				//StringBufferInputStream sb = new StringBufferInputStream(xml);
				StringReader sr = new StringReader(xml);
				InputSource is = new InputSource(sr);
				d = db.parse(is);
			}
			catch(Exception e2) {
				throw new TamagoCCException(e);
			}
		}

		TTamagoEntity entity = parseXML(d);
		TamagoCCLogger.infoln(1,"[Parsing OK of "+xml+"]");
		return entity;
	}

	private TTamagoEntity parseXML(Node n)
	throws TamagoCCException
	{
		switch(n.getNodeType()) {
		case Node.DOCUMENT_NODE:
			Document d = (Document)n;
			return this.parseXML(d.getDocumentElement());	
		case Node.ELEMENT_NODE: {
			Element e = (Element)n;
			//NodeList nl = e.getChildNodes();
			String tag = e.getTagName();

			/* TODO: mettre la gestion de plusieurs service/composant/... */

			if("service".equals(tag)) {
				return parseService(e);
			}
			else if("component".equals(tag)) {
				return parseComponent(e);
			}
			else if("composite".equals(tag)) {
				return parseComposite(e);
			}
			else if("assembly".equals(tag)) {
				return parseAssembly(e);
			}
			else {
				throw new TamagoCCException("Unknow TagName of element : "+tag);
			}
		}
		default: {
			throw new TamagoCCException("Unknow node type : "+n.getNodeName());
		}

		}
	}

	/**
	 * Internal function. It returns the first element child of a element node 
	 * @param e : the node where begin to seach
	 * @return the first encounter element of the node e.
	 * @throws TamagoCCNotFoundChild : is thrown when no element is finded.
	 */
	public Element findFirstElementChild(Element e)
	throws TamagoCCNotFoundChild
	{
		NodeList nl = e.getChildNodes();
		int l = nl.getLength();
		for(int i=0;i < l;++i) {
			Node n = nl.item(i);
			switch(n.getNodeType()) {
			case Node.ELEMENT_NODE:{
				return(Element)n;
			}
			default:                
			}
		}
		throw new TamagoCCNotFoundChild("No element child for '"+e.getTagName()+"'");
	}

	/**
	 * Internal function. It returns the first element with a specific tag, from a specific node. 
	 * @param e : the node where to begin the searching.
	 * @param tagname : the specific name for the searching tag.
	 * @return return the first encounter element.
	 * @throws TamagoCCNotFoundChild : is thrown when no element with the specific name is finded.
	 */
	public Element findChild(Element e,String tagname)
	throws TamagoCCNotFoundChild
	{
		NodeList nl = e.getChildNodes();
		int l = nl.getLength();
		for(int i=0;i < l;++i) {
			Node n = nl.item(i);
			switch(n.getNodeType()) {
			case Node.ELEMENT_NODE:{
				Element elt = (Element)n;
				if(tagname.equals(elt.getTagName()))
					return elt;
			}
			default:                
			}
		}
		throw new TamagoCCNotFoundChild("No such child with name : "+tagname);
	}

	/**
	 * Internal function. It searches all element node with a specific tagname.
	 * @param e
	 * @param tagname
	 * @return
	 */
	public Iterator<Element> findChilds(Element e,String tagname)
	{
		NodeList nl = e.getChildNodes();
		ArrayList<Element> retour = new ArrayList<Element>();
		int l = nl.getLength();
		for(int i=0;i < l;++i) {
			Node n = nl.item(i);
			switch(n.getNodeType()) {
			case Node.ELEMENT_NODE: {
				Element elt = (Element)n;
				if(tagname.equals(elt.getTagName()))
					retour.add(elt);
			}
			default:
			}
		}
		return retour.iterator();
	}

	/**
	 * Internal function. Return an iterator of all child element of the specified element.
	 * @param e : an Element objet
	 * @return return child corresponding to an element of <em>e</em>.
	 */
	public Iterator<Element> findAllChilds(Element e)
	{
		NodeList nl = e.getChildNodes();
		ArrayList<Element> retour = new ArrayList<Element>();
		int l = nl.getLength();
		for(int i=0;i < l;++i) {
			Node n = nl.item(i);
			switch(n.getNodeType()) {
			case Node.ELEMENT_NODE: {
				Element elt = (Element)n;
				retour.add(elt);
			}
			default:
			}
		}
		return retour.iterator();
	}

	private String findChildText(Element e)
	throws TamagoCCNotFoundChild
	{
		NodeList nl = e.getChildNodes();
		int l = nl.getLength();
		for(int i=0;i < l;++i) {
			Node n = nl.item(i);
			switch(n.getNodeType()) {
			case Node.TEXT_NODE: {
				return ((Text)n).getData();
			}
			default:
			}
		}
		throw new TamagoCCNotFoundChild("No text node in element '"+e.getTagName()+"'" );
	}

	// --------------------------------------------------------------------------

	private Collection<TProperty> searchAndParseProperties(Element e)
	throws TamagoCCException
	{
		Collection<TProperty> properties = null;
		try {
			Element props = findChild(e,"properties");
			properties = parseProperties(findChilds(props,"property"));
		}
		catch(TamagoCCNotFoundChild noprops) {
			properties = new NilCollection<TProperty>();
		}
		return properties;
	}

	private Collection<TDefinition> searchAndParseDefinitions(Element e)
	throws TamagoCCException
	{
		Collection<TDefinition> definitions = null;
		try {
			Element defs = findChild(e,"definitions");
			definitions = parseDefinitions(findChilds(defs,"use"));
		}
		catch(TamagoCCNotFoundChild ee) {
			definitions = new NilCollection<TDefinition>();
		}
		return definitions;
	}

	private Collection<TBind> searchAndParseBinds(Element e)
	throws TamagoCCException
	{

		Collection<TBind> connects = null;
		try {
			Element cons = findChild(e,"binds");
			connects = parseBinds(findChilds(cons,"bind"));
		}
		catch(TamagoCCNotFoundChild ee) {
			connects = new NilCollection<TBind>();
		}
		return connects;
	}

	private Collection<TInvariant> searchAndParseInvariant(Element e)
	throws TamagoCCException
	{

		Collection<TInvariant> list;
		try {
			Element invariants = findChild(e,"invariants");
			TamagoCCLogger.println(3,"\t-- find some invariants");
			list = parseInvariants(findAllChilds(invariants));
		}
		catch(TamagoCCNotFoundChild noinvariants) {
			// on cherche s'il y a au moins 1 invariant
			list = new ArrayList<TInvariant>(1);
			try {
				Element invariant = findChild(e,"invariant");
				TamagoCCLogger.println(3,"\t-- find one invariant");
				list.add(parseInvariant(invariant));
			}
			catch(TamagoCCNotFoundChild noinvariant) {
				// on a trouve aucun invariant
				TamagoCCLogger.println(3,"\t-- Unfind any invariant");
			}			
		}
		return list;
	}

	private Collection<TInvariant> parseInvariants(Iterator<Element> invariants)
	throws TamagoCCException
	{
		ArrayList<TInvariant> list = new ArrayList<TInvariant>();

		while(invariants.hasNext()) {
			Element inv = (Element)invariants.next();
			list.add(parseInvariant(inv));
		}
		return list;		
	}

	private TInvariant parseInvariant(Element einv)
	throws TamagoCCException
	{
		String message = einv.getAttribute("message");
		String strcategory = einv.getAttribute("category");

		TExpression contract = null;

		try {
			Element element = findFirstElementChild(einv);
			contract = parseExpression(element);
		}
		catch(TamagoCCNotFoundChild ee) {
			contract = new TINoContract();
		}

		TCategory category = null;

		if(strcategory.trim().length() > 0) {
			category = new TICategory(strcategory);
		}
		else {
			category = TICategory.NoCategory;
		}

		TInvariant renvoie = new TIInvariant(contract,category,message);
		return renvoie;
	}

	private Collection<TImplements> parseImplements(Iterator<Element> impls)
	throws TamagoCCException
	{
		ArrayList<TImplements> list = new ArrayList<TImplements>();

		while(impls.hasNext()) {
			Element inv = (Element)impls.next();
			list.add(parseInterfaceImplement(inv));
		}
		return list;		
	}
	
	private TImplements parseInterfaceImplement(Element einv)
	throws TamagoCCException
	{
		String name = einv.getAttribute("type");
		String module = einv.getAttribute("module");

		TIImplements renvoie = new TIImplements(name,module);
		return renvoie;
	}


	private Collection<TExport> searchAndParseExports(Element e)
	throws TamagoCCException
	{
		Collection<TExport> exports = null;
		try {
			Element exps = findChild(e,"exports");
			exports = parseExports(findChilds(exps,"export"));
		}
		catch(TamagoCCNotFoundChild ee) {
			exports = new ArrayList<TExport>();
		}
		return exports;
	}

	private Collection<TRequire> searchAndParseRequires(Element e)
	throws TamagoCCException
	{		
		Collection<TRequire> requires = null;
		try {
			Element reqs = findChild(e,"requires");
			requires = parseRequires(findChilds(reqs,"require"));
		}
		catch(TamagoCCNotFoundChild ee) {
			requires = new ArrayList<TRequire>();
		}
		return requires;
	}

	private Collection<TProvide> searchAndParseProvides(Element e)
	throws TamagoCCException
	{
		Collection<TProvide> provides = null;
		try {
			Element pros = findChild(e,"provides");
			provides = parseProvides(findChilds(pros,"provide"));
		}
		catch(TamagoCCNotFoundChild ee) {
			provides = new ArrayList<TProvide>();
		}
		return provides;
	}

	private TBehavior searchAndParseBehavior(Element e)
	throws TamagoCCException
	{
		TBehavior behavior = null;
		try {
			behavior = parseBehavior(findChild(e,"behavior"));
		}
		catch(TamagoCCNotFoundChild nobehavior) {
			behavior = new TIBehavior(new NilCollection<TState>(),new NilCollection<TTransition>(),"");
		}
		return behavior;		
	}

	private Collection<TMethod> searchAndParseMethods(Element e)
	throws TamagoCCException 
	{
		Collection<TMethod> methods = null;        
		try {
			Element meths = findChild(e,"methods");       
			methods = parseMethods(findChilds(meths,"method"));
		}
		catch(TamagoCCNotFoundChild nomeths) {
			methods = new ArrayList<TMethod>();
		}
		return methods;
	}

	private Collection<TPercolator> searchAndParsePercolators(Element e)
	throws TamagoCCException 
	{
		Collection<TPercolator> percolators = new ArrayList<TPercolator>();        
		try {
			Element meths = findChild(e,"percolators");       
			percolators = parsePercolators(findChilds(meths,"percolator"));
		}
		catch(TamagoCCNotFoundChild nomeths) {
			// on fait rien
		}
		if(percolators.size() == 0)
			percolators.add(TIPercolator.getAllPercolator()); // par defaut on va chercher tous les percolateurs possibles 
		return percolators;
	}

	private Collection<TPercolator> parsePercolators(Iterator<?> nodelist)
	throws TamagoCCException
	{
		Collection<TPercolator> renvoie = new ArrayList<TPercolator>();
		while(nodelist.hasNext()) {
			Element e = (Element)nodelist.next();
			renvoie.add(parsePercolator(e));
		}
		return renvoie;
	}

	private TPercolator parsePercolator(Element e)
	throws TamagoCCException
	{
		String name = e.getAttribute("name");
		if(name.length() == 0) {
			throw new TamagoCCException("Problem : unfind name of the percolator");
		}
		return new TIPercolator(name);
	}

	private TService parseService(Element e)
	throws TamagoCCException
	{

		String name = e.getAttribute("name");
		String module = e.getAttribute("module");

		if(name.length() == 0)
			throw new TamagoCCException("Empty name of the service");

		TamagoCCLogger.infoln(3,"Parsing of Service :"+name+" in module "+module);

		String generic = e.getAttribute("generic");

		if(generic.length() != 0) {
			throw new TamagoCCException("Generic service is not available");
		}

		Collection<TMethod> methods = searchAndParseMethods(e);		
		Collection<TProperty> properties = searchAndParseProperties(e);		
		TBehavior behavior = searchAndParseBehavior(e);

		Collection<TInvariant> invariants = searchAndParseInvariant(e);

		Collection<TExtendService> extend = null;
		try {
			Element eextends = findChild(e,"extends");
			extend = parseExtends(findAllChilds(eextends));
		}
		catch(TamagoCCNotFoundChild noextends) {
			extend = new ArrayList<TExtendService>();
		}


		Collection<TImplements> impls = searchAndParseImplements(e);
		Collection<TNamespace> namespaces = searchAndParseImports(e);

		TamagoCCLogger.infoln(3,"End Parsing of Service :"+name+" in module "+module);		
		TService service = new TIService(TITamago.extractNameWithoutParamInfo(name),
				module,generic,methods,properties,invariants,behavior,extend,impls,namespaces,
				TITamago.extractParametrizedTypesFromName(name));
		TamagoCCPool.getDefaultPool().addEntry(name,module,service);
		return service;

	}

	private Collection<TImplements> searchAndParseImplements(Element e)
	throws TamagoCCException
	{

		Collection<TImplements> list;
		try {
			Element impls = findChild(e,"coretypes");
			TamagoCCLogger.println(3,"\t-- find some interface type");
			list = parseImplements(findAllChilds(impls));
		}
		catch(TamagoCCNotFoundChild noimpls) {
			// on cherche s'il y a au moins 1 invariant
			list = new ArrayList<TImplements>(1);		
		}
		return list;
	}

	private Collection<TMethod> parseMethods(Iterator<?> m)
	throws TamagoCCException
	{
		ArrayList<TMethod> al = new ArrayList<TMethod>();
		while(m.hasNext()) {
			al.add(parseMethod((Element)m.next()));
		}
		return al;
	}


	private TCondition parseCondition(Element e)
	throws TamagoCCException
	{
		TExpression expr;
		TCategory category;

		String message = e.getAttribute("message");
		String strcategory = e.getAttribute("category");


		try {
			Element element = findFirstElementChild(e);
			expr = parseExpression(element);
		}
		catch(TamagoCCNotFoundChild ee) {
			expr = new TINoContract();
		}


		if(strcategory.trim().length() > 0) {
			category = new TICategory(strcategory);
		}
		else {
			category = TICategory.NoCategory;
		}


		return new TICondition(expr,category,message);
	}

	private TMethod parseMethod(Element e)
	throws TamagoCCException
	{
		String name = e.getAttribute("name");
		String id = e.getAttribute("id");
		TType type = TIType.generateType(e.getAttribute("rettype"));

		Collection<TParameter> args = null;
		try {
			Element eargs = findChild(e,"parameters");
			args = parseParameters(findChilds(eargs,"parameter"));
		}
		catch(TamagoCCNotFoundChild ee) { 
			args = new NilCollection<TParameter>();
		}

		TCondition precondition = null;
		try {
			Element elepre = findChild(e,"pre");
			precondition = parseCondition(elepre);
		}
		catch(TamagoCCNotFoundChild ee) {
			precondition = new TICondition(new TINoContract(),TICategory.NoCategory,"");
		}

		TCondition postcondition = null;
		try {
			Element elepost = findChild(e,"post");
			postcondition = parseCondition(elepost);
		}
		catch(TamagoCCNotFoundChild ee) {
			postcondition = new TICondition(new TINoContract(),TICategory.NoCategory,"");
		}

		return new TIMethod(name,id,type,args,precondition,postcondition);
	}


	private Collection<TParameter> parseParameters(Iterator<?> ite)
	throws TamagoCCException
	{
		ArrayList<TParameter> al = new ArrayList<TParameter>();
		while(ite.hasNext()) {
			Node noeud = (Node)ite.next();
			if(noeud.getNodeType() == Node.ELEMENT_NODE)
				al.add(parseParameter((Element)noeud));
		}
		return al;
	}

	private TParameter parseParameter(Element e) 
	throws TamagoCCException
	{
		String name = e.getAttribute("name");
		String strtype = e.getAttribute("type");
		if(strtype.length() == 0) throw new TamagoCCException("No type in argument");
		TType type = TIType.generateType(strtype);
		return new TIParameter(name,type);
	}

	private TBool parseBool(Element e) 
	{

		String val = e.getAttribute("value");

		return new TIBool((new Boolean(val)).booleanValue());
	}

	private TNot parseNot(Element e)
	throws TamagoCCException
	{
		Element f = findFirstElementChild(e);
		return new TINot(parseExpression(f));
	}

	private TReturn parseReturn(Element e) 
	throws TamagoCCException
	{
		try {
			Element eindex = findChild(e, "index");
			TExpression index = parseExpression(findFirstElementChild(eindex));
			return new TIReturn(index);
		}
		catch(TamagoCCNotFoundChild tnfc) {
			return new TIReturn();	
		}       
	}




	private TExpression parseExpression(Element e)
	throws TamagoCCException
	{
		String tag = e.getTagName();
		if("operator".equals(tag))
			return parseOperator(e);
		else if("int".equals(tag))
			return parseInt(e);
		else if("real".equals(tag))
			return parseReal(e);
		else if(("string".equals(tag))||("String".equals(tag)))
			return parseString(e);
		else if("return".equals(tag))
			return parseReturn(e);
		else if("not".equals(tag))
			return parseNot(e);
		else if("atpre".equals(tag))
			return parseAtpre(e);
		else if("bool".equals(tag))
			return parseBool(e);
		else if("call".equals(tag))
			return parseCall(e);
		else if("cast".equals(tag))
			return parseCast(e);
		else if("read".equals(tag))
			return parseRead(e);
		else if("variable".equals(tag))
			return parseVariable(e);
		else if("in".equals(tag))
			return parseInLabel(e);
		else if("quantifier".equals(tag))
			return parseQuantifier(e);
		else if("nil".equals(tag) || "null".equals(tag))
			return parseNil(e);
		else if("lang-expr".equals(tag))
			return parseLangExpr(e);
		else if("instate".equals(tag))
			return parseInState(e);
		else 
			throw new TamagoCCException("Unknow expression : "+e.getTagName());
	}

	/**
	 * @param e
	 * @return
	 */
	private TExpression parseCast(Element e) throws TamagoCCException {
		String stype = e.getAttribute("type");
		Element expr = findChild(e, "expression");
		TExpression ebody = parseExpression(findFirstElementChild(expr));
		TIType type = (TIType) TIType.generateType(stype);
		
		return new TICast(type,ebody);
	}

	private TExpression parseLangExpr(Element e) throws TamagoCCException {
		String val = findChildText(e);
		return new TILanguageExpr(val);
	}

	private TExpression parseQuantifier(Element e) throws TamagoCCException {
		TamagoCCLogger.println(4, "Parse Quantifier");
		Element body = findChild(e, "body");
		String svar = e.getAttribute("variable");
		String quantifier = e.getAttribute("quantifier");
		String stype = e.getAttribute("type");
		TIType type = (TIType) TIType.generateType(stype);
		TIVariable var = new TIVariable(svar,true,stype);

		TExpression ebody = parseExpression(findFirstElementChild(body));

		try {
			Element range = findChild(e, "range");
			TamagoCCLogger.println(3, "Quantifier with range detected");
			Element min = findChild(range, "min");
			TamagoCCLogger.println(3, "\tmin element detected");
			Element max = findChild(range, "max");
			TamagoCCLogger.println(3, "\tmax element detected");

			TExpression emin = parseExpression(findFirstElementChild(min));
			TamagoCCLogger.println(3, "\tmin expression parsed");
			TExpression emax = parseExpression(findFirstElementChild(max));
			TamagoCCLogger.println(3, "\tmax expression parsed");

			if("forall".equals(quantifier)) {
				return new TIForallRange(type,var,emin,emax,ebody);
			}
			else if("exist".equals(quantifier)) {
				return new TIExistRange(type,var,emin,emax,ebody);
			}
			else {
				throw new TamagoCCException("Unsupported quantifier");
			}

		}
		catch(TamagoCCNotFoundChild nfc) {
			//TamagoCCLogger.println(3, "Quantifier with a set detected");
		}

		try {
			Element set = findChild(e, "set");
			TamagoCCLogger.println(3, "\tset element confirmed");

			ArrayList<TExpression> eset = new ArrayList<TExpression>();
			Iterator<Element> elts = findAllChilds(set);
			while(elts.hasNext()) {
				TExpression expr = parseExpression(findFirstElementChild(elts.next()));
				if(expr.getCat() == ExprType.VARIABLE)
					expr = new TIVariable(((TVariable)expr).getVariable(),true,stype);
				eset.add(expr);
			}

			TISet tset = new TISet(type,eset);

			TamagoCCLogger.println(3, "\tmin expression parsed");
			if("forall".equals(quantifier)) {
				return new TIForallSet(type,var,tset,ebody);
			}
			else if("exist".equals(quantifier)) {
				return new TIExistSet(type,var,tset,ebody);
			}
			else {
				throw new TamagoCCException("Unsupported quantifier");
			}
		}
		catch(TamagoCCNotFoundChild nfc) {
			//TamagoCCLogger.println(3, "Quantifier with a set");
		}
		
		try {
			Element ecoll = findChild(e, "collection");
			TamagoCCLogger.println(3, "\tcollection/array element confirmed");
			TExpression coll = parseExpression(findFirstElementChild(ecoll));
			
			TamagoCCLogger.println(3, "\tcollection/array expression parsed");
			if("forall".equals(quantifier)) {
				return new TIForallColl(type,var,coll,ebody);
			}
			else if("exist".equals(quantifier)) {
				return new TIExistColl(type,var,coll,ebody);
			}
			else {
				throw new TamagoCCException("Unsupported quantifier");
			}
		}
		catch(TamagoCCNotFoundChild nfc) {
			//TamagoCCLogger.println(3, "Quantifier with a set");
		}

		throw new TamagoCCException("Exception in a quantifier expression");
	}

	private TInState parseInState(Element e) throws TamagoCCException {
		Iterator<Element> ite  =  findChilds(e, "state");
		ArrayList<String> al = new ArrayList<String>();
		while(ite.hasNext()) {
			Element estate = ite.next();
			String state =  estate.getAttribute("name");
			al.add(state);
		}
		TIInState res = new TIInState(al);
		
		return res;
		
	}
	
	private TInLabel parseInLabel(Element e) throws TamagoCCException {
		TExpression subexpr = null;
		TExpression scope = null;
		
		try {
			Element escope = findChild(e, "scope");
			scope = parseExpression(findFirstElementChild(escope));
		}
		catch(TamagoCCNotFoundChild tnfc) {
			TamagoCCLogger.println(3,"TamagoCCParser<parseInLabel> : unfind a scope expression");
			throw new TamagoCCException(tnfc);
		}
		
		try {
			Element esubexpr = findChild(e, "target"); 
			subexpr = parseExpression(findFirstElementChild(esubexpr));
		}
		catch(TamagoCCNotFoundChild tnfc) {
			TamagoCCLogger.println(3,"TamagoCCParser<parseInLabel> : unfind a subexpression in the label term");
			throw new TamagoCCException(tnfc);
		}

		return new TIInLabel(scope,subexpr);
	}

	private TRead parseRead(Element e) throws TamagoCCException
	{
		String name = e.getAttribute("property");
		try {
			Element ex = findChild(e, "index");
			TExpression expr = parseExpression(findFirstElementChild(ex));
			return new TIRead(name,expr);
		}
		catch(TamagoCCNotFoundChild fc) {
			return new TIRead(name);	
		}
	}

	private TNil parseNil(Element e) throws TamagoCCException
	{
		return new TINil();
	}


	private TAtPre parseAtpre(Element e) throws TamagoCCException
	{
		Element elt = findFirstElementChild(e);
		return new TIAtPre(parseExpression(elt));
	}

	private TCall parseCall(Element e) throws TamagoCCException
	{
		TamagoCCLogger.infoln(4,"Parsing of Call started");

		String method = e.getAttribute("method");
		Collection<TExpression> args = null;
		try {
			Element eargs = findChild(e,"arguments");
			args = parseExpressions(findAllChilds(eargs));
		} catch(TamagoCCNotFoundChild nfce) {
			args = new NilCollection<TExpression>();
		}

		TamagoCCLogger.infoln(4,"Parsing of Call on method = "+method);

		return new TICall(method,args);

	}

	private TInteger parseInt(Element e) 
	throws TamagoCCException
	{
		String val = e.getAttribute("value");
		return new TIInteger(Integer.parseInt(val));
	}
	private TReal parseReal(Element e)
	throws TamagoCCException
	{
		String val = e.getAttribute("value");
		return new TIReal(Double.parseDouble(val));
	}
	private TString parseString(Element e) 
	throws TamagoCCException
	{
		String val = findChildText(e);
		return new TIString(val);
	}

	private TOperator parseOperator(Element e) 
	throws TamagoCCException 
	{
		String operateur = e.getAttribute("name");
		TOpeName op;
		if("add".equals(operateur)) 
			op = TOpeName.opPlus;
		else if("sub".equals(operateur))
			op = TOpeName.opMinus;
		else if("mul".equals(operateur))
			op = TOpeName.opTimes;
		else if("quo".equals(operateur))
			op = TOpeName.opQuo;
		else if("mod".equals(operateur))
			op = TOpeName.opMod;
		else if("le".equals(operateur))
			op = TOpeName.opInfEg;
		else if("lt".equals(operateur))
			op = TOpeName.opInf;
		else if("ge".equals(operateur))
			op = TOpeName.opSupEg;
		else if("gt".equals(operateur))
			op = TOpeName.opSup;
		else if("eq".equals(operateur))
			op = TOpeName.opEg;
		else if("ne".equals(operateur))
			op = TOpeName.opNe;
		else if("and".equals(operateur))
			op = TOpeName.opAnd;
		else if("or".equals(operateur))
			op = TOpeName.opOr;
		else if("xor".equals(operateur))
			op = TOpeName.opXor;
		else if("impl".equals(operateur))
			op = TOpeName.opImply;
		else if("equiv".equals(operateur))
			op = TOpeName.opEquiv;
		else
			throw new TamagoCCException("Unknow operator : "+operateur);

		Collection<TExpression> operands= parseExpressions(findAllChilds(e));

		return new TIOperator(op,operands);
	}

	private Collection<TExpression> parseExpressions(Iterator<?> ite) 
	throws TamagoCCException
	{
		ArrayList<TExpression> al = new ArrayList<TExpression>();
		while(ite.hasNext()) {
			Element e = (Element)ite.next();
			al.add(parseExpression(e));
		}
		return al;
	}

	private Collection<TProvide> parseProvides(Iterator<?> ite) 
	throws TamagoCCException
	{
		ArrayList<TProvide> al = new ArrayList<TProvide>();
		while(ite.hasNext()) {
			Element e = (Element)ite.next();
			al.add(parseProvide(e));
		}
		return al;
	}
	private TProvide parseProvide(Element e)
	throws TamagoCCException
	{
		String name = e.getAttribute("service");
		String module = e.getAttribute("module");
		TType[] type = TITamago.extractParametrizedTypesFromNameInArray(name);
		name = TITamago.extractNameWithoutParamInfo(name);
		TService s = (TService)pool.getTreeAbstractSyntax(name,module);
		return new TIProvide(name,module,type,s);
	}

	private Collection<TRequire> parseRequires(Iterator<?> ite) 
	throws TamagoCCException
	{
		ArrayList<TRequire> al = new ArrayList<TRequire>();
		while(ite.hasNext()) {
			Element e = (Element)ite.next();
			al.add(parseRequire(e));
		}
		return al;
	}
	private TRequire parseRequire(Element e)
	throws TamagoCCException
	{
		String name = e.getAttribute("service");
		String module = e.getAttribute("module");
		String label = e.getAttribute("label");
		TType[] types = TITamago.extractParametrizedTypesFromNameInArray(name);
		name = TITamago.extractNameWithoutParamInfo(name);
		TService service = (TService)pool.getTreeAbstractSyntax(name,module);
		return new TIRequire(label,name,module,service,types);
	}

	private TComponent parseComponent(Element e) 
	throws TamagoCCException
	{
		String name = e.getAttribute("name");
		String module = e.getAttribute("module");

		TamagoCCLogger.infoln(3,"Parsing of the component "+ name+" in module "+module);

		Collection<TProperty> properties = searchAndParseProperties(e);		
		Collection<TProvide> provides = searchAndParseProvides(e);		
		Collection<TRequire> requires = searchAndParseRequires(e);		
		Collection<TInvariant> invariants = searchAndParseInvariant(e);		
		Collection<TMethod> methods = searchAndParseMethods(e);
		TBehavior behavior = searchAndParseBehavior(e);
		Collection<TPercolator> percolators = searchAndParsePercolators(e);
		Collection<TImplements> impls = searchAndParseImplements(e);
		Collection<TNamespace> namespaces = searchAndParseImports(e);
		
		TamagoCCLogger.infoln(3,"End Parsing of the component "+ name+" in module "+module);
		TIComposant composant =new TIComposant(TITamago.extractNameWithoutParamInfo(name),
				module,properties,provides,requires,invariants,methods,behavior,percolators,impls,namespaces,
				TITamago.extractParametrizedTypesFromName(name));

		return composant;
	}

	private TAssembly parseAssembly(Element e) 
	throws TamagoCCException
	{
		String name = e.getAttribute("name");
		String module = e.getAttribute("module");

		TamagoCCLogger.infoln(3,"Parsing of the assembly "+ name+" in module "+module);


		Collection<TDefinition> definitions = searchAndParseDefinitions(e);		
		Collection<TBind> binds = searchAndParseBinds(e);
		TBehavior behavior = searchAndParseBehavior(e);
		Collection<TInvariant> invariants = searchAndParseInvariant(e);

		TamagoCCLogger.infoln(3,"End Parsing of the assembly "+ name+" in module "+module);

		return new TIAssembly(TITamago.extractNameWithoutParamInfo(name),
				module,definitions,binds,invariants,behavior,
				TITamago.extractParametrizedTypesFromName(name));
	}

	private TComposite parseComposite(Element e)
	throws TamagoCCException
	{
		String name = e.getAttribute("name");
		String module = e.getAttribute("module");

		TamagoCCLogger.infoln(3,"Parsing of the composite "+ name+" in module "+module);

		Collection<TProperty> properties = searchAndParseProperties(e);		
		Collection<TProvide> provides = searchAndParseProvides(e);		
		Collection<TRequire> requires = searchAndParseRequires(e);
		Collection<TInvariant> invariants = searchAndParseInvariant(e);		
		Collection<TDefinition> definitions = searchAndParseDefinitions(e);		
		Collection<TExport> exports = searchAndParseExports(e);		
		Collection<TBind> binds = searchAndParseBinds(e);		
		Collection<TMethod> methods = searchAndParseMethods(e);
		TBehavior behavior = searchAndParseBehavior(e);
		Collection<TPercolator> percolators = searchAndParsePercolators(e);
		Collection<TImplements> impls = searchAndParseImplements(e);
		Collection<TNamespace> namespaces = searchAndParseImports(e);
		
		TamagoCCLogger.infoln(3,"End Parsing of the composite "+ name+" in module "+module);

		return new TIComposite(TITamago.extractNameWithoutParamInfo(name),
				module,definitions,exports,properties,provides,requires,binds,invariants,behavior,methods,percolators,impls,namespaces,
				TITamago.extractParametrizedTypesFromName(name));
	}

	private Collection<TDefinition> parseDefinitions(Iterator<?> ite)
	throws TamagoCCException
	{
		ArrayList<TDefinition> al = new ArrayList<TDefinition>();
		while(ite.hasNext()) {
			Element e = (Element)ite.next();
			al.add(parseDefinition(e));
		}
		return al;
	}
	private TDefinition parseDefinition(Element e)
	throws TamagoCCException
	{
		String component = e.getAttribute("label");
		String type = e.getAttribute("type");
		String module = e.getAttribute("module");
		return new TIDefinition(type,module,component);
	}

	private Collection<TExport> parseExports(Iterator<?> ite) 
	throws TamagoCCException
	{
		ArrayList<TExport> al = new ArrayList<TExport>();
		while(ite.hasNext()) {
			Element e = (Element)ite.next();
			al.add(parseExport(e));
		}
		return al;
	}
	private TExport parseExport(Element e) throws TamagoCCException
	{
		String service = e.getAttribute("service");
		String module = e.getAttribute("module");


		Element eprovider = findChild(e,"provider");
		TExpression provider = parseExpression(findFirstElementChild(eprovider));

		return new TIExport(service,module,provider);
	}


	private Collection<TBind> parseBinds(Iterator<?> ite) throws TamagoCCException
	{
		ArrayList<TBind> al = new ArrayList<TBind>();
		while(ite.hasNext()){
			Element e = (Element)ite.next();
			al.add(parseBind(e));
		}
		return al; 
	}
	private TBind parseBind(Element e) throws TamagoCCException
	{
		String provider = e.getAttribute("provider");
		String requirer = e.getAttribute("requirer");
		String label = e.getAttribute("label");
		if(label.equals(""))
			label = requirer;

		String servicename = e.getAttribute("service");
		String module = e.getAttribute("module");

		if((servicename.length()>0) &&(module.length()>0)) {
			return new TIBind(provider,requirer,label,servicename,module);
		}
		else {
			return new TIBind(provider,requirer,label);
		}
	}


	// -----------------------------------------------------------------------------

	private Collection<TProperty> parseProperties(Iterator<?> ite) throws TamagoCCException
	{
		ArrayList<TProperty> al = new ArrayList<TProperty>();
		while(ite.hasNext()){
			Element e = (Element)ite.next();
			al.add(parseProperty(e));
		}
		return al; 
	}

	private TProperty parseProperty(Element e) throws TamagoCCException 
	{
		String name = e.getAttribute("name");
		String atype = e.getAttribute("type");
		String aaccess = e.getAttribute("access");

		TType type = TIType.generateType(atype);
		TAccess access = new TIAccess(aaccess);

		return new TIProperty(name,type,access);
	}

	private TBehavior parseBehavior(Element e) throws TamagoCCException
	{
		Element estates = findChild(e,"states"); 
		Collection<TState> states = parseStates(findChilds(estates,"state"));

		Element etransitions = findChild(e,"transitions");
		Collection<TTransition> transitions = parseTransitions(findChilds(etransitions,"transition"));

		String defaultState = e.getAttribute("default");

		if(defaultState.trim().length() == 0)
			throw new TamagoCCException("Not default attribute in behavior");
		// DONE : mettre l'attribut obligatoire dans le xschema

		return new TIBehavior(states,transitions,defaultState);
	}


	private Collection<TState> parseStates(Iterator<?> ite) throws TamagoCCException
	{
		ArrayList<TState> al = new ArrayList<TState>();
		while(ite.hasNext()){
			Element e = (Element)ite.next();
			al.add(parseState(e));
		}
		return al; 
	}

	private Collection<TTransition> parseTransitions(Iterator<?> ite) throws TamagoCCException
	{
		ArrayList<TTransition> al = new ArrayList<TTransition>();
		while(ite.hasNext()){
			Element e = (Element)ite.next();
			al.add(parseTransition(e));
		}
		return al; 
	}

	private TState parseState(Element e)  throws TamagoCCException
	{
		String name = e.getAttribute("name");

		Collection<TAllow> allows = parseAllows(findAllChilds(e));

		return new TIState(name,allows);
	}

	private Collection<TAllow> parseAllows(Iterator<?> ite) throws TamagoCCException
	{
		ArrayList<TAllow> al = new ArrayList<TAllow>();
		while(ite.hasNext()){
			Element e = (Element)ite.next();
			al.add(parseAllow(e));
		}
		return al; 
	}

	private TAllow parseAllow(Element e)  throws TamagoCCException
	{
		String idmethod = e.getAttribute("method");        
		return new TIAllow(idmethod);
	}

	private TTransition parseTransition(Element e)  throws TamagoCCException
	{
		String from = e.getAttribute("from");
		String to = e.getAttribute("to");
		String with = e.getAttribute("with");

		try {
			Element condition = findChild(e,"condition");
			TExpression exprcond = parseExpression(findFirstElementChild(condition));
			TamagoCCLogger.infoln(3,"-- Transition with condition --");
			return new TITransition(from,to,with,exprcond);
		}
		catch(TamagoCCException exc) {
			TamagoCCLogger.infoln(3,"-- Transition without condition --");
		}        
		return new TITransition(from,to,with);
	}

	private TVariable parseVariable(Element e) throws TamagoCCException {
		String name = e.getAttribute("name");
		String type = e.getAttribute("type");
		if(name.trim().length() == 0)
			throw new TamagoCCException("Variable without name");
		try {
			Element eindex = findChild(e, "index");
			TExpression index = parseExpression(findFirstElementChild(eindex));
			if(type.trim().length() == 0)
				return new TIVariable(name,index);
			else
				return new TIVariable(name,true,type,index);
		}
		catch(TamagoCCNotFoundChild tnfc) {
			if(type.trim().length() == 0)
				return new TIVariable(name);
			else
				return new TIVariable(name,true,type);	
		}
	}

	private Collection<TExtendService> parseExtends(Iterator<?> m)
	throws TamagoCCException
	{
		ArrayList<TExtendService> al = new ArrayList<TExtendService>();
		while(m.hasNext()) {
			Element e = (Element)m.next();
			String tagname = e.getTagName();
			if("refine".equals(tagname))
				al.add(parseRefine(e));
			else if("include".equals(tagname))
				al.add(parseInclude(e));
			else
				throw new TamagoCCException("Incorrect service inheritance : " + tagname);
		}
		return al;
	}

	private TRefineService parseRefine(Element r)
	throws TamagoCCException
	{
		String service = r.getAttribute("service");
		String module = r.getAttribute("module");


		return new TIRefineService(service,module,(TService)pool.getTreeAbstractSyntax(service,module));
	}

	private TIncludeService parseInclude(Element r)
	throws TamagoCCException
	{
		String service = r.getAttribute("service");
		String module = r.getAttribute("module");

		TService s = (TService)pool.getTreeAbstractSyntax(service,module);
		return new TIIncludeService(service,module,s);
	}

	private TNamespace parseUsing(Element e)throws TamagoCCException {
		Element pack = findChild(e,"package");
		String spack = pack.getAttribute("value");
		String type = "";
		try {
			findChild(pack, "anyentity");
			type="*"; 
		}
		catch(TamagoCCNotFoundChild lk) {
			
		}
		try {
			Element sub = findChild(pack, "entity");
			type=sub.getAttribute("value"); 
		}
		catch(TamagoCCNotFoundChild lk) {
			
		}
		if(type.length() == 0)
			throw new TamagoCCException("Malformed using package expression");
		return new TINamespace(spack,type);
	}
	
	private Collection<TNamespace> parseImports(Iterator<Element> m) throws TamagoCCException {
		ArrayList<TNamespace> al = new ArrayList<TNamespace>();
		while(m.hasNext()) {
			al.add(parseUsing(m.next()));
		}
		return al;
	}
	
	private Collection<TNamespace> searchAndParseImports(Element e)
	throws TamagoCCException
	{

		Collection<TNamespace> list;
		try {
			Element impls = findChild(e,"imports");
			TamagoCCLogger.println(3,"\t-- find some used namespace");
			list = parseImports(findAllChilds(impls));
		}
		catch(TamagoCCNotFoundChild noimpls) {
			// on cherche s'il y a au moins 1 invariant
			list = new ArrayList<TNamespace>(1);		
		}
		return list;
	}
}
