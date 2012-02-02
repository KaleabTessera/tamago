/**
 * 
 */
package tamago.aca.visitor;

import tamago.aca.term.ACA;
import tamago.aca.term.Actions;
import tamago.aca.term.Bans;
import tamago.aca.term.Info;
import tamago.aca.term.Obl;
import tamago.aca.term.Obls;
import tamago.aca.term.Orgs;
import tamago.aca.term.Perms;
import tamago.aca.term.Play;
import tamago.aca.term.PlayItem;
import tamago.aca.term.Quad;
import tamago.aca.term.Roles;
import tamago.aca.term.Sod;
import tamago.aca.term.Sods;
import tamago.aca.term.Users;

/**
 * @author hakim
 *
 */
public interface ACAVisitor<R, E extends Exception> {
	R visitACA(ACA e) throws E;

	R visitInfo(Info e) throws E;
	
	
	R visitRoles(Roles roles) throws E;
	R visitUsers(Users user) throws E;
	R visitOrgs(Orgs pl) throws E;
	
	R visitActions(Actions actions) throws E;
	
	R visitBans(Bans bans) throws E;
	R visitPerms(Perms p) throws E;
	
	R visitObl(Obl o) throws E;
	R visitObls(Obls o) throws E;
	
	R visitSod(Sod o) throws E;
	R visitSods(Sods o) throws E;
	
	R visitPlay(Play p) throws E;
	R visitPlayItem(PlayItem p) throws E;
	
	R visitQuad(Quad q) throws E;
	
}
