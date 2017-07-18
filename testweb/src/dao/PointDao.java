package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Point;
import util.AppException;
import util.DBUtil;

public class PointDao {
	public Point getbyid (String pId) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Point point = new Point();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_POINT "
					+"where  PointId = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, pId);
			
			// Query result set
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				point.setPointid(rs.getString("PointId"));
				point.setPointname(rs.getString("PointName"));
				point.setLongitude(rs.getString("PointLongitude"));
				point.setLatitude(rs.getString("PointLatitude"));
				point.setEquipment(rs.getInt("PointEquipment"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("PointDao.getbyid");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return point;
	}

	public boolean addPoint(Point point) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "insert into SYS_POINT " 
				+"values(?,?,?,?,?)";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, point.getPointid());
			psmt.setString(2, point.getPointname());
			psmt.setString(3, point.getLongitude());
			psmt.setString(4, point.getLatitude());
			psmt.setInt(5, point.getEquipment());
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			
		
		
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.PointDao.addpoint");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public boolean removePointbyid(String pId) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
	
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "delete  from  SYS_POINT " 
				+"where pointid  = ? ";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, pId);
			
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 

			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.PointDao.removepointbyid");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean updatePoint(Point point) throws AppException {
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "update SYS_POINT " 
					+"set PointLongitude = ? ,PointLatitude = ?,PointName = ? "
				+"where pointid = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(4, point.getPointid());
			psmt.setString(1, point.getLongitude());
			psmt.setString(2, point.getLatitude());
			psmt.setString(3, point.getPointname());
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			
		
		
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.PointDao.updatepoint");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public boolean addEquibyid(String pId) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "update SYS_POINT " 
					+"set PointEquipment = PointEquipment + 1 "
				+"where pointid = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, pId);
	
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.PointDao.addEquibyid");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	public boolean removeEquibyid(String pId) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "update SYS_POINT " 
					+"set PointEquipment = PointEquipment - 1 "
				+"where pointid = ?";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, pId);
	
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.PointDao.removeEquibyid");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public List<Point> pointList(String condition) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Point> pointlist = new ArrayList<Point>();
		condition = "%"+condition+"%";
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_POINT "
					+"where  Pointname like ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, condition);
			
			// Query result set
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				Point point = new Point();
				point.setPointid(rs.getString("PointId"));
				point.setPointname(rs.getString("PointName"));
				point.setLongitude(rs.getString("PointLongitude"));
				point.setLatitude(rs.getString("PointLatitude"));
				point.setEquipment(rs.getInt("PointEquipment"));
				pointlist.add(point);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("PointDao.pointList");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return pointlist;
	}

		
	public Point getbyName(String pname) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Point point = new Point();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_POINT "
					+"where  PointName = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, pname);
			
			// Query result set
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				point.setPointid(rs.getString("PointId"));
				point.setPointname(rs.getString("pointname"));
				point.setLongitude(rs.getString("PointLongitude"));
				point.setLatitude(rs.getString("PointLatitude"));
				point.setEquipment(rs.getInt("PointEquipment"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("PointDao.getbyName");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return point;
	}

	public List<Point> getbyName2(String pname) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<Point> list =new ArrayList<Point>();
		
		try {
			
			conn = DBUtil.getConnection();
			if(pname!=null&&!pname.equals("")  ){
				String sql = "select  * "
						+"from SYS_POINT "
						+"where  PointName like ? ";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				pname = "%"+pname+"%";
				psmt.setString(1, pname);
				
			}
			else{
				String sql = "select  * "
						+"from SYS_POINT";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
			}
	
			// Query result set
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				Point point = new Point();
				
				point.setPointid(rs.getString("PointId"));
				point.setPointname(rs.getString("pointname"));
				point.setLongitude(rs.getString("PointLongitude"));
				point.setLatitude(rs.getString("PointLatitude"));
				point.setEquipment(rs.getInt("PointEquipment"));
				
				list.add(point);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("PointDao.getbyName2");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	public List<Point> getAll() throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<Point> list =new ArrayList<Point>();
		
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_POINT";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			
			// Query result set
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				Point point = new Point();
				
				point.setPointid(rs.getString("PointId"));
				point.setPointname(rs.getString("pointname"));
				point.setLongitude(rs.getString("PointLongitude"));
				point.setLatitude(rs.getString("PointLatitude"));
				point.setEquipment(rs.getInt("PointEquipment"));
				
				list.add(point);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("PointDao.getAll");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}

	public List<String> getNamesbyIDs(String ids) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<String> list =new ArrayList<String>();
		String[] id = ids.split(",");
		
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_POINT where pointid in(";
			
			for(int i=0;i<id.length-1;i++){
				sql = sql+"?,";
			}
			sql = sql + "?)";
			
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			for(int i=0;i<id.length;i++){
				psmt.setString(i+1, id[i]);
			}
			// Query result set
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				Point point = new Point();
				
				point.setPointid(rs.getString("PointId"));
				point.setPointname(rs.getString("pointname"));
				point.setLongitude(rs.getString("PointLongitude"));
				point.setLatitude(rs.getString("PointLatitude"));
				point.setEquipment(rs.getInt("PointEquipment"));
				
				list.add(point.getPointname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("PointDao.getNamesbyIDs");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
	}
}