/**
 * 
 */
package tamago.aca.visitor;

import tamago.aca.term.ACA;
import tamago.aca.term.Info;
import tamago.aca.term.Perms;
import tamago.aca.term.Quad;
import tamagocc.api.TExpression;
import tamagocc.api.TMethod;
import tamagocc.api.TOpeName;
import tamagocc.api.TService;
import tamagocc.api.TTamago;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGenerator;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.impl.TIAccess;
import tamagocc.impl.TIComposant;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIInLabel;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TIOperator;
import tamagocc.impl.TIPercolator;
import tamagocc.impl.TIProperty;
import tamagocc.impl.TIRequire;
import tamagocc.impl.TIString;
import tamagocc.impl.TIType;
import tamagocc.impl.TIVariable;
import tamagocc.javasource.TamagoCCJavaSourceBuilder;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;


/**
 * @author hbelhaou
 *
 */
public class ConvertACAtoCDL{

	private ACA aca;
	private TIComposant tamagoaca;
	
	/**
	 * 
	 */
	public ConvertACAtoCDL(ACA aca) {
		this.aca = aca;
		tamagoaca = new TIComposant(aca.getInfo().getModelName()+"ACA", aca.getInfo().getModelModule());
	}
	
	public GTamagoEntity convert() throws TamagoCCException {
		// Transformation de base
		Info info = aca.getInfo();
		TTamago tamago = TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(info.getModelName(), info.getModelModule());
		tamagoaca.addRequire(new TIRequire("acamodel", tamago.getName()	, tamago.getModule(), (TService)tamago));
		tamagoaca.addAllowedPercolators(new TIPercolator("plugin"));
		
		TIProperty property = new TIProperty("historic", TIType.generateType("tamago.ext.aca2.Historic"), new TIAccess("read"));
		tamagoaca.addProperty(property);
		
		
		
		TIOperator pre_user = new TIOperator(TOpeName.opOr);
		TIOperator pre_role = new TIOperator(TOpeName.opOr);
		TIOperator pre_org = new TIOperator(TOpeName.opOr);
		
		TIOperator and_pre = new TIOperator(TOpeName.opAnd);
		and_pre.addOperand(pre_user);
		and_pre.addOperand(pre_role);
		and_pre.addOperand(pre_org);
		
		for (String suser : aca.getUsers()) {
			TIString str = new TIString(suser);
			TIVariable aca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
			TIVariable user = new TIVariable("user",true,"String");
			TIInLabel inaca = new TIInLabel(aca, user);
			TIOperator opuser = new TIOperator(TOpeName.opEg);
			opuser.addOperand(inaca);
			opuser.addOperand(str);
			pre_user.addOperand(opuser);
		}
		
		for (String srole : aca.getRoles()) {
			TIString str = new TIString(srole);
			TIVariable aca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
			TIVariable role = new TIVariable("role",true,"String");
			TIInLabel inaca = new TIInLabel(aca, role);
			TIOperator opuser = new TIOperator(TOpeName.opEg);
			opuser.addOperand(inaca);
			opuser.addOperand(str);
			pre_role.addOperand(opuser);
		}
		
		for (String sorg : aca.getOrgs()) {
			TIString str = new TIString(sorg);
			TIVariable aca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
			TIVariable org = new TIVariable("org",true,"String");
			TIInLabel inaca = new TIInLabel(aca, org);
			TIOperator opuser = new TIOperator(TOpeName.opEg);
			opuser.addOperand(inaca);
			opuser.addOperand(str);
			pre_org.addOperand(opuser);
		}
		
		// generation pour les methods de securite
		for (String mid : aca.getActions()) {
			TMethod method = tamago.getDeclaredMethodID(mid);
			
			TIMethod methsec = new TIMethod(method);
			TIOperator and = new TIOperator(TOpeName.opAnd);
			methsec.setPrecond(new TICondition(and));
			
			Perms perms = aca.getPerms();
			for (Quad quad : perms) {
				if(mid.equals(quad.getAction())) {
					and.addOperand(eq(quad));
				}
			}
			
			for (Quad quad : aca.getBans()) {
				if(mid.equals(quad.getAction())) {
					and.addOperand(eq(quad));
				}
			}
			and.addOperand(and_pre);
			tamagoaca.addMethod(methsec);
		}
		
		GTamagoEntity entity = TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(tamagoaca);
		
		TamagoCCPercolation.initialisation();
		TamagoCCJavaSourceBuilder builder = new TamagoCCJavaSourceBuilder();
		TamagoCCGenerator generator = new TamagoCCGenerator(builder, entity, "examples", true, true, true);
		generator.generate();
		
		return entity;
	}

	
	
	private TExpression eq(Quad quad) {
		TIOperator and = new TIOperator(TOpeName.opAnd);
		if(!"_".equals(quad.getUser())) {
			TIString str = new TIString(quad.getUser());
			TIVariable aca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
			TIVariable user = new TIVariable("user",true,"String");
			TIInLabel inaca = new TIInLabel(aca, user);
			
			TIOperator opuser;
			if(quad.isNegUser())
				opuser = new TIOperator(TOpeName.opNe);
			else
				opuser = new TIOperator(TOpeName.opEg);
			opuser.addOperand(inaca);
			opuser.addOperand(str);
			and.addOperand(opuser);
		}
		
		if(!"_".equals(quad.getRole())) {
			TIString str = new TIString(quad.getRole());
			TIVariable aca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
			TIVariable role = new TIVariable("role",true,"String");
			TIInLabel inaca = new TIInLabel(aca, role);
			
			TIOperator oprole;
			if(quad.isNegRole())
				oprole = new TIOperator(TOpeName.opNe);
			else
				oprole = new TIOperator(TOpeName.opEg);
			oprole.addOperand(inaca);
			oprole.addOperand(str);
			and.addOperand(oprole);
		}
		
		if(!"_".equals(quad.getOrg())) {
			TIString str = new TIString(quad.getOrg());
			TIVariable aca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
			TIVariable org = new TIVariable("org",true,"String");
			TIInLabel inaca = new TIInLabel(aca, org);
			
			TIOperator oporg;
			if(quad.isNegOrg())
				oporg = new TIOperator(TOpeName.opNe);
			else
				oporg = new TIOperator(TOpeName.opEg);
			oporg.addOperand(inaca);
			oporg.addOperand(str);
			and.addOperand(oporg);
		}
		return and;
	}

}
