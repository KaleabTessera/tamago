package tamago.check.model;

public enum NodeDNFResult {
	NONE,          // Aucun probleme 
	FIXPOINT,      // aboutit a un point fixe
	MAXDEPTH,      // profondeur max atteinte
	ERROR,
	IMPOSSIBLE     // branche impossible
}
