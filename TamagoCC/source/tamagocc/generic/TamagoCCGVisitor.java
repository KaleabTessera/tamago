/**
 * 
 */
package tamagocc.generic;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GAccess;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCategory;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GInvariant;
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
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface TamagoCCGVisitor extends TamagoCCGExpressionVisitor,TamagoCCGPreExpressionVisitor {
	Object visitAccess(GAccess access) throws TamagoCCException;
	Object visitAllow(GAllow allow) throws TamagoCCException;
	Object visitAssembly(GAssemblyContainer assembly) throws TamagoCCException;
	Object visitBehavior(GBehavior behavior) throws TamagoCCException;
	Object visitBind(GBind bind) throws TamagoCCException;
	Object visitBool(GBool bool) throws TamagoCCException;
	Object visitCall(GCall call) throws TamagoCCException;
	Object visitCategory(GCategory category) throws TamagoCCException;
	Object visitComponent(GComponentContainer component) throws TamagoCCException;
	Object visitComposite(GCompositeContainer composite) throws TamagoCCException;
	Object visitCondition(GCondition condition) throws TamagoCCException;
	Object visitDefinition(GInstantiateComponent definition) throws TamagoCCException;
	Object visitExport(GExport export) throws TamagoCCException;
	Object visitExpression(GExpression e) throws TamagoCCException;
	Object visitExtendService(GExtendService extendservice) throws TamagoCCException;
	Object visitInvariant(GInvariant invariant) throws TamagoCCException;
	Object visitInteger(GInteger integer) throws TamagoCCException;
	Object visitMethod(GMethod method) throws TamagoCCException;
	Object visitModule(GModule module) throws TamagoCCException;
	Object visitNoContract(GNoContract nocontract) throws TamagoCCException;
	Object visitNot(GNot not) throws TamagoCCException;
	Object visitObject(GObject object) throws TamagoCCException;
	Object visitOperator(GOperator operator) throws TamagoCCException;
	Object visitParameter(GParameter parameter) throws TamagoCCException;
	Object visitPre(GAtPre pre) throws TamagoCCException;
	Object visitProperty(GProperty property) throws TamagoCCException;
	Object visitProvide(GProvide provide) throws TamagoCCException;
	Object visitRead(GRead read) throws TamagoCCException;
	Object visitReal(GReal real) throws TamagoCCException;
	Object visitRequire(GRequire require) throws TamagoCCException;
	Object visitReturn(GReturn ret) throws TamagoCCException;
	Object visitService(GServiceInterface service) throws TamagoCCException;
	Object visitState(GState state) throws TamagoCCException;
	Object visitString(GString string) throws TamagoCCException;
	Object visitTamagoEntity(GTamagoEntity entity) throws TamagoCCException;
	Object visitTransition(GTransition transition) throws TamagoCCException;
	Object visitType(GType type) throws TamagoCCException;
	Object visitVariable(GVariable variable) throws TamagoCCException;
	Object visitInLabel(GInLabel inlabel) throws TamagoCCException;
	Object visitPercolator(GPercolator percolator)throws TamagoCCException;
	
	Object visitNil(GNil nil) throws TamagoCCException;
	Object visitImplements(GImplements impl) throws TamagoCCException;
	Object visitNamespace(GNamespace namespace) throws TamagoCCException;
}
