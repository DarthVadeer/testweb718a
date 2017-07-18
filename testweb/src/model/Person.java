package model;

public class Person {
	private String peopID;
	private String peopPassword;
	private String peopName;
	private String peopRole;
	private String peopSubdetach;
	private String peopPhone;
	
	public void setPeopID(String a)
	{
		this.peopID = a;
	}
	public void setPeopPassword(String a)
	{
		this.peopPassword = a;
	}
	public void setPeopName(String a)
	{
		this.peopName = a;
	}
	public void setPeopRole(String a)
	{
		this.peopRole = a;
	}
	public void setPeopSubdetach(String a)
	{
		this.peopSubdetach = a;
	}
	public void setPeopPhone(String a)
	{
		this.peopPhone = a;
	}

	public String getPeopID()
	{
		return this.peopID;
	}
	public String getPeopPassword()
	{
		return this.peopPassword;
	}
	public String getPeopName()
	{
		return this.peopName;
	}
	public String getPeopRole()
	{
		return this.peopRole;
	}
	public String getPeopSubdetach()
	{
		return this.peopSubdetach;
	}
	public String getPeopPhone()
	{
		return this.peopPhone;
	}

}
