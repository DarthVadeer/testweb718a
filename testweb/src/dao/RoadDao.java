package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.Equipment;
import model.Point;
import model.Road;
import util.AppException;
import util.DBUtil;

public class RoadDao {
	
	public boolean insert(Road road) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "insert into"
					+" SYS_ROAD "
					+"values(?,?,?,?,?)";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, road.getRoadID());
			psmt.setString(2, road.getRoadName());
			psmt.setString(3, road.getRoadMPoint());
			psmt.setString(4, road.getRoadCPoint());
			psmt.setString(5, road.getRoadPoint());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Road.insert");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

	public List<Road> getAll() throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Road> list = new ArrayList<Road>();	
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_ROAD";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'

			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while (rs.next()) {
				Road road=new Road();
				
				road.setRoadID(rs.getString("roadid"));
				road.setRoadName(rs.getString("roadname"));
				road.setRoadMPoint(rs.getString("roadMPoint"));
				road.setRoadCPoint(rs.getString("roadCPoint"));
				road.setRoadPoint(rs.getString("roadpoint"));
				
				list.add(road);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("RoadDao.getAll");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	public List<Road> getbyName(String name) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql=null;
		
		List<Road> list = new ArrayList<Road>();	
			
		try {
			
			conn = DBUtil.getConnection();
			
			if(name.equals("")||name.equals(null)){
				sql="select * from SYS_ROAD";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
			}
			else{
				sql = "select  * "
						+"from SYS_ROAD"
						+" where roadname like ?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder  '?'
				name = "%"+name+"%";
				psmt.setString(1, name);
			}
 
			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			while(rs.next()) {
				Road road=new Road();
				
				road.setRoadID(rs.getString("roadid"));
				road.setRoadName(rs.getString("roadname"));
				road.setRoadMPoint(rs.getString("roadMPoint"));
				road.setRoadCPoint(rs.getString("roadCPoint"));
				road.setRoadPoint(rs.getString("roadpoint"));
				
				list.add(road);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("RoadDao.getbyName");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

 	public boolean update(Road road) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "Update"
					+" SYS_ROAD set roadname=?,roadpoint=?,roadmpoint=?,roadcpoint=?"
					+" where roadid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(5, road.getRoadID());
			psmt.setString(1, road.getRoadName());
			psmt.setString(3, road.getRoadMPoint());
			psmt.setString(4, road.getRoadCPoint());
			psmt.setString(2, road.getRoadPoint());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("RoadDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

	public boolean delete(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "Delete from"
					+" SYS_ROAD"
					+" where roadid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1,id);
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
		
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("RoadDao.delete");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

	public HashSet<Point> getPoints(String roadname) throws AppException{
		Road road = new Road();
		RoadDao roaddao = new RoadDao();
		List<Road> roadlist = new ArrayList<Road>();
		Point point = new Point();
		PointDao pointdao = new PointDao();
		List<Point> pointlist = new ArrayList<Point>();
		String[] pointids = new String[]{""};
		System.out.println("roadname"+roadname);
		if(roadname!=null&&!roadname.equals("") )
		{		
			roadlist = roaddao.getbyName(roadname);
			for(int i=0;i<roadlist.size();i++){
				road = roadlist.get(i);
				if(road.getRoadPoint()!=null){
					pointids = road.getRoadPoint().split(",");
					for(int j=0;j<pointids.length;j++){
						point = pointdao.getbyid(pointids[j]);
						pointlist.add(point);
					}
				}
			}			
		}
		else
			pointlist = pointdao.getAll();
		
		HashSet<Point> hs = new HashSet<Point>(pointlist); 
		return hs;	
	}
}
