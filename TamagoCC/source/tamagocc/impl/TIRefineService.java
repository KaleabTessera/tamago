package tamagocc.impl;

import tamagocc.api.TRefineService;
import tamagocc.api.TService;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

public class TIRefineService extends TIExtendService implements TRefineService {

	public TIRefineService(String name, String module, TService service) {
		super(name, module, service);
	}
	
	public boolean equals(Object o) {
		return (o instanceof TRefineService) && super.equals(o);
	}
	
	/**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitRefineService(this);
    }
}
