/**
 * 
 */
package tamagocc.generator;

import java.util.Iterator;

import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVisibility;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIImplements;
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
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GTamagoEntity;


/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TamagoCCGeneratorInterface extends TamagoCCGeneratorCommon implements TamagoCCGVisitor {

	private AThrowsException ate;
	
	public TamagoCCGeneratorInterface(TamagoCCIGenerator owner,GTamagoEntity entity) {
		super(owner,entity);
		ate = new AIThrowsException((AIType) AIType.generateType("tamago.TamagoException"));
		//ast.setName(owner.getBuilder().generateInterfaceName(entity));
	}

	public Object visitExtendService(GExtendService extendservice) throws TamagoCCException {
		if(extendservice.isRefined()) {
			AIImplements impl = new AIImplements(extendservice.getServiceName(),module(extendservice.getModule()));
			ast.addImplement(impl);
		}
		else {
			// a priori depuis l'introduction de GITamago
			// ca devient inutile
			// extendservice.getService().visit(this);
		}
		return null;
	}

	
	
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

	public Object visitService(GServiceInterface service) throws TamagoCCException {
		// rien a faire a present
		
		for(GProperty prop :  service.getProperties()) {
			prop.visit(this);
		}
		
		for(GMethod meth :  service.getUniqueMethods()) {
			meth.visit(this);
		}
		
		Iterator<GExtendService> refs = service.getRefines();
		while(refs.hasNext()) {
			refs.next().visit(this);
		}
		
		// on indique aussi les elements implementant le sous-type
		for (GImplements impls : service.getImplements()) {
			AImplements gimpls = (AImplements) impls.visit(this);
			ast.addImplement(gimpls);
		}
		
		for(GNamespace ns : service.getNamespaces()) {
			ANamespace ans = (ANamespace) ns.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return ast;
	}
	
	public Object visitProvide(GProvide provide) throws TamagoCCException {
		AIImplements impl = new AIImplements((AType) visitType(provide.getNameAsType()));
		ast.addImplement(impl);
		AINamespace ns = new AINamespace(provide.getModule().getFullModule(),provide.getName());
		ast.addUsedNamespaces(ns);
		
		for(GNamespace nsp : provide.getService().getNamespaces()) {
			ANamespace ans = (ANamespace) nsp.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return null;
	}
	
	public Object visitRequire(GRequire require) throws TamagoCCException {
		AIdent ident = new AIIdent(require.getLabel());
		//GServiceInterface service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(require.getServiceName(),require.getServiceModule());
		//String fullname = owner.getBuilder().generateInterfaceName(service);
		AType type = (AType) visitType(require.getNameAsType());//(AIType) AIType.generateType(fullname); 
		
		AIDocComment doccomment = new AIDocComment("This method allow user to access to the required service." +
				"(You do not need to implement this method, TamagoCC generates it in the skeleton)");
		
		AIMethod accesseur = new AIMethod(AMethod.DECLARED,ident,type,AIVisibility.PUBLIC_VISIBILITY);
		accesseur.setComment(doccomment);
		ast.addMethod(accesseur);
		
		AINamespace ns = new AINamespace(require.getServiceModule().getFullModule(),require.getServiceName());
		ast.addUsedNamespaces(ns);
		return null;
	}
	
	public Object visitMethod(GMethod method) throws TamagoCCException {
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
	
	public Object visitComponent(GComponentContainer component) throws TamagoCCException {
		Iterator<GProperty> properties = component.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
		
		
		Iterator<GProvide> provides = component.getAllProvide();
		while(provides.hasNext()) {
			provides.next().visit(this);
		}
		
		Iterator<GRequire> requires = component.getAllRequire();
		while(requires.hasNext()) {
			requires.next().visit(this);
		}
		
		Iterator<GMethod> methods = component.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
		}
		
		// on indique aussi les elements implementant le sous-type
		for (GImplements impls : component.getImplements()) {
			AImplements gimpls = (AImplements) impls.visit(this);
			ast.addImplement(gimpls);
		}
		
		for(GNamespace ns : component.getNamespaces()) {
			ANamespace ans = (ANamespace) ns.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return ast;
	}
	
	public Object visitComposite(GCompositeContainer composite) throws TamagoCCException {
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
		
		Iterator<GRequire> requires = composite.getAllRequire();
		while(requires.hasNext()) {
			requires.next().visit(this);
		}
		
		Iterator<GMethod> methods = composite.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
		}
		
		Iterator<GInstantiateComponent> inst = composite.getInstatiateComponent();
		while(inst.hasNext()) {
			inst.next().visit(this);
		}
		
		// on indique aussi les elements implementant le sous-type
		for (GImplements impls : composite.getImplements()) {
			AImplements gimpls = (AImplements) impls.visit(this);
			ast.addImplement(gimpls);
		}
		
		for(GNamespace ns : composite.getNamespaces()) {
			ANamespace ans = (ANamespace) ns.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return ast;
	}
	
	public Object visitDefinition(GInstantiateComponent inst) throws TamagoCCException {
		return null;
	}
}
