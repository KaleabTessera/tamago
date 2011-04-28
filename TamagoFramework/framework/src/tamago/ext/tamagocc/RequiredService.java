/**
 * 
 */
package tamago.ext.tamagocc;

/**
 * @author hakim
 *
 */
public class RequiredService {
	private String servicename;
	private String servicemodule;
	private String label;
	/**
	 * 
	 */
	public RequiredService(String servicename,String servicemodule,String label) {
		super();
		this.servicemodule = servicemodule;
		this.servicename = servicename;
		this.label = label;
	}
	
	public String getServiceName() {
		return servicename;
	}
	
	public String getServiceModule() {
		return servicemodule;
	}
	
	
	public String getAssociatedLabel() {
		return label;
	}
	
	public boolean equals(Object o) {
		if(o instanceof RequiredService) {
			RequiredService rs = (RequiredService)o;
			return servicename.equals(rs.getServiceName())
				&&servicemodule.equals(rs.getServiceModule())
				&&label.equals(rs.getAssociatedLabel());
		}
		else 
			return false;
	}
}
