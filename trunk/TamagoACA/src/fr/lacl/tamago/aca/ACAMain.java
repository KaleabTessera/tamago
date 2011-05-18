/**
 * 
 */
package fr.lacl.tamago.aca;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import tamagocc.TamagoCC;
import tamagocc.api.TExpression;
import tamagocc.api.TInState;
import tamagocc.api.TMethod;
import tamagocc.api.TOpeName;
import tamagocc.api.TExpression.ExprType;
import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder;
import tamagocc.generator.TamagoCCIGenerator;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.impl.GIComponentContainer;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TICall;
import tamagocc.impl.TIComposant;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIInLabel;
import tamagocc.impl.TIInState;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TINoContract;
import tamagocc.impl.TIOperator;
import tamagocc.impl.TIPercolator;
import tamagocc.impl.TIProperty;
import tamagocc.impl.TIType;
import tamagocc.impl.TIVariable;
import tamagocc.javasource.TamagoCCJavaSourceBuilder;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.Pair;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.TamagoCCPrintTBehavior;
import tamagocc.util.TamagoCCToXml;
import fr.lacl.tamago.aca.convert.BridgeState;
import fr.lacl.tamago.aca.exception.ACATermVisitorException;
import fr.lacl.tamago.aca.exception.XmlTermToTermException;
import fr.lacl.tamago.aca.term.Atomic;
import fr.lacl.tamago.aca.term.Logic;
import fr.lacl.tamago.aca.term.OnEnum;
import fr.lacl.tamago.aca.term.Processus;
import fr.lacl.tamago.aca.term.Sod;
import fr.lacl.tamago.aca.term.util.XmlTermToTerm;
import fr.lacl.tamago.aca.term.Parstrong;

/**
 * @author hakim
 *
 */
