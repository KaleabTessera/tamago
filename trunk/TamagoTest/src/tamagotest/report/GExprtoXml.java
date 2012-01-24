/**
 * 
 */
package tamagotest.report;

import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAccess;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GCategory;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExistColl;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExistSet;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GForallRange;
import tamagocc.generic.api.GForallSet;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInitialisation;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GIsBound;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GObject;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GPreInitialisation;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GIQuantifierVariable;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class GExprtoXml implements TamagoCCGVisitor {

	private TamagoCCIndentator indent;
	
	/**
	 * 
	 */
	public GExprtoXml(TamagoCCIndentator indent) {
		this.indent = indent;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitBool(tamagocc.generic.api.GBool)
	 */
	public Object visitBool(GBool bool) throws TamagoCCException {
		indent.print("<bool value=\"");
		indent.print(""+bool.getValue());
		indent.print("\" />");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCall(tamagocc.generic.api.GCall)
	 */
	public Object visitCall(GCall call) throws TamagoCCException {
		indent.print("<call name=\"");
		indent.print(call.getName());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<arguments>");
		indent.shiftright();
		Iterator<GExpression> exprs = call.getArguments();
		while(exprs.hasNext()) {
			exprs.next().visit(this);
		}
		indent.shiftleft();
		indent.println("</arguments>");
		indent.shiftleft();
		indent.println("</call>");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitExpression(tamagocc.generic.api.GExpression)
	 */
	public Object visitExpression(GExpression expression)
			throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitInLabel(tamagocc.generic.api.GInLabel)
	 */
	public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
		indent.println("<in>");
		indent.shiftright();
		indent.println("<scope>");
		indent.shiftright();
		inlabel.getTarget().visit(this);
		indent.newline();
		indent.shiftleft();
		indent.println("</scope>");
		indent.println("<target>");
		indent.shiftright();
		inlabel.getSubExpression().visit(this);
		indent.newline();
		indent.shiftleft();
		indent.println("</target>");
		indent.shiftleft();
		indent.println("</in>");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitInteger(tamagocc.generic.api.GInteger)
	 */
	public Object visitInteger(GInteger interger) throws TamagoCCException {
		indent.print("<integer value=\"");
		indent.print(""+interger.getValue());
		indent.println("\" />");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNil(tamagocc.generic.api.GNil)
	 */
	public Object visitNil(GNil nil) throws TamagoCCException {
		indent.println("<nil />");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNoContract(tamagocc.generic.api.GNoContract)
	 */
	public Object visitNoContract(GNoContract nocontract)
			throws TamagoCCException {
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNot(tamagocc.generic.api.GNot)
	 */
	public Object visitNot(GNot not) throws TamagoCCException {
		indent.println("<not>");
		indent.shiftright();
		not.getTerm().visit(this);
		indent.shiftleft();
		indent.println("</not>");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitOperator(tamagocc.generic.api.GOperator)
	 */
	public Object visitOperator(GOperator operator) throws TamagoCCException {
		indent.print("<operator name=\"");
		indent.print(operator.getOperator().getOperator());
		indent.println("\" >");
		indent.shiftright();
		Iterator<GExpression> exprs = operator.getOperands();
		while(exprs.hasNext()) {
			GExpression expr = exprs.next();
			expr.visit(this);
			indent.newline();
		}
		indent.shiftleft();
		indent.println("</operator>");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitPre(tamagocc.generic.api.GAtPre)
	 */
	public Object visitPre(GAtPre atpre) throws TamagoCCException {
		/*indent.println("<atpre>");
		indent.shiftright();*/
		atpre.getTerm().visitExpression(this);
		/*indent.newline();
		indent.shiftleft();
		indent.println("</atpre>");*/
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitQuantifierVariable(tamagocc.generic.impl.GIQuantifierVariable)
	 */
	public Object visitQuantifierVariable(GIQuantifierVariable variable)
			throws TamagoCCException {
		variable.getSimpleVariable().visitExpression(this);
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitRead(tamagocc.generic.api.GRead)
	 */
	public Object visitRead(GRead read) throws TamagoCCException {
		indent.print("<read property=\"");
		indent.print(read.getName());
		indent.print("\" />");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitReal(tamagocc.generic.api.GReal)
	 */
	public Object visitReal(GReal real) throws TamagoCCException {
		indent.print("<real value=\"");
		indent.print(""+real.getValue());
		indent.println("\" />");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitReturn(tamagocc.generic.api.GReturn)
	 */
	public Object visitReturn(GReturn ret) throws TamagoCCException {
		if(ret.hasIndex()) {
			indent.println("<return>");
			indent.shiftright();
			ret.getIndex().visit(this);
			indent.shiftleft();
			indent.println("</return>");
		}
		else
			indent.println("<return />");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitString(tamagocc.generic.api.GString)
	 */
	public Object visitString(GString string) throws TamagoCCException {
		indent.print("<string>");
		indent.print(string.getValue());
		indent.println("</string>");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitVariable(tamagocc.generic.api.GVariable)
	 */
	public Object visitVariable(GVariable variable) throws TamagoCCException {
		indent.print("<variable name=\"");
		indent.print(variable.getName());
		indent.print("\" ");
		if(variable.hasIndex()) {
			indent.println(">");
			indent.shiftright();
			indent.println("<index>");
			indent.shiftright();
			variable.getIndex().visit(this);
			indent.shiftleft();
			indent.println("</index>");
			indent.shiftleft();
			indent.println("</variable>");
		}
		else {
			indent.println("/>");
		}
		return null;
	}

	public Object visitAccess(GAccess access) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitAllow(GAllow allow) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitAssembly(GAssemblyContainer assembly) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitBehavior(GBehavior behavior) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitBind(GBind bind) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitCategory(GCategory category) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitComponent(GComponentContainer component) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitComposite(GCompositeContainer composite) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitCondition(GCondition condition) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitDefinition(GInstantiateComponent definition) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitExistColl(GExistColl f) throws TamagoCCException {
		indent.print("<quantifier category=\"collection\" quantifier=\"exist\" ");
		indent.print("type=\"");
		indent.print(f.getType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		f.getVariable().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<collection>");
		f.getCollection().visit(this);
		indent.println("</collection>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</quantifier>");
		return null;
	}

	public Object visitExistRange(GExistRange f) throws TamagoCCException {
		indent.print("<quantifier category=\"range\" quantifier=\"exist\" ");
		indent.print("type=\"");
		indent.print(f.getType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		f.getVariable().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<range>");
		indent.shiftright();
		indent.println("<min>");
		f.getMin().visit(this);
		indent.println("</min>");
		indent.println("<max>");
		f.getMax().visit(this);
		indent.println("</max>");
		indent.shiftleft();
		indent.println("</range>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</quantifier>");
		return null;
	}

	public Object visitExistSet(GExistSet f) throws TamagoCCException {
		indent.print("<quantifier category=\"set\" quantifier=\"exist\" ");
		indent.print("type=\"");
		indent.print(f.getType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		f.getVariable().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<set>");
		indent.shiftright();
		for (GExpression expr : f.getSet().getElements()) {
			indent.println("<element>");
			expr.visit(this);
			indent.println("</element>");	
		}
		indent.shiftleft();
		indent.println("</set>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</quantifier>");
		return null;
	}

	public Object visitExport(GExport export) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitExtendService(GExtendService extendservice) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitForallColl(GForallColl f) throws TamagoCCException {
		indent.print("<quantifier category=\"collection\" quantifier=\"exist\" ");
		indent.print("type=\"");
		indent.print(f.getType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		f.getVariable().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<collection>");
		f.getCollection().visit(this);
		indent.println("</collection>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</quantifier>");
		return null;
	}

	public Object visitForallRange(GForallRange f) throws TamagoCCException {
		indent.print("<quantifier category=\"range\" quantifier=\"exist\" ");
		indent.print("type=\"");
		indent.print(f.getType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		f.getVariable().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<range>");
		indent.shiftright();
		indent.println("<min>");
		f.getMin().visit(this);
		indent.println("</min>");
		indent.println("<max>");
		f.getMax().visit(this);
		indent.println("</max>");
		indent.shiftleft();
		indent.println("</range>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</quantifier>");
		return null;
	}

	public Object visitForallSet(GForallSet f) throws TamagoCCException {
		indent.print("<quantifier category=\"set\" quantifier=\"forall\" ");
		indent.print("type=\"");
		indent.print(f.getType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		f.getVariable().visit(this);
		indent.newline();
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<set>");
		indent.shiftright();
		for (GExpression expr : f.getSet().getElements()) {
			indent.println("<element>");
			expr.visit(this);
			indent.println("</element>");	
		}
		indent.newline();
		indent.shiftleft();
		indent.println("</set>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.newline();
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</quantifier>");
		return null;
	}

	public Object visitImplements(GImplements impl) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitInitialisation(GInitialisation initialisation) throws TamagoCCException {
		indent.println("<set-value>");
		indent.shiftright();
		indent.print("<variable type=\"");
		indent.print(initialisation.getVariable().getType().getType());
		indent.println("\" >");
		indent.shiftright();
		initialisation.getVariable().visit(this);
		indent.newline();
		indent.shiftleft();
		indent.println("</variable>");
		indent.println("<value>");
		indent.shiftright();
		initialisation.getInitValue().visit(this);
		indent.newline();
		indent.shiftleft();
		indent.println("</value>");
		indent.shiftleft();
		indent.println("</set-value>");
		return null;
	}

	public Object visitInvariant(GInvariant invariant) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitMethod(GMethod method) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitModule(GModule module) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitObject(GObject object) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitParameter(GParameter parameter) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitPercolator(GPercolator percolator) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitProperty(GProperty property) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitProvide(GProvide provide) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitRequire(GRequire require) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitService(GServiceInterface service) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitSet(GSet set) throws TamagoCCException {
		indent.println("<set>");
		indent.shiftright();
		for (GExpression expr : set.getElements()) {
			expr.visit(this);
			indent.newline();
		} 
		indent.shiftleft();
		indent.println("</set>");
		return null;
	}

	public Object visitState(GState state) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitTamagoEntity(GTamagoEntity entity) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitTransition(GTransition transition) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitType(GType type) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitNamespace(GNamespace namespace) throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	public Object visitLanguageExpr(GLanguageExpr languageExpr)
			throws TamagoCCException {
		throw new TamagoCCException("GExprToXml : not yet supported");
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCast(tamagocc.generic.api.GCast)
	 */
	public Object visitCast(GCast cast) throws TamagoCCException {
		indent.print("<cast type=\"");
		indent.print(cast.getType().getType());
		indent.println("\">");
		indent.shiftright();
		indent.println("<expression>");
		indent.shiftright();
		cast.getExpression().visit(this);
		indent.shiftleft();
		indent.println("</expression>");
		indent.shiftleft();
		indent.println("</cast>");
		return null;
	}

	public Object visitPreInitialisation(GPreInitialisation preInitialisation) throws TamagoCCException {
		return visitInitialisation(preInitialisation);
	}

	@Override
	public Object visitInState(GInState instate) throws TamagoCCException {
		indent.open("instate");
		for (String st : instate.getInState()) {
			indent.openclose("state", "name",st);
		}
		indent.close("instate");
		return null;
	}

	@Override
	public Object visitIsBound(GIsBound giIsBound) throws TamagoCCException {
		indent.openclose("isbound", "label",giIsBound.getLabel());
		return null;
	}

}
