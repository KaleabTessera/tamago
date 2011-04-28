/**
 * 
 */
package tamagocc.generator;

import java.util.Iterator;

import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVisibility;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AIProperty;
import tamagocc.ast.impl.AIThrowsException;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GTamagoEntity;

/**
 * @author Hakim Belhaouari 
 *
 */
public class TamagoCCGeneratorContainerInterface extends TamagoCCGeneratorCommon implements TamagoCCGVisitor {

	private AThrowsException ate;

	public TamagoCCGeneratorContainerInterface(TamagoCCIGenerator owner, GTamagoEntity entity) throws TamagoCCException {
		super(owner, entity);
		ate = new AIThrowsException((AIType) AIType.generateType("tamago.TamagoException"));
		ast.setName(owner.getBuilder().generateContainerInterfaceName(entity));
		
		AIImplements impl = new AIImplements((AType) visitType(entity.getNameAsType()));
		ast.addImplement(impl);
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitComponent(tamagocc.generic.api.GComponentContainer)
	 */
	public Object visitComponent(GComponentContainer component)
			throws TamagoCCException {
		Iterator<GProperty> properties = component.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
		
		
		Iterator<GProvide> provides = component.getAllProvide();
		while(provides.hasNext()) {
			GProvide es = provides.next();
			es.visit(this);
		}
		
		Iterator<GMethod> methods = component.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
		}
		
		for(GNamespace nsp : component.getNamespaces()) {
			ANamespace ans = (ANamespace) nsp.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		
		return ast;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitComposite(tamagocc.generic.api.GCompositeContainer)
	 */
	public Object visitComposite(GCompositeContainer composite)
			throws TamagoCCException {
		Iterator<GProperty> properties = composite.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
		
		
		Iterator<GProvide> provides = composite.getAllProvide();
		while(provides.hasNext()) {
			GProvide es = provides.next();
			es.visit(this);
		}
		
		Iterator<GMethod> methods = composite.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
		}
		
		for(GNamespace nsp : composite.getNamespaces()) {
			ANamespace ans = (ANamespace) nsp.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return ast;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitExtendService(tamagocc.generic.api.GExtendService)
	 */
	public Object visitExtendService(GExtendService extendservice)
			throws TamagoCCException {
		if(extendservice.isRefined()) {
			AIImplements impl = new AIImplements(extendservice.getServiceName(),module(extendservice.getModule()));
			ast.addImplement(impl);
		}
		extendservice.getService().visit(this);
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitMethod(tamagocc.generic.api.GMethod)
	 */
	public Object visitMethod(GMethod method)
			throws TamagoCCException {
		AType type= type(method.getType());
		AIdent ident = ident(method.getName());
		AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
		
		AIMethod m = new AIMethod(AMethod.DECLARED,ident,type,visibility);
		
		m.addThrowsException(ate);		
		Iterator<GParameter> parameters = method.getParameters();
		while(parameters.hasNext()) {
			GParameter parameter = parameters.next();
			AParameter aparam = (AParameter)parameter.visit(this);
			m.addParameters(aparam);
		}
		
		ast.addMethod(m);
		return m;
	}
	
	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitProperty(tamagocc.generic.api.GProperty)
	 */
	public Object visitProperty(GProperty property) throws TamagoCCException {
		AIProperty prop = new AIProperty(true,property.getName(),(AType) property.getType().visit(this),
				property.getAccess().canRead(),property.getAccess().canWrite());
		this.ast.addProperty(prop);
		/*
		String part = propertyToMethod(property.getName());
		AType type = type(property.getType());
		AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
		
		if(property.getAccess().canRead()) {
			AIdent name = ident("get"+part);
			ADocComment comment = new AIDocComment("Getter of the property "+ property.getName());
			AIMethod getter = new AIMethod(AMethod.DECLARED,name,type,visibility);
			getter.setComment(comment);
			ast.addMethod(getter);
		}
		
		if(property.getAccess().canWrite()) {
			AIdent name = ident("set"+part);
			AType vtype = new AIType(CatType.VOID,"void");
			ADocComment comment = new AIDocComment("Setter of the property "+ property.getName());
			AIMethod setter = new AIMethod(AMethod.DECLARED,name,vtype,visibility);
			AIParameter parameter = new AIParameter(ident(property.getName()),type);
			setter.addParameters(parameter);
			setter.setComment(comment);
			ast.addMethod(setter);
		}
		 */
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitProvide(tamagocc.generic.api.GProvide)
	 */
	public Object visitProvide(GProvide provide) throws TamagoCCException {
		/*AIImplements impl = new AIImplements((AType) visitType(provide.getNameAsType()));
		ast.addImplement(impl);*/
		provide.getService().visit(this);
		
		AINamespace ns = new AINamespace(provide.getModule().getFullModule(),provide.getName());
		ast.addUsedNamespaces(ns);
		
		for(GNamespace nsp : provide.getService().getNamespaces()) {
			ANamespace ans = (ANamespace) nsp.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return null;
	}
	
	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitRequire(tamagocc.generic.api.GRequire)
	 */
	public Object visitRequire(GRequire require) throws TamagoCCException {
		AIdent ident = new AIIdent(require.getLabel());
		//GServiceInterface service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(require.getServiceName(),require.getServiceModule());
		//String fullname = owner.getBuilder().generateInterfaceName(service);
		AType type = (AIType) visitType(require.getNameAsType()); 
		
		AIMemberVariable member = new AIMemberVariable(ident,type,AIVisibility.PRIVATE_VISIBILITY);
		
		ast.addVariablesMembers(member);
		
		AINamespace ns = new AINamespace(require.getServiceModule().getFullModule(),require.getServiceName());
		ast.addUsedNamespaces(ns);
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitService(tamagocc.generic.api.GServiceInterface)
	 */
	public Object visitService(GServiceInterface service)
			throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}
}
