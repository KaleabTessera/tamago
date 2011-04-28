package tamagocc.generic.impl;

import tamagocc.api.TAccess;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAccess;

public class GIAccess implements GAccess {

	private boolean canread;
	private boolean canwrite;
	
	public GIAccess(boolean canread,boolean canwrite) {
		super();
		this.canread = canread;
		this.canwrite = canwrite;
	}
	
	public GIAccess(TAccess access) {
		super();
		this.canread = access.canRead();
		this.canwrite = access.canWrite();
	}

	public boolean canRead() {
		return canread;
	}

	public boolean canWrite() {
		return canwrite;
	}

	
	public void setCanRead(boolean f) {
		canread = f;
	}
	
	public void setCanWrite(boolean f) {
		canwrite = f;
	}
	
	public boolean equals(Object o) {
		if(o instanceof GAccess) {
			GAccess ace = (GAccess)o;
			return ((ace.canRead() == canread) && (ace.canWrite() == canwrite));
		}
		else
			return false;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitAccess(this);
	}
	
}
