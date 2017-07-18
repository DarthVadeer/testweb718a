package model;
import java.sql.Date;

public class Alarm {
	private String alarmID;
	private String alarmPastID;
	private String alarmCarNum;
	private Date alarmDcoDate;
	private int alarmState;
	private String alarmHandler;
	private String alarmMemo;
	private String alarmFeedBack;
	private int alarmIfIntercept;
	private String alarmHandleProc;
	private String alarmUniReason;
	private String alarmPhone;

	public String getAlarmID() {
		return alarmID;
	}

	public void setAlarmID(String alarmID) {
		this.alarmID = alarmID;
	}

	public String getAlarmPastID() {
		return alarmPastID;
	}

	public void setAlarmPastID(String alarmPastID) {
		this.alarmPastID = alarmPastID;
	}

	public String getAlarmCarNum() {
		return alarmCarNum;
	}

	public void setAlarmCarNum(String alarmCarNum) {
		this.alarmCarNum = alarmCarNum;
	}

	public Date getAlarmDcoDate() {
		return alarmDcoDate;
	}

	public void setAlarmDcoDate(Date alarmDcoDate) {
		this.alarmDcoDate = alarmDcoDate;
	}

	public int getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}

	public String getAlarmHandler() {
		return alarmHandler;
	}

	public void setAlarmHandler(String alarmHandler) {
		this.alarmHandler = alarmHandler;
	}

	public String getAlarmMemo() {
		return alarmMemo;
	}

	public void setAlarmMemo(String alarmMemo) {
		this.alarmMemo = alarmMemo;
	}

	public String getAlarmFeedBack() {
		return alarmFeedBack;
	}

	public void setAlarmFeedBack(String alarmFeedBack) {
		this.alarmFeedBack = alarmFeedBack;
	}

	public int getAlarmIfIntercept() {
		return alarmIfIntercept;
	}

	public void setAlarmIfIntercept(int alarmIfIntercept) {
		this.alarmIfIntercept = alarmIfIntercept;
	}

	public String getAlarmHandleProc() {
		return alarmHandleProc;
	}

	public void setAlarmHandleProc(String alarmHandleProc) {
		this.alarmHandleProc = alarmHandleProc;
	}

	public String getAlarmUniReason() {
		return alarmUniReason;
	}

	public void setAlarmUniReason(String alarmUniReason) {
		this.alarmUniReason = alarmUniReason;
	}

	public String getAlarmPhone() {
		return alarmPhone;
	}

	public void setAlarmPhone(String alarmPhone) {
		this.alarmPhone = alarmPhone;
	}

}
