/**
 * 
 */
package tamagocc.ast.api;


/**
 * @author Hakim Belhaouari
 *
 */
public interface ABodyMethodContainer extends ASequence {
	ASequence getInitBody();
	ASequence getPreInvariant();
	ASequence getPrecondition();
	ASequence getInitPrecondition();
	ASequence getRedirection();
	ASequence getPostcondition();
	ASequence getInitPostcondition();
	ASequence getPostinvariant();
	ASequence getUpdateBehavior();
	ASequence getReturnResult();
}
