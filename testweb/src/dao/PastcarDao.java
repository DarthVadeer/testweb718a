package dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Location;
import model.PastCar;
import util.AppException;
import util.DBUtil;

public class PastcarDao {

	//通过过车ID查找过车信息
	public List<PastCar> getbyID(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<PastCar> list = new ArrayList<PastCar>();	
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_PASTCAR"
					+" where pastid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, id);
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				PastCar pcar=new PastCar();
				
				pcar.setPastID(rs.getString("pastid"));
				pcar.setPastLicType(rs.getString("pastlicensetype"));
				pcar.setPastCarNum(rs.getString("pastcarnumber"));
				pcar.setPastDcoDate(dateToString(rs.getDate("pastdcollectiondate")));
				pcar.setPastDevCode(rs.getString("pastcdevicecode"));
				pcar.setPastLaneNum(rs.getString("pastlanenumber"));
				pcar.setPastVehSpeed(rs.getString("pastvehiclespeed"));
				pcar.setPastPicPath(rs.getString("pastpic1path"));
				pcar.setPastDesc(rs.getString("pastderiction"));

				list.add(pcar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyID");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	//通过车牌号查找过车信息
	public List<Location> getbyCarnumAndDate(String carnum,Date beginDate,Date endDate) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<Location> list = new ArrayList<Location>();	
			
		try {
			
			conn = DBUtil.getConnection();
			
			if(carnum==null)
				return list;
			
				sql = "select  * "
						+"from SYS_PASTCAR,SYS_equipment,sys_point "
						+" where SYS_PASTCAR.PASTCDEVICECODE=SYS_equipment.EQUID "
						+ "and sys_point.pointid=SYS_equipment.EQUBINDPOINT and "
						+ " pastcarnumber=? and pastdcollectiondate>? and pastdcollectiondate<?";
				
				
				
				
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setString(1, carnum);
				psmt.setDate(2, beginDate);
				psmt.setDate(3, endDate);
			
				
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				Location loc=new Location();
				
			
				loc.setDate(dateToString(rs.getDate("pastdcollectiondate")));
				loc.setLongitude(rs.getString("POINTLONGITUDE"));
				loc.setLatitude(rs.getString("POINTLATITUDE"));
				
				list.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyCarnum");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	private String dateToString(Date date) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	
		String str=sdf.format(date);  
		return str;
	}

	private String showDirection(int int1) {
		switch(int1){
		case 1:return "东-西";
		case 2:return "西-东";
		case 3:return "南-北";
		case 4:return "北-南";
		default: return "";
		}
		
	}
	
	
	public List<PastCar> getbyDcodatePointName(Date beginTime,Date endTime,String PointName) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;
		PointName="%"+PointName+"%";
		
		List<PastCar> list = new ArrayList<PastCar>();	
			
		try {
			
			conn = DBUtil.getConnection();
			
			sql = "select * from SYS_PASTCAR,SYS_Point,SYS_equipment "
					+ "where SYS_PASTCAR.PASTCDEVICECODE = SYS_equipment.EQUID and SYS_equipment.EQUBINDPOINT=SYS_Point.POINTID";
			sql+=" and POINTNAME like ? ";
			
			/*if(beginTime!=null)
				sql+=" and pastdcollectiondate>? ";
				if(endTime!=null)
			sql+=" and pastdcollectiondate<? ";*/
		
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setString(1, PointName);
				int i = 2;
				/*
				if(beginTime!=null)
				psmt.setDate(i++, beginTime);
				if(endTime!=null)
				psmt.setDate(i, endTime);*/
				

			System.out.println(sql);
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				PastCar pcar=new PastCar();
				pcar.setPastID(rs.getString("pastid"));
				pcar.setPastLicType(rs.getString("pastlicensetype"));
				pcar.setPastCarNum(rs.getString("pastcarnumber"));
				pcar.setPastDcoDate(dateToString(rs.getDate("pastdcollectiondate")));
				pcar.setPastDevCode(rs.getString("pastcdevicecode"));
				pcar.setPastLaneNum(rs.getString("pastlanenumber"));
				pcar.setPastVehSpeed(rs.getString("pastvehiclespeed"));
				pcar.setPastPicPath(rs.getString("pastpic1path"));
				pcar.setPastDesc(showDirection(rs.getInt("PASTDERICTRION")));

				list.add(pcar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyDcodate");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	//通过过车时间查找过车信息
	public List<PastCar> getbyDcodate(Date beginTime,Date endTime) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;
		
		List<PastCar> list = new ArrayList<PastCar>();	
			
		try {
			
			conn = DBUtil.getConnection();
			
			if(beginTime.equals("") && endTime.equals(""))
				sql="select * from SYS_PASTCAR";
			else if(!beginTime.equals("") && !endTime.equals("")){
				sql = "select  * "
						+"from SYS_PASTCAR"
						+" where pastdcollectiondate>? and pastdcollectiondate<?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setDate(1, beginTime);
				psmt.setDate(2, endTime);
			}			
			else if(!beginTime.equals("") && endTime.equals("")){
				sql="select * from SYS_PASTCAR where pastdcollectiondate>?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setDate(1, beginTime);
			}
			else if(!beginTime.equals("") && !endTime.equals("")){
				sql="select * from SYS_PASTCAR where pastdcollectiondate<?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setDate(1, endTime);
			}
			
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				PastCar pcar=new PastCar();
				
				pcar.setPastID(rs.getString("pastid"));
				pcar.setPastLicType(rs.getString("pastlicensetype"));
				pcar.setPastCarNum(rs.getString("pastcarnumber"));
				pcar.setPastDcoDate(dateToString(rs.getDate("pastdcollectiondate")));
				pcar.setPastDevCode(rs.getString("pastcdevicecode"));
				pcar.setPastLaneNum(rs.getString("pastlanenumber"));
				pcar.setPastVehSpeed(rs.getString("pastvehiclespeed"));
				pcar.setPastPicPath(rs.getString("pastpic1path"));
				pcar.setPastDesc(showDirection(rs.getInt("PASTDERICTRION")));

				list.add(pcar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyDcodate");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}
	
	//通过卡口编号查找过车信息
	public List<PastCar> getbyDevcode(String devcode) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<PastCar> list = new ArrayList<PastCar>();	
			
		try {
			
			conn = DBUtil.getConnection();
			
			if(devcode.equals(""))
				sql="select * from SYS_PASTCAR";
			else{
				sql = "select  * "
						+"from SYS_PASTCAR"
						+" where pastdevicecode=?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setString(1, devcode);
			}
				
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				PastCar pcar=new PastCar();
				
				pcar.setPastID(rs.getString("pastid"));
				pcar.setPastLicType(rs.getString("pastlicensetype"));
				pcar.setPastCarNum(rs.getString("pastcarnumber"));
				pcar.setPastDcoDate(dateToString(rs.getDate("pastdcollectiondate")));
				pcar.setPastDevCode(rs.getString("pastcdevicecode"));
				pcar.setPastLaneNum(rs.getString("pastlanenumber"));
				pcar.setPastVehSpeed(rs.getString("pastvehiclespeed"));
				pcar.setPastPicPath(rs.getString("pastpic1path"));
				pcar.setPastDesc(showDirection(rs.getInt("PASTDERICTRION")));

				list.add(pcar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyDevcode");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}
	
	//通过车道编号查找过车信息
	public List<PastCar> getbyLane(String lanenum) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<PastCar> list = new ArrayList<PastCar>();	
			
		try {
			
			conn = DBUtil.getConnection();
			
			if(lanenum.equals(""))
				sql="select * from SYS_PASTCAR";
			else{
				sql = "select  * "
						+"from SYS_PASTCAR"
						+" where pastlanenumber=?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				psmt.setString(1, lanenum);	
			}
				
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				PastCar pcar=new PastCar();
				
				pcar.setPastID(rs.getString("pastid"));
				pcar.setPastLicType(rs.getString("pastlicensetype"));
				pcar.setPastCarNum(rs.getString("pastcarnumber"));
				pcar.setPastDcoDate(dateToString(rs.getDate("pastdcollectiondate")));
				pcar.setPastDevCode(rs.getString("pastcdevicecode"));
				pcar.setPastLaneNum(rs.getString("pastlanenumber"));
				pcar.setPastVehSpeed(rs.getString("pastvehiclespeed"));
				pcar.setPastPicPath(rs.getString("pastpic1path"));
				pcar.setPastDesc(showDirection(rs.getInt("PASTDERICTRION")));

				list.add(pcar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyLane");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}
}