public class ACAMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TamagoCCLogger.setLevel(3);
		try {
			try {
				TamagoCC.initialize(args);
	    		
	    	}
	    	catch(TamagoCCException exception) {
	    		TamagoCCLogger.println(1,"the initialisation of TamagoCC fail.");
	    		TamagoCCLogger.infoln(exception);
	    		System.exit(4);
	    	} catch (LineParserException e) {
	    		TamagoCCLogger.println(1,"Parsing of the command line fail.");
	    		TamagoCCLogger.infoln(e);
	    		System.exit(5);
			}
	    	final TamagoCCJavaSourceBuilder builder = new TamagoCCJavaSourceBuilder();
			
			TamagoCCIGenerator igenerator = new TamagoCCIGenerator() {
				@Override
				public TamagoCCGeneratorTargetLanguageBuilder getBuilder() {
					return builder;
				}
			};
			
	    	
			FileInputStream fis = new FileInputStream("result.xml");
			Processus prop = XmlTermToTerm.convert(ACAParser.unmarshall(fis));
			
			String name = prop.getName();
			String namepackage = prop.getModule();
			TamagoCCLogger.println(3, " Search functional contract: ");
			TamagoCCLogger.println(3, " Name: "+name);
			TamagoCCLogger.println(3, " Module: "+namepackage);
			
			/*
			Sod sod1 = new Sod(OnEnum.USER, new Atomic("action1"), new Atomic("action2"));
			Sod sod2 = new Sod(OnEnum.USER, new Atomic("action2"), new Atomic("action4"));
			//Sod sod3 = new Sod(OnEnum.USER, new Atomic("action5"), new Atomic("action6"));
			Atomic a1 = new Atomic("action1");
			Atomic a2 = new Atomic("action2");
			
			Parstrong t1 = new Parstrong();
			t1.addTerm(sod1);
			t1.addTerm(sod2);
			*/
			GenerateBehavior gb = new GenerateBehavior(prop.getTerm());
			gb.convert();
			
			TIBehavior behavior = new TIBehavior(gb.getStates(), gb.getTransitions(), gb.getInit().getState());
			// TamagoCCPrintTBehavior print = new TamagoCCPrintTBehavior(behavior, "Automate");
			// print.setVisible(true);
			
			GIComponentContainer component = (GIComponentContainer) TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(name, namepackage);
			
			
			TIComposant newcomponent = new TIComposant(name, namepackage);
			newcomponent.setName(newcomponent.getName()+"ACA");
			newcomponent.setBehavior(behavior);
			enrichMethod(component,newcomponent,gb,prop);
			FileOutputStream os = new FileOutputStream("toto.txt");
			TamagoCCToXml.generateXMLFile(newcomponent, os);
			newcomponent.addAllowedPercolators(new TIPercolator("plugin"));
			TamagoCC.setOutputDir("output");
			TamagoCC.generate(newcomponent);
			
			/*TamagoCCLogger.println(3,"Generation of the container for ACA ");
			CAComponentGenerator generator = new CAComponentGenerator(igenerator,component);
			AIEntity entity = (AIEntity)generator.getASTEntity();
			entity.addImplement(new AIImplements(AIType.generateType(namepackage+"."+ name+"Container")));
			entity.setInherit(new AIInherit("TamagoCCContainerImpl",new AIModule("tamago.ext.tamagocc")));
			File dirout = new File(TamagoCC.getOutputDir());
			TamagoCCLogger.println(3,"Generation of the output in "+builder.getLanguage()+" ...");
			TamagoCCGeneratorTargetLanguage tl = builder.getTargetLanguage(entity, dirout);
			tl.generate();
			TamagoCCLogger.println(3,"End generation of the container ACA");
			*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TamagoCCException e) {
			e.printStackTrace();
		} catch (XmlTermToTermException e) {
			e.printStackTrace();
		} catch (ACATermVisitorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void enrichMethod(GIComponentContainer component,
			TIComposant newcomponent, GenerateBehavior gb, Processus prop)
	{
		for (String methid : component.getAllMethodID()) {
			GMethod meth = component.getMethod(methid);
			TIMethod nmeth = new TIMethod(meth);
			newcomponent.addMethod(nmeth);
			if(gb.getConditions().containsKey(methid)) {
				ArrayList<TExpression> cond = new ArrayList<TExpression>();
				ArrayList<Pair<String, Logic>> map = gb.getConditions().get(methid);
				HashMap<String, ArrayList<Logic>> logInstate = new HashMap<String, ArrayList<Logic>>();
				for (Pair<String, Logic> pair : map) {
					if(logInstate.containsKey(pair.getL())) {
						logInstate.get(pair.l()).add(pair.r());
					}
					else {
						logInstate.put(pair.l(), new ArrayList<Logic>());
						logInstate.get(pair.l()).add(pair.r());
					}
				}
				
				for (Entry<String, ArrayList<Logic>> set : logInstate.entrySet()) {
					TIInState instate = new TIInState();
					instate.addInState(set.getKey());
					TExpression expr = convertToExpression(set.getValue());
					if(expr.getCat() != ExprType.NOCONTRACT) {
						TIOperator impl = new TIOperator(TOpeName.opImply);
						impl.addOperand(instate);
						impl.addOperand(expr);
						cond.add(impl);
					}
				}
				
				if(cond.size() > 1)
					((TICondition)nmeth.getPrecondition()).setExpression(new TIOperator(TOpeName.opAnd,cond));
				else if(cond.size() == 1)
					((TICondition)nmeth.getPrecondition()).setExpression(cond.get(0));	
			}
		} // end while
		
	}

	private static TExpression convertToExpression(ArrayList<Logic> value) {
		ArrayList<TExpression> exprs = new ArrayList<TExpression>();
		for (Logic logic : value) {
			exprs.add(convertToExpression(logic));
		}
		switch(exprs.size()) {
		case 1:
			return exprs.get(0);
		case 2:
			return new TIOperator(TOpeName.opAnd,exprs);
		default:
			return new TINoContract();
		}
	}

	public static final String TYPE_QUAD_ACCESS_CONTROL = "tamago.ext.aca.Quad";
	
	private static TExpression convertToExpression(Logic l) {
		TICall call;
		String meth = "";
		switch(l.getOn()) {
		case USER:
			meth = "user";
			break;
		case ROLE:
			meth = "role";
			break;
		case ORGANISATION:
			meth = "org";
			break;
		}
		meth += "_Is";
		
		switch(l.getMode()) {
		case READ_OBL:
		case READ_SOD:
			TamagoCCLogger.println(3, "READ OPERATOR");
			break;
		case WRITE_OBL:
		case WRITE_SOD:
			TamagoCCLogger.println(3, "WRITE OPERATOR");
			break;
		}
		
		//meth += 
		
		call = new TICall(meth);
		call.addArgument(new TIVariable(l.getVariable(), true,"String"));
		
		TIInLabel inlabel = new TIInLabel(new TIVariable("quad",true,TYPE_QUAD_ACCESS_CONTROL), call);
		return inlabel;
	}

}
