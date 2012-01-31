package tamago.aca.term;

import javapop.framework.parser.MaybeParse;
import javapop.utils.Decuple;
import tamago.aca.visitor.ACAVisitor;

public class ACA {
	protected Info info;
	protected Users users;
	protected Roles roles;
	protected Orgs orgs;
	protected Actions actions;
	protected Play play;
	protected Perms perms;
	protected Bans bans;
	protected Obls obls;
	protected Sods sods;
	
	
	
	public ACA(
			Decuple<Info, MaybeParse<Users>, MaybeParse<Roles>, MaybeParse<Orgs>, MaybeParse<Actions>, MaybeParse<Play>, MaybeParse<Perms>, MaybeParse<Bans>, MaybeParse<Obls>, MaybeParse<Sods>> r) {
		info = r.getFirst();
		if(r.getSecond().hasResult())
			users = r.getSecond().getResult().getResult();
		
		if(r.getThird().hasResult())
			roles = r.getThird().getResult().getResult();
		
		if(r.getFourth().hasResult())
			orgs = r.getFourth().getResult().getResult();
		if(r.getFifth().hasResult())
			actions = r.getFifth().getResult().getResult();
		if(r.getSixth().hasResult())
			play = r.getSixth().getResult().getResult();
		if(r.getSeventh().hasResult())
			perms = r.getSeventh().getResult().getResult();
		if(r.getEighth().hasResult())
			bans = r.getEighth().getResult().getResult();
		if(r.getNinth().hasResult())
			obls = r.getNinth().getResult().getResult();
		if(r.getTenth().hasResult())
			sods = r.getTenth().getResult().getResult();
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public Orgs getOrgs() {
		return orgs;
	}
	public void setOrgs(Orgs orgs) {
		this.orgs = orgs;
	}
	public Actions getActions() {
		return actions;
	}
	public void setActions(Actions actions) {
		this.actions = actions;
	}
	public Play getPlay() {
		return play;
	}
	public void setPlay(Play play) {
		this.play = play;
	}
	public Perms getPerms() {
		return perms;
	}
	public void setPerms(Perms perms) {
		this.perms = perms;
	}
	public Bans getBans() {
		return bans;
	}
	public void setBans(Bans bans) {
		this.bans = bans;
	}
	public Obls getObls() {
		return obls;
	}
	public void setObls(Obls obls) {
		this.obls = obls;
	}
	public Sods getSods() {
		return sods;
	}
	public void setSods(Sods sods) {
		this.sods = sods;
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitACA(this);
	}
}
