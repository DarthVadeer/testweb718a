package model;

public class Role {
	private String roleid;
	private String rolename;
	private String rolemenunames;
	private String rolemenuids;
	private String rolerenarks;
	
	
	public Role(){
		this.roleid = "";
		this.rolename = "";
		this.rolemenunames = "";
		this.rolemenuids = "";
		this.rolerenarks = "";
		
	}
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolemenunames() {
		return rolemenunames;
	}
	public void setRolemenunames(String rolemenunames) {
		this.rolemenunames = rolemenunames;
	}
	public String getRolemenuids() {
		return rolemenuids;
	}
	public void setRolemenuids(String rolemenuids) {
		this.rolemenuids = rolemenuids;
	}
	public String getRolerenarks() {
		return rolerenarks;
	}
	public void setRolerenarks(String rolerenarks) {
		this.rolerenarks = rolerenarks;
	}


}
