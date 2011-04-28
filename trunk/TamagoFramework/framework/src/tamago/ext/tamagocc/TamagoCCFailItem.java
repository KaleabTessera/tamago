/**
 * 
 */
package tamago.ext.tamagocc;

/**
 * @author belhaouari
 *
 */
public class TamagoCCFailItem {

	private String expr;
	private String message;
	private String category;
	
	/**
	 * 
	 */
	public TamagoCCFailItem(String expr,String message,String category) {
		super();
		this.expr = expr;
		if(message.length() >0 )
			this.message = message;
		else
			this.message = "";
		this.category = category;		
	}
	
	public TamagoCCFailItem(String expr) {
		this(expr,"","");
	}
	
	public TamagoCCFailItem(String expr,String message) {
		this(expr,message,"");
	}
	
	public TamagoCCFailItem() {
		this("","","");
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getExpression() {
		return expr;
	}
	
	public String getCategory() {
		return category;
	}
	

}
