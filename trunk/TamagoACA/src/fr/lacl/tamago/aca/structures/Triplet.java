package fr.lacl.tamago.aca.structures;

public class Triplet 
{
	public String user = new String();
	public String role = new String();
	public String organisation = new String();
	
	public String toString() 
	{
		String resultat = "user = " + user + " , " +
						  "role = " + role + " , " +
						  "organisation = " + organisation + "\n"  ;
		return resultat;
	}
	
}
