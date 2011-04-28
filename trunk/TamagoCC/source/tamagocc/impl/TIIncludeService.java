package tamagocc.impl;

import tamagocc.api.TIncludeService;
import tamagocc.api.TService;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

public class TIIncludeService extends TIExtendService implements
		TIncludeService {

	public TIIncludeService(String name, String module, TService service) {
		super(name, module, service);
	}
	
	public boolean equals(Object o) {
		return (o instanceof TIncludeService) && super.equals(o);
	}

	/**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitIncludeService(this);
    }
}
