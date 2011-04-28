/**
 * 
 */
package tamagocc.util;

/**
 * @author Hakim Belhaouari
 *
 */
public class Triplet<L, C, R> {
	private C c;
	private L l;
	private R r;
	
	
	/**
	 * @param l
	 * @param r
	 * @param c
	 */
	public Triplet(L l,C c, R r) {
		this.c = c;
		this.l = l;
		this.r = r;
	}
	
	public L l() {return l; }
	public R r() { return r; }
	
	public C c() {return c; }
	
	public boolean equals(Object o) {
		if (o instanceof Triplet<?, ?, ?>) {
			Triplet<?, ?, ?> p = (Triplet<?,?, ?>) o;
			return p.l.equals(l) && p.r.equals(r) && c.equals(p.c());
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(l.toString());
		sb.append("x");
		sb.append(c.toString());
		sb.append("x");
		sb.append(r.toString());
		return sb.toString();
	}

	public void setC(C c) {
		this.c = c;
	}

	public void setL(L l) {
		this.l = l;
	}

	public void setR(R r) {
		this.r = r;
	}

}
