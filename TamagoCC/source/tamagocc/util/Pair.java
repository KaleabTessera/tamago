/**
 * 
 */
package tamagocc.util;

/**
 * @author Hakim Belhaouari
 *
 */
public class Pair<L,R> {

	private L l;
	private R r;
	
	/**
	 * 
	 */
	public Pair(L l, R r) {
		this.l = l;
		this.r = r;
	}
	
	public L l() {return l; }
	public R r() { return r; }
	
	public boolean equals(Object o) {
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			return p.l.equals(l) && p.r.equals(r);
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(l.toString());
		sb.append(" x ");
		sb.append(r.toString());
		return sb.toString();
	}

	public L getL() {
		return l;
	}

	public void setL(L l) {
		this.l = l;
	}

	public R getR() {
		return r;
	}

	public void setR(R r) {
		this.r = r;
	}
}
