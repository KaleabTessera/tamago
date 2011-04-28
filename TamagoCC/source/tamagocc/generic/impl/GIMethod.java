/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.api.CatType;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.util.NilIterator;
import tamagocc.util.TamagoFreshVar;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIMethod implements GMethod {

	private String name;
	private GType type;
	private String id;

	private Collection<GParameter> parameters;
	private GCondition precondition;
	private GCondition postcondition;
	
	private boolean mustsaveresult;
	private GVariable variable;
		
	
	public GIMethod(String name,GType type,String id,
			Collection<GParameter> parameters,
			GCondition precondition,
			GCondition postcondition)
	{
		this.name = name;
		this.type = type;
		this.id = id;

		if(parameters == null)
			this.parameters = new ArrayList<GParameter>();
		else
			this.parameters = new ArrayList<GParameter>(parameters);
		this.precondition = precondition;
		this.postcondition = postcondition;
		
		if(type.catType() != CatType.VOID) {
			this.variable = new GIVariable(TamagoFreshVar.Default.getName("__tamago_return"),type);
			mustsaveresult = true;
		}
		else {
			mustsaveresult = false;
			this.variable = null;
		}
	}
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getParameterNumber() {
		return parameters.size();
	}

	public Iterator<GParameter> getParameters() {
		return parameters.iterator();
	}

	public GCondition getPostcondition() {
		return postcondition;
	}

	public GCondition getPrecondition() {
		return precondition;
	}

	public GVariable getSavedResult() {
		return variable;
	}

	public GType getType() {
		return type;
	}

	public boolean mustSaveResult() {
		return mustsaveresult;
	}

	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitMethod(this);
	}
	
	 public void setName(String name) {
		 this.name = name;
	 }
	 public void setType(GType type) {
		 this.type = type;		 
	 }
	 public void setID(String id) {
		 this.id = id;
	 }
	 public void setParameters(Collection<GParameter> parameters) {
		 this.parameters = parameters;
	 }
	 public void addParameter(GParameter parameter) {
		 this.parameters.add(parameter);
	 }
	 
	 public void setPrecondition(GCondition condition) {
		 precondition = condition;
	 }
	 public void setPostcondition(GCondition condition) {
		 postcondition = condition;
	 }
	
	
	/**
	 * @see tamagocc.generic.api.GMethod#sameSignature(tamagocc.generic.api.GMethod)
	 */
	public boolean sameSignature(GMethod method) {
				
		return(getName().equals(method.getName()) 
			&& getType().equals(method.getType())
			&&(method.getParameterNumber() == getParameterNumber())
			&&(NilIterator.areEqual(getParameters(), method.getParameters()))
			);
	}
	/*
	public static String genMethodID(GTamagoEntity entity,TMethod meth) {
		if("".equals(meth.getID())||(meth.getID() == null)) {
			StringBuilder sb = new StringBuilder();
			sb.append(entity.getModule().getFullModule());
			sb.append("#");
			sb.append(entity.getName());
			sb.append(":");
			sb.append(meth.getName());
			sb.append("/");
			sb.append(meth.getParameterNumber());
			return sb.toString();
		}
		else
			return meth.getID();
	}*/
}
