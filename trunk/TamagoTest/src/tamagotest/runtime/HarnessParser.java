/**
 * 
 */
package tamagotest.runtime;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AAffectation;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIFor;
import tamagocc.ast.impl.AIForeach;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AILanguageExpr;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINoExpression;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIRead;
import tamagocc.ast.impl.AIReal;
import tamagocc.ast.impl.AISelf;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIWhile;
import tamagocc.exception.TamagoCCNotFoundChild;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.Triplet;
import tamagotest.TamagoTestCase;
import tamagotest.TamagoTestException;
import tamagotest.TamagoTestScenario;

/**
 * @author Hakim Belhaouari
 *
 */
public class HarnessParser {

	private Element findFirstElementChild(Element e)
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

	private Element findChild(Element e,String tagname)
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

	private Iterable<Element> findChilds(Element e,String tagname)
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
		return retour;
	}

	private Iterable<Element> findAllChilds(Element e)
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
		return retour;
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
	
	/**
	 * 
	 */
	public HarnessParser() {
	}

	
	public TamagoTestPerform parse(String xml) throws ParserConfigurationException, TamagoTestException, TamagoCCNotFoundChild {
		TamagoCCLogger.infoln(1,"Parsing of URI:"+xml+"...");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = null;
		try {
			//new URI(xml); // on test si c'est une syntaxe correct
			d = db.parse(xml);
		}
		catch(Exception e) {


			TamagoCCLogger.infoln(1,"*Warning* : The argument is not a correct URI. I will deal as a stream");
			try {
				//StringBufferInputStream sb = new StringBufferInputStream(xml);
				StringReader sr = new StringReader(xml);
				InputSource is = new InputSource(sr);
				d = db.parse(is);
			}
			catch(Exception e2) {
				throw new TamagoTestException(e);
			}
		}
		TamagoTestPerform buf = parseXML(d);
		
		TamagoCCLogger.infoln(1,"Parsing [OK]");
		return buf;
	}
	
	public TamagoTestPerform parse(InputStream xml) throws ParserConfigurationException, TamagoTestException, TamagoCCNotFoundChild {
		TamagoCCLogger.infoln(1,"Parsing of stream...");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = null;
		try {
			//new URI(xml); // on test si c'est une syntaxe correct
			d = db.parse(xml);
		}
		catch(Exception e) {
			throw new TamagoTestException(e);
		}
		TamagoTestPerform buf = parseXML(d);
		TamagoCCLogger.infoln(1,"Parsing [OK]");
		return buf;
	}


	private TamagoTestPerform parseXML(Node n) throws TamagoTestException, TamagoCCNotFoundChild {
		switch(n.getNodeType()) {
		case Node.DOCUMENT_NODE:
			Document d = (Document)n;
			return this.parseXML(d.getDocumentElement());	
		case Node.ELEMENT_NODE: {
			Element e = (Element)n;
			//NodeList nl = e.getChildNodes();
			String tag = e.getTagName();
			if("tamagotest".equals(tag)) {
				return parseTest(e);
			}
			else {
				throw new TamagoTestException("Unknow TagName of element : "+tag);
			}
		}
		default: {
			throw new TamagoTestException("Unknow node type : "+n.getNodeName());
		}

		}
	}

	private transient ContractInformation ci;
	
	private TamagoTestPerform parseTest(Element e) throws TamagoTestException, TamagoCCNotFoundChild {
		ContractInformation ci = searchAndParseContractInformation(e);
		this.ci = ci;
		Collection<TamagoTestScenario> coll = parseAllScenario(findChilds(e, "scenario"));
		return new TamagoTestPerform(ci,coll);
	}

	private Collection<TamagoTestScenario> parseAllScenario(Iterable<Element> name) throws TamagoTestException, TamagoCCNotFoundChild {
		ArrayList<TamagoTestScenario> res = new ArrayList<TamagoTestScenario>();
		for (Element element : name) {
			res.add(parseScenario(element));
		}
		return res;
	}

	private TamagoTestScenario parseScenario(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		element.getAttribute("size");
		TamagoTestScenario tts = new TamagoTestScenario(ci.getPercolator());
		for (Element e : findChilds(element, "testcase")) {
			tts.add(parseTestCase(e));
		}
		return tts;
	}

	private TamagoTestCase parseTestCase(Element e) throws TamagoTestException, TamagoCCNotFoundChild {
		
		Element callid = findChild(e, "call");
		Element datatest = findChild(e, "datatest");
		
		Element precond = findChild(e, "precondition");
		Element initpre = findChild(e, "init-precondition");
		
		Element eatpreoracle= findChild(e, "pre-oracle");
		Element initora = findChild(e, "init-oracle");
		Element oracle = findChild(e, "oracle");
		
		String mid = callid.getAttribute("name");
		
		AExpression exprpre = parseExpression(findFirstElementChild(precond));
		AInstruction instpre = null;
		try {
			 instpre = parseInstruction(findFirstElementChild(initpre));
		}
		catch(TamagoCCNotFoundChild child) {
			instpre = AINoInstruction.getNoInstruction();
		}
		
		AExpression expror = parseExpression(findFirstElementChild(oracle));
		AInstruction instor;
		try {
			instor = parseInstruction(findFirstElementChild(initora));
		}
		catch(TamagoCCNotFoundChild child) {
			instor = AINoInstruction.getNoInstruction();
		}
		AInstruction atpreoracle;
		try {
			atpreoracle = parseInstruction(findFirstElementChild(eatpreoracle));
		}
		catch(TamagoCCNotFoundChild child) {
			atpreoracle = AINoInstruction.getNoInstruction();
		}
		
		Map<String, Triplet<AExpression, AInstruction,AInstruction>> values = searchAndParseParameters(datatest);
		
		
		TamagoTestCase ttc = new TamagoTestCase(mid,instpre,exprpre,atpreoracle,instor,expror,values);
		
		return ttc;
	}

	private Map<String, Triplet<AExpression, AInstruction,AInstruction>> searchAndParseParameters(Element datatest) throws TamagoCCNotFoundChild, TamagoTestException {
		Hashtable<String, Triplet<AExpression, AInstruction, AInstruction>> table = new Hashtable<String, Triplet<AExpression, AInstruction, AInstruction>>();
		Iterable<Element> parameters = findChilds(datatest, "parameter");
		for (Element eparam : parameters) {
			String key = eparam.getAttribute("name");
			Element evalue = findChild(eparam,"value");
			AExpression value = parseExpression(findFirstElementChild(evalue));
			
			AInstruction init;
			AInstruction postinit;
			try {
				Element einit = findChild(eparam, "initial");
				init = parseInstruction(findFirstElementChild(einit));
				
			}
			catch (TamagoCCNotFoundChild e) {
				init = AINoInstruction.getNoInstruction();
			}
			
			try {
				Element einit = findChild(eparam, "postinitial");
				postinit = parseInstruction(findFirstElementChild(einit));
				
			}
			catch (TamagoCCNotFoundChild e) {
				postinit = AINoInstruction.getNoInstruction();
			}
			
			table.put(key, new Triplet<AExpression, AInstruction, AInstruction>(value,init,postinit));
		}
		
		return table;
	}

	private AInstruction parseInstruction(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		if("ifthenelse".equals(element.getTagName())) {
			return parseIfThenElse(element);
		}
		else if ("while".equals(element.getTagName())) {
			return parseWhile(element);
		}
		else if("noinstruction".equals(element.getTagName()))
			return AINoInstruction.getNoInstruction();
		else if("for".equals(element.getTagName())) {
			return parseFor(element);
		}
		else if("sequence".equals(element.getTagName())) {
			return parseSequence(element);
		}
		else if("expression".equals(element.getTagName())) {
			return parseInstExpression(element);
		}
		else if("affect".equals(element.getTagName())) {
			return parseAffect(element);
		}
		else if("set-value".equals(element.getTagName())) {
			return parseInitialisation(element);
		}
		else if("foreach".equals(element.getTagName())) {
			return parseForeach(element);
		}
		else if("initialisation".equals(element.getTagName())) {
			return parseInitialisation(element);
		}
		else {
			throw new TamagoTestException("Unkown tag instruction: "+element.getTagName());
		}
	}

	private AInstruction parseForeach(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		String avar = element.getAttribute("variable");
		String atype = element.getAttribute("type");
		
		AIVariable var = new AIVariable(new AIIdent(avar));
		AType type = AIType.generateType(atype);
		
		Element ecoll = findChild(element, "collection");
		Element ebody = findChild(element,"body");
		
		AExpression coll = parseExpression(findFirstElementChild(ecoll)); 		
		AInstruction body = parseInstruction(findFirstElementChild(ebody));
		
		return new AIForeach(var, type,coll,body);
	}

	private AInstruction parseInitialisation(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		Element evar = findChild(element, "variable");
		Element eval = findChild(element, "value");
		
		String type = evar.getAttribute("type");
		AVariable var = (AVariable) parseExpression(findFirstElementChild(evar));
		AExpression val = parseExpression(findFirstElementChild(eval));
		
		if(val.getExpressionType() != AExpression.NOEXPRESSION)
			return new AIInitialisation(var.getIdent(),AIType.generateType(type),val);
		else
			return new AIInitialisation(var.getIdent(),AIType.generateType(type));
	}

	private AInstruction parseAffect(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		Element evar = findChild(element, "variable");
		Element eval = findChild(element, "value");
		
		AExpression var = parseExpression(findFirstElementChild(evar));
		AExpression val = parseExpression(findFirstElementChild(eval));
		
		AIAffectation affect = new AIAffectation((AVariable) var,val);
		return affect;
	}

	private AInstruction parseInstExpression(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		AExpression expr = parseExpression(findFirstElementChild(element));
		AIInstExpression iexpr = new AIInstExpression(expr);
		return iexpr;
	}

	private AInstruction parseSequence(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		AISequence seq = new AISequence();
		for (Element inst : findAllChilds(element)) {
			seq.addInstruction(parseInstruction(inst));
		}
		return seq;
	}

	private AInstruction parseFor(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		Element eaffect = findChild(element, "initialisation");
		Element econd = findChild(element, "condition");
		Element eincr = findChild(element, "increment");
		Element ebody = findChild(element, "body");
		
		AAffectation affect = (AAffectation) parseInstruction(findFirstElementChild(eaffect));
		AExpression cond = parseExpression(findFirstElementChild(econd));
		AInstruction incr = parseInstruction(findFirstElementChild(eincr));
		AInstruction body = parseInstruction(findFirstElementChild(ebody));
		
		AIFor f = new AIFor(affect,cond,incr,body);
		return f;
	}

	private AInstruction parseWhile(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		
		Element econd = findChild(element, "condition");
		Element ebody = findChild(element, "body");
		
		AExpression cond = parseExpression(findFirstElementChild(econd));
		AInstruction body = parseInstruction(findFirstElementChild(ebody));
		
		AIWhile w = new AIWhile(cond,body);
		return w;
	}

	private AInstruction parseIfThenElse(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		Element econd = findChild(element, "condition");
		Element econs = findChild(element, "then");
		Element ealt  = findChild(element, "else");
		
		AExpression cond = parseExpression(findFirstElementChild(econd));
		AInstruction cons = parseInstruction(findFirstElementChild(econs));
		AInstruction alt = parseInstruction(findFirstElementChild(ealt));
		
		
		return new AIIfThenElse(cond,cons,alt);
	}

	private AExpression parseExpression(Element element) throws TamagoCCNotFoundChild, TamagoTestException {
		if("integer".equals(element.getTagName())) {
			return parseInt(element);
		}
		else if("real".equals(element.getTagName())) {
			return parseReal(element);
		}
		else if("string".equals(element.getTagName())) {
			return parseString(element);
		}
		else if("operator".equals(element.getTagName())) {
			return parseOperator(element);
		}
		else if("bool".equals(element.getTagName())) {
			return parseBool(element);
		}
		else if("not".equals(element.getTagName())) {
			return parseNot(element);
		}
		else if("noexpression".equals(element.getTagName())) {
			return AINoExpression.getNoExpression();
		}
		else if("call".equals(element.getTagName())) {
			return parseCall(element);
		}
		else if("read".equals(element.getTagName())) {
			return parseRead(element);
		}
		else if("variable".equals(element.getTagName())) {
			return parseVariable(element);
		}
		else if("in".equals(element.getTagName())) {
			return parseInLabel(element);
		}
		else if("new".equals(element.getTagName())) {
			return parseNew(element);
		}
		else if("tamagonew".equals(element.getTagName())) {
			return parseTamagoNew(element);
		}
		else if("self".equals(element.getTagName())) {
			return new AISelf();
		}
		else if("convert".equals(element.getTagName())) {
			return parseConvert(element);
		}
		else if("nil".equals(element.getTagName())) {
			return parseNil(element);
		}
		else if("lang-expr".equals(element.getTagName())) {
			return parseLang(element);
		}
		else {
			throw new TamagoTestException("Unkown tag expression: "+element.getTagName());
		}
	}

	private AExpression parseInt(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String val = element.getAttribute("value");
		return new AIInteger(Integer.parseInt(val));
	}

	private AExpression parseReal(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String real = element.getAttribute("value");
		return new AIReal(Double.parseDouble(real));
	}

	private AExpression parseString(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		return new AIString(element.getAttribute("value"));
	}

	private AExpression parseOperator(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		ArrayList<AExpression> res = new ArrayList<AExpression>();
		for (Element e : findAllChilds(element)) {
			res.add(parseExpression(e));
		}
		String operator = element.getAttribute("name");
		
		try {
			return new AIOperator(TOpeName.generate(operator),res);
		}
		catch(Exception e) {
			throw new TamagoTestException(e);
		}
	}

	private AExpression parseBool(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String val = element.getAttribute("value");
		return new AIBool(Boolean.parseBoolean(val));
	}

	private AExpression parseNot(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		AExpression expr = parseExpression(findFirstElementChild(element));
		return new AINot(expr);
	}

	private AExpression parseCall(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String name = element.getAttribute("name");
		ArrayList<AExpression> args = new ArrayList<AExpression>();
		Element eargs = findChild(element, "arguments");
		for (Element arg : findAllChilds(eargs)) {
			args.add(parseExpression(arg));
		}
		return new AICall(new AIIdent(name),args);
	}

	private AExpression parseRead(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String prop = element.getAttribute("property");
		return new AIRead(prop);
	}

	private AExpression parseVariable(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String name = element.getAttribute("name");
		try {
			Element fe = findChild(element, "index");
			AExpression index = parseExpression(findFirstElementChild(fe));
			AIVariable v = new AIVariable(new AIIdent(name),index);
			return v;
		}
		catch(TamagoCCNotFoundChild nf) {
			return new AIVariable(new AIIdent(name));
		}
	}

	private AExpression parseInLabel(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		AExpression scope = parseExpression(findFirstElementChild(findChild(element, "scope")));
		AExpression target = parseExpression(findFirstElementChild(findChild(element, "target")));
		return new AIInLabel(scope,target);
	}

	private AExpression parseNew(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		// TODO Auto-generated method stub
		throw new TamagoTestException("Not yet implemented");
	}

	private AExpression parseTamagoNew(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		// TODO Auto-generated method stub
		throw new TamagoTestException("Not yet implemented");
	}

	private AExpression parseConvert(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String ex = element.getAttribute("type");
		AExpression expr = parseExpression(findFirstElementChild(element));
		AIConvertType ct = new AIConvertType(AIType.generateType(ex),expr);
		return ct;
	}

	private AExpression parseNil(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		return new AINil();
	}

	private AExpression parseLang(Element element) throws TamagoTestException, TamagoCCNotFoundChild {
		String txt = findChildText(element);
		return new AILanguageExpr(txt);
	}

	private ContractInformation searchAndParseContractInformation(Element e) throws TamagoTestException, TamagoCCNotFoundChild  {
		Element contractinfo = findChild(e, "contract");
		
		Element percoinfo = findChild(e, "percolator");
		
		Element sceinfo = findChild(e, "scenarios");
		
		String name = contractinfo.getAttribute("name");
		String module = contractinfo.getAttribute("module");
		String perco = percoinfo.getAttribute("name");
		String count = sceinfo.getAttribute("count");
		
		ContractInformation ci = new ContractInformation(name,module,perco,Integer.parseInt(count));
		
		return ci;
	}
	
	public static void main(String args[]) {
		HarnessParser parser = new HarnessParser();
		
		String file = "/home/aliquando/Tamago/Tamago/TamagoExample/TME-Tamago/test1/test/BankAccount_report.xml";
		try {
			TamagoTestPerform perform =  parser.parse(file);
			perform.getContractInformation();
		} catch (TamagoCCNotFoundChild e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TamagoTestException e) {
			e.printStackTrace();
		}
	}
}
