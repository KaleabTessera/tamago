/**
 * 
 */
package tamago.aca.visitor;

import tamago.aca.term.ACA;
import tamago.aca.term.Info;
import tamago.aca.term.Obl;
import tamago.aca.term.Perms;
import tamago.aca.term.Quad;
import tamago.aca.term.Sod;
import tamagocc.api.TExpression;
import tamagocc.api.TMethod;
import tamagocc.api.TOpeName;
import tamagocc.api.TService;
import tamagocc.api.TTamago;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.impl.TIAccess;
import tamagocc.impl.TICall;
import tamagocc.impl.TIComposant;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIInLabel;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TIOperator;
import tamagocc.impl.TIPercolator;
import tamagocc.impl.TIProperty;
import tamagocc.impl.TIProvide;
import tamagocc.impl.TIRead;
import tamagocc.impl.TIString;
import tamagocc.impl.TIType;
import tamagocc.impl.TIVariable;
import tamagocc.util.TamagoCCPool;


/**
 * @author hbelhaou
 *
 */
public class ConvertACAtoCDL{

	private ACA aca;
	private TIComposant tamagoaca;
	private GTamagoEntity gentity;
	private boolean done;
	
	/**
	 * @param name 
	 * 
	 */
	public ConvertACAtoCDL(String name, ACA aca) {
		this.aca = aca;
		done = false;
		tamagoaca = new TIComposant(name, aca.getInfo().getModelModule());
	}
	
	public ACA getAca() {
		return aca;
	}

	public TIComposant getTEntity() {
		if(done)
			return tamagoaca;
		else
			return null;
	}

	public GTamagoEntity getGEntity() {
		return gentity;
	}

	public void convert() throws TamagoCCException {
		if(done)
			return;
		// Transformation de base
		Info info = aca.getInfo();
		TTamago tamago = TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(info.getModelName(), info.getModelModule());
		tamagoaca.addProvide(new TIProvide(tamago.getName()	, tamago.getModule(), (TService)tamago));
		tamagoaca.addAllowedPercolators(new TIPercolator("aca"));
		
		TIProperty property = new TIProperty("historic", TIType.generateType("tamago.ext.aca2.Historic"), new TIAccess("read"));
		tamagoaca.addProperty(property);
		
		TIProperty propPlay = new TIProperty("play", TIType.generateType("tamago.ext.aca2.Play"), new TIAccess("read"));
		tamagoaca.addProperty(propPlay);
		
		TIRead historic = new TIRead("historic");
		
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
			methsec.setId("");
			TIOperator and = new TIOperator(TOpeName.opAnd);
			
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
			{
				TIVariable vaca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
				// --------------------------------- SOD OBL
				for (Sod sod : aca.getSods()) {
					if(sod.getRight().getAction().equals(mid)) {
						// hasDone
						TICall mustDone = new TICall("mustDone");
						mustDone.addArgument(new TIString(sod.getLeft().getAction()));
						TIInLabel historicDOTmustDone = new TIInLabel(historic, mustDone);
						and.addOperand(historicDOTmustDone);
						
						// check target
						// historic.getSecuParamFromID(”deposit”).getUser()
						TICall getsecuparam = new TICall("getSecuParamFromID");
						getsecuparam.addArgument(new TIString(sod.getLeft().getAction()));
						TICall target = null;
						TIVariable vacatarget = null;
						switch(sod.getTarget()) {
						case USER:
							target = new TICall("getUser");
							vacatarget = new TIVariable("user",true,"java.lang.String");
							break;
						case ORG:
							target = new TICall("getOrg");
							vacatarget = new TIVariable("org",true,"java.lang.String");
							break;
						case ROLE:
							target = new TICall("getRole");
							vacatarget = new TIVariable("role",true,"java.lang.String");
							break;
						}
						TIInLabel getSecuParamDOTTarget = new TIInLabel(getsecuparam, target);
						TIInLabel historicDOTgetSecuParamDOTTarget = new TIInLabel(historic, getSecuParamDOTTarget);
						TIOperator opeqsod = new TIOperator(TOpeName.opNe);
						opeqsod.addOperand(new TIInLabel(vaca, vacatarget));
						opeqsod.addOperand(historicDOTgetSecuParamDOTTarget);
						and.addOperand(opeqsod);
					}
				}
				
				for (Obl obl : aca.getObls()) {
					if(obl.getRight().getAction().equals(mid)) {
						// hasDone
						TICall mustDone = new TICall("mustDone");
						mustDone.addArgument(new TIString(obl.getLeft().getAction()));
						TIInLabel historicDOTmustDone = new TIInLabel(historic, mustDone);
						and.addOperand(historicDOTmustDone);
						
						// check target
						// historic.getSecuParamFromID(”deposit”).getUser()
						TICall getsecuparam = new TICall("getSecuParamFromID");
						getsecuparam.addArgument(new TIString(obl.getLeft().getAction()));
						TIInLabel historicDOTgetSecuParam = new TIInLabel(historic, getsecuparam);
						TICall target = null;
						TIVariable vacatarget = null;
						switch(obl.getTarget()) {
						case USER:
							target = new TICall("getUser");
							vacatarget = new TIVariable("user",true,"java.lang.String");
							break;
						case ORG:
							target = new TICall("getOrg");
							vacatarget = new TIVariable("org",true,"java.lang.String");
							break;
						case ROLE:
							target = new TICall("getRole");
							vacatarget = new TIVariable("role",true,"java.lang.String");
							break;
						}
						TIInLabel historicDOTgetSecuParamDOTTarget = new TIInLabel(historicDOTgetSecuParam, target);
						TIOperator opeqobl = new TIOperator(TOpeName.opEg);
						opeqobl.addOperand(historicDOTgetSecuParamDOTTarget);
						opeqobl.addOperand(new TIInLabel(vaca, vacatarget));
						and.addOperand(opeqobl);
					}
				}
			}
			methsec.setPrecond(new TICondition(and));
			
			// make postcondition
			// post (historic.getLastSecuParam() == aca) && (historic.getLastAction() == "deposit");
			and = new TIOperator(TOpeName.opAnd);
			{
				TICall lastAction = new TICall("lastAction");
				TIInLabel historicDOTlastAction = new TIInLabel(historic, lastAction);
				TIString action = new TIString(mid);
				TIOperator op = new TIOperator(TOpeName.opEg);
				op.addOperand(action);
				op.addOperand(historicDOTlastAction);
				and.addOperand(op);
				// ----------------
				
				TICall lastSecuParam = new TICall("lastSecuParam");
				TIVariable vaca = new TIVariable("aca",true,"tamago.ext.aca2.ACA");
				TIInLabel historicDOTlastSecuParam = new TIInLabel(historic, lastSecuParam);
				op = new TIOperator(TOpeName.opEg);
				op.addOperand(vaca);
				op.addOperand(historicDOTlastSecuParam);
				and.addOperand(op);
				
				
			}
			methsec.setPostcond(new TICondition(and));
			tamagoaca.addMethod(methsec);
		}
		
		gentity = TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(tamagoaca);
		done = true;
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
