package model;

public class BlackList {

	private String blackID;
	private String blackFlowID;
	private String blackCarNum;
	private String blackDcoDate;
	private int blackState;
	private String blackApplicant;
	private String blackConPoint;
	private String reason;
	
	public void setBlackID(String a)
	{
		this.blackID = a;
	}
	public void setBlackFlowID(String a)
	{
		this.blackFlowID = a;
	}
	public void setBlackCarNum(String a)
	{
		this.blackCarNum = a;
	}

	public void setBlackDcoDate(String a)
	{
		this.blackDcoDate = a;
	}
	public void setBlackState(int a)
	{
		this.blackState = a;
	}
	public void setBlackApplicant(String a)
	{
		this.blackApplicant = a;
	}
	public void setBlackConPoint(String a)
	{
		this.blackConPoint = a;
	}

	public void setBlackReason(String a) {
		this.reason = a;
	}
	
	
	public String getBlackID()
	{
		return this.blackID;
	}
	public String getBlackFlowID()
	{
		return this.blackFlowID;
	}
	public String getBlackCarNum()
	{
		return this.blackCarNum;
	}

	public String getBlackDcoDate()
	{
		return this.blackDcoDate;
	}
	public int getBlackState()
	{
		return this.blackState;
	}
	public String getBlackApplicant()
	{
		return this.blackApplicant;
	}
	public String getBlackConPoint()
	{
		return this.blackConPoint;
	}

	public String getBlackReason() {
		return this.reason;
	}

	
}
