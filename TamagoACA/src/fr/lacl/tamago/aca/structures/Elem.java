package fr.lacl.tamago.aca.structures;

import java.util.List;

public abstract class Elem 
{
	public abstract String toString();
	public abstract boolean containIL();
	public abstract boolean containNOT();
	public abstract boolean containOnlyNOT();
	public abstract void liste(List<Nuplet> listePermission);
	public abstract String toSCD();
	public abstract void isFirstParallele();
	public abstract void addElem(Elem t);
	public abstract String getNom();
	
}
