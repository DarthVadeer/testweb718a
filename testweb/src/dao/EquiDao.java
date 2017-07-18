package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.Equipment;
import model.PastCar;
import model.People;
import model.Point;
import model.Road;
import util.AppException;
import util.DBUtil;

public class EquiDao {
	
	public boolean addEquipment(Equipment equipment) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "insert into SYS_EQUIPMENT " 
				+"values(?,?,?,?)";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, equipment.getEquipID());
			psmt.setInt(2, equipment.getEquipState());
			psmt.setString(3, equipment.getEquipBPoint());
			psmt.setString(4, equipment.getEquipName());
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 			
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.EquiDao.addEquipment");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public boolean removeEquipment(String eId) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "delete from SYS_EQUIPMENT " 
				+"where EquId = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, eId);
			
			
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			
		
		
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.EquiDao.removeEquipment");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public boolean moveEquipment(Equipment equipment) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "update SYS_EQUIPMENT " 
					+"set EquBindpoint = ?"
				+"where EquId = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, equipment.getEquipBPoint());
			psmt.setString(2, equipment.getEquipID());
			
			
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			
		
		
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.EquiDao.moveEquipment");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public boolean setstate(String eId,int state) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "update SYS_EQUIPMENT " 
					+"set EquState = ?"
				+"where EquId = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setInt(1, state);
			psmt.setString(2, eId);
			
			
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			
		
		
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.EquiDao.moveEquipment");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public List<Point> getPoints(List<String> EquipIDs) throws AppException{
		List<Point> list = new ArrayList<Point>();
		Equipment equip = new Equipment();
		EquiDao equidao = new EquiDao();
		String pointID = null;
		Point point = new Point();
		PointDao pointdao = new PointDao();
		for(int i = 0; i<EquipIDs.size();i++){
			equip = equidao.getbyID(EquipIDs.get(i));
			pointID = equip.getEquipBPoint();
			point =pointdao.getbyid(pointID); 
			list.add(point);
		}
		
		return list;
	}
	
	public Equipment getbyID(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<Equipment> list = new ArrayList<Equipment>();	
		Equipment equip = new Equipment();
			
		try {
			
			conn = DBUtil.getConnection();
			
			if(id.equals("")||id.equals(null)){
				sql="select * from SYS_EQUIPMENT";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
			}
			else{
				sql = "select  * "
						+"from SYS_EQUIPMENT"
						+" where equname = ?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				
				psmt.setString(1, id);
			}
 
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
	
				
				equip.setEquipID(rs.getString("equid"));
				equip.setEquipName(rs.getString("equname"));
				equip.setEquipState(rs.getInt("equstate"));
				equip.setEquipBPoint(rs.getString("equbindpoint"));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Equipment.getbyID");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return equip;	
	}

	public List<String> getbyPoint(String pointid) throws AppException{
		// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		List<Equipment> equiplist = new ArrayList<Equipment>();
		List<String> equipids = new ArrayList<String>();
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "Select * from SYS_EQUIPMENT " 
				+"where equbindpoint = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, pointid);

			rs =psmt.executeQuery();
			
			while(rs.next()){
				Equipment equip = new Equipment();
				equip.setEquipID(rs.getString("equid"));
				equip.setEquipState(rs.getInt("equState"));
				equip.setEquipBPoint(rs.getString("equbindpoint"));
				equip.setEquipName(rs.getString("equname"));
				equiplist.add(equip);
			}
			
			for(int i=0;i<equiplist.size();i++){
				equipids.add(equiplist.get(i).getEquipID());
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.EquiDao.getbyPoint");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return equipids;
		
	}

	public List<Equipment> getEquips(List<PastCar> list) throws AppException{
		//Equipment equip = new Equipment();
		EquiDao equipdao = new EquiDao();
		List<Equipment> equiplist = new ArrayList<Equipment>();

		String devicecode = null;
		for(int i=0;i<list.size();i++){
			devicecode = list.get(i).getPastDevCode();
			equiplist.add(equipdao.getbyID(devicecode));
		}
		
		return equiplist;
	}

	public List<String> getEquipsByPoints(HashSet<Point> list) throws AppException{
		List<String> equiplist = new ArrayList<String>();
		String equipid = null;
		EquiDao equipdao = new EquiDao();
		
        for (Point p:list) {  
        	for(int i=0;i<equipdao.getbyPoint(p.getPointid()).size();i++){
            	equipid = equipdao.getbyPoint(p.getPointid()).get(i);
            	if(equipid!=null)
            		equiplist.add(equipid);
        	}
        }  
		
		return equiplist;
	}
	
	public List<Equipment> getbyName(String equipname) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<Equipment> list = new ArrayList<Equipment>();	
		
		try {
			
			conn = DBUtil.getConnection();
			
			if(equipname.equals("")||equipname.equals(null)){
				sql="select * from SYS_EQUIPMENT";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
			}
			else{
				sql = "select  * "
						+"from SYS_EQUIPMENT"
						+" where equname like ?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				equipname = "%"+equipname+"%";
				psmt.setString(1, equipname);
			}
 
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				Equipment equip = new Equipment();
				
				equip.setEquipID(rs.getString("equid"));
				equip.setEquipName(rs.getString("equname"));
				equip.setEquipState(rs.getInt("equstate"));
				equip.setEquipBPoint(rs.getString("equbindpoint"));
				
				list.add(equip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Equipment.getbyName");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	public List<String> getEquipsByPoints(List<Point> list) throws AppException{
		List<String> equiplist = new ArrayList<String>();
		String equipid = new String();
		EquiDao equipdao = new EquiDao();
		
        for (Point p:list) {  
        	for(int i=0;i<equipdao.getbyPoint(p.getPointid()).size();i++){
            	equipid = equipdao.getbyPoint(p.getPointid()).get(i);
            	if(equipid!=null)
            		equiplist.add(equipid);
        	}

        }  
		
		return equiplist;
	}
	
	public List<Equipment> getbyState(String state) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<Equipment> list = new ArrayList<Equipment>();	
		
		try {
			
			conn = DBUtil.getConnection();
			
			if(state.equals("")||state.equals(null)){
				sql="select * from SYS_EQUIPMENT";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
			}
			else{
				sql = "select  * "
						+"from SYS_EQUIPMENT"
						+" where equstate = ?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				int a = Integer.parseInt(state);
				psmt.setInt(1, a);
			}
 
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				Equipment equip = new Equipment();
				
				equip.setEquipID(rs.getString("equid"));
				equip.setEquipName(rs.getString("equname"));
				equip.setEquipState(rs.getInt("equstate"));
				equip.setEquipBPoint(rs.getString("equbindpoint"));
				
				list.add(equip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Equipment.getbyName");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	public boolean update(Equipment equip) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "Update"
					+" SYS_EQUIPMENT set EQUNAME=?,EQUSTATE=?,EQUBINDPOINT=?"
					+" where EQUID=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(4, equip.getEquipID());
			psmt.setInt(1, equip.getEquipState());
			psmt.setString(2, equip.getEquipBPoint());
			psmt.setString(3, equip.getEquipName());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("EquiDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

	public List<Equipment> search(String name,List<String> plist, String state) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Equipment> elist = new ArrayList<Equipment>();
		PointDao pointdao = new PointDao(); 
		int i = 0;
		int s = 0;
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_EQUIPMENT "
					+"where equname like ? ";
			
			if(plist.size()>0){
				sql = sql + "and equbindpoint in (";
				for(i = 0 ;i<plist.size()-1;i++)
				{
					sql = sql +" ?, ";
				}
				sql = sql +" ?) ";
			}
			if(state != null && !state.equals("")){
				s = Integer.parseInt(state);
				sql = sql + "and equstate = ? ";
			}


			// Pre-compiled sql
			
			System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			name = "%"+name+"%";
			psmt.setString(1, name);
			for(i = 0 ;i<plist.size();i++){
				psmt.setString(i+2, plist.get(i));
			}
			if(state != null && !state.equals("")){
				psmt.setInt(i+2,s);
			}
			

			// Query result set
			rs = psmt.executeQuery();
			
			
			while (rs.next()) {
				Equipment equip = new Equipment();
				
				equip.setEquipID(rs.getString("equid"));
				equip.setEquipName(rs.getString("equname"));
				equip.setEquipState(rs.getInt("equstate"));
				equip.setEquipBPoint(rs.getString("equbindpoint"));
				Point point = pointdao.getbyid(equip.getEquipBPoint());
				equip.setPointname(point.getPointname());
				
				elist.add(equip);
			}
			System.out.println("elist:"+elist.size());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("EquiDao.search");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return elist;
		
	}
}
