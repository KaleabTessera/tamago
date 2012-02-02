package tamago.aca.term;

import java.util.ArrayList;

import javapop.framework.parser.MaybeParse;
import tamago.aca.visitor.ACAVisitor;
import tamago.aca.parser.Process;

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
	protected Process process;
	
	
	
	public ACA(ArrayList r) {
		info = (Info) r.get(0);
		if( ((MaybeParse<Users>)r.get(1)).hasResult())
			users = ((MaybeParse<Users>)r.get(1)).getResult().getResult();
		else
			users = new Users();
		
		if(((MaybeParse<Roles>) r.get(2)).hasResult())
			roles =((MaybeParse<Roles>) r.get(2)).getResult().getResult();
		else
			roles = new Roles();
		
		if( ((MaybeParse<Orgs>) r.get(3)).hasResult())
			orgs = ((MaybeParse<Orgs>) r.get(3)).getResult().getResult();
		else
			orgs = new Orgs();
		
		if( ((MaybeParse<Actions> ) r.get(4)).hasResult())
			actions = ((MaybeParse<Actions> ) r.get(4)).getResult().getResult();
		else
			actions = new Actions();
		
		if(((MaybeParse<Play>) r.get(5)).hasResult())
			play = ((MaybeParse<Play>) r.get(5)).getResult().getResult();
		else
			play = new Play();
		
		if(((MaybeParse<Perms>) r.get(6)).hasResult())
			perms = ((MaybeParse<Perms>) r.get(6)).getResult().getResult();
		else
			perms = new Perms();
		
		if(((MaybeParse<Bans>) r.get(7)).hasResult())
			bans = ((MaybeParse<Bans>) r.get(7)).getResult().getResult();
		else
			bans = new Bans();
		
		if(((MaybeParse<Obls>) r.get(8)).hasResult())
			obls = ((MaybeParse<Obls>) r.get(8)).getResult().getResult();
		else
			obls = new Obls();
		if(((MaybeParse<Sods>) r.get(9)).hasResult())
			sods = ((MaybeParse<Sods>) r.get(9)).getResult().getResult();
		else
			sods = new Sods();
		
		if(((MaybeParse<Process>)r.get(10)).hasResult())
			process = ((MaybeParse<Process>)r.get(10)).getResult().getResult();
		else
			process = null;
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

	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
		
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitACA(this);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(info);
		sb.append("\n");
		sb.append(users);
		sb.append("\n");
		sb.append(roles);
		sb.append("\n");
		sb.append(orgs);
		sb.append("\n");
		sb.append(actions);
		sb.append("\n");
		sb.append(play);
		sb.append("\n");
		sb.append(perms);
		sb.append("\n");
		sb.append(bans);
		sb.append("\n");
		sb.append(obls);
		sb.append("\n");
		sb.append(sods);
		sb.append("\n");
		sb.append(process);
		sb.append("\n");
		return sb.toString();
	}
	
}
