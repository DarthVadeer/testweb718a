package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Alarm;
import util.AppException;
import util.DBUtil;

public class AlarmDao {

	//插入一条报警处理记录
	public boolean insert(Alarm alarm) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "insert into"
					+" SYS_ALARMPROCESS "
					+"values(?,?,?,?,?,?,?,?,?,?,?,?)";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, alarm.getAlarmID());
			psmt.setString(2, alarm.getAlarmPastID());
			psmt.setString(3, alarm.getAlarmCarNum());
			psmt.setDate(4, alarm.getAlarmDcoDate());
			psmt.setInt(5, alarm.getAlarmState());
			psmt.setString(6, alarm.getAlarmHandler());
			psmt.setString(7, alarm.getAlarmMemo());
			psmt.setString(8, alarm.getAlarmFeedBack());
			psmt.setInt(9, alarm.getAlarmIfIntercept());
			psmt.setString(10, alarm.getAlarmHandleProc());
			psmt.setString(11, alarm.getAlarmUniReason());	
			psmt.setString(12,alarm.getAlarmPhone());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Alarm.insert");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

	//查询所有报警处理信息
	public List<Alarm> getAll() throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Alarm> list = new ArrayList<Alarm>();	
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_ALARMPROCESS";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'

			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				Alarm alarm=new Alarm();
				
				alarm.setAlarmID(rs.getString("alarmid"));
				alarm.setAlarmPastID(rs.getString("alarmpastid"));
				alarm.setAlarmCarNum(rs.getString("alarmcarnumber"));
				alarm.setAlarmDcoDate(rs.getDate("alarmcollectiondate"));
				alarm.setAlarmState(rs.getInt("alarmstate"));
				alarm.setAlarmHandler(rs.getString("alarmhandler"));
				alarm.setAlarmMemo(rs.getString("alarmmemo"));
				alarm.setAlarmFeedBack(rs.getString("alarmfeedback"));
				alarm.setAlarmIfIntercept(rs.getInt("alarmintercept"));
				alarm.setAlarmHandleProc(rs.getString("alarmhandleprocess"));
				alarm.setAlarmUniReason(rs.getString("alarmuninterceptedreason"));
				alarm.setAlarmPhone(rs.getString("alarmphone"));
				
				list.add(alarm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("AlarmDao.getAll");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	//通过状态查询报警处理信息
	public List<Alarm> getbyState(int state) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Alarm> list = new ArrayList<Alarm>();	
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_ALARMPROCESS"
					+" where alarmstate=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setInt(1, state);
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				Alarm alarm=new Alarm();
				
				alarm.setAlarmID(rs.getString("alarmid"));
				alarm.setAlarmPastID(rs.getString("alarmpastid"));
				alarm.setAlarmCarNum(rs.getString("alarmcarnumber"));
				alarm.setAlarmDcoDate(rs.getDate("alarmcollectiondate"));
				alarm.setAlarmState(rs.getInt("alarmstate"));
				alarm.setAlarmHandler(rs.getString("alarmhandler"));
				alarm.setAlarmMemo(rs.getString("alarmmemo"));
				alarm.setAlarmFeedBack(rs.getString("alarmfeedback"));
				alarm.setAlarmIfIntercept(rs.getInt("alarmintercept"));
				alarm.setAlarmHandleProc(rs.getString("alarmhandleprocess"));
				alarm.setAlarmUniReason(rs.getString("alarmuninterceptedreason"));
				alarm.setAlarmPhone(rs.getString("alarmphone"));
				
				list.add(alarm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("AlarmDao.getbyState");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}
	
	//通过时间查询报警处理信息
	public List<Alarm> getbyDate(Date beginTime,Date endTime) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<Alarm> list = new ArrayList<Alarm>();	
			
		try {
			
			conn = DBUtil.getConnection();
			if(beginTime.equals("") && endTime.equals(""))
				sql="select * from SYS_ALARMPROCESS";
			else if(!beginTime.equals("") && !endTime.equals("")){
				sql = "select  * "
						+"from SYS_ALARMPROCESS"
						+" where alarmcollectiondate>? and alarmcollectiondate<?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setDate(1, beginTime);
				psmt.setDate(2, endTime);
			}
			else if(!beginTime.equals("") && endTime.equals("")){
				sql="select * from SYS_ALARMPROCESS where alarmcollectiondate>?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setDate(1, beginTime);
			}
			else if(beginTime.equals("") && endTime.equals("")){
				sql="select * from SYS_ALARMPROCESS where alarmcollectiondate<?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setDate(1, endTime);
			}
				
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				Alarm alarm=new Alarm();
				
				alarm.setAlarmID(rs.getString("alarmid"));
				alarm.setAlarmPastID(rs.getString("alarmpastid"));
				alarm.setAlarmCarNum(rs.getString("alarmcarnumber"));
				alarm.setAlarmDcoDate(rs.getDate("alarmcollectiondate"));
				alarm.setAlarmState(rs.getInt("alarmstate"));
				alarm.setAlarmHandler(rs.getString("alarmhandler"));
				alarm.setAlarmMemo(rs.getString("alarmmemo"));
				alarm.setAlarmFeedBack(rs.getString("alarmfeedback"));
				alarm.setAlarmIfIntercept(rs.getInt("alarmintercept"));
				alarm.setAlarmHandleProc(rs.getString("alarmhandleprocess"));
				alarm.setAlarmUniReason(rs.getString("alarmuninterceptedreason"));
				alarm.setAlarmPhone(rs.getString("alarmphone"));
				
				list.add(alarm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("AlarmDao.getbyDate");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	//
	public boolean update(Alarm alarm) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "Update"
					+" SYS_ARMPROCESS set alarmpastid=?,alarmcarnumber=?,alarmcollectiondate=?,alarmstate=?,alarmhandler=?,alarmmemo=?,alarmfeedback=?,alarmintercept=?,alarmhandleprocess=?,alarmunintercepterreason=?,alarmphone=?"
					+" where alarmid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(12, alarm.getAlarmID());
			psmt.setString(1, alarm.getAlarmPastID());
			psmt.setString(2, alarm.getAlarmCarNum());
			psmt.setDate(3, alarm.getAlarmDcoDate());
			psmt.setInt(4, alarm.getAlarmState());
			psmt.setString(5, alarm.getAlarmHandler());
			psmt.setString(6, alarm.getAlarmMemo());
			psmt.setString(7, alarm.getAlarmFeedBack());
			psmt.setInt(8, alarm.getAlarmIfIntercept());
			psmt.setString(9, alarm.getAlarmHandleProc());
			psmt.setString(10, alarm.getAlarmUniReason());	
			psmt.setString(11,alarm.getAlarmPhone());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("AlarmDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

}
