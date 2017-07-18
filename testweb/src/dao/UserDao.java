package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.People;
import util.AppException;
import util.DBUtil;

public class UserDao {
	
	public People login (String uId,String password) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		People people = new People();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_PERSONNEL "
					+"where PeopId = ? and PeopPassword = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, uId);
			psmt.setString(2, password);
		
			// Query result set
			rs = psmt.executeQuery();
			
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				people.setRole(rs.getString("PeopRole"));
				people.setName(rs.getString("peopname"));
				people.setPeopid(rs.getString("Peopid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.login");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return people;
	}

	public People getbyId (String uId) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		People people = new People();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_PERSONNEL "
					+"where PeopId = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, uId);
			// Query result set
			rs = psmt.executeQuery();
			
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				people.setName(rs.getString("PeopName"));
				people.setPassword(rs.getString("PeopPassword"));
				people.setRole(rs.getString("PeopRole"));
				people.setPeoSubdetach(rs.getString("PeopSubdetach"));
				people.setPhone(rs.getString("PeopPhone"));
				people.setPeopid(rs.getString("PeopId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.getbyId");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return people;
		
	}

	public boolean update(People people) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		boolean flag = false;
try {
			
			conn = DBUtil.getConnection();
			String sql = " update  "
					+"SYS_PERSONNEL set PeopPassword = ?, PeopName = ? ,PeopRole = ?, PeopSubdetach =?, PeopPhone = ?"
					+" where PeopId = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(6, people.getPeopid());
			psmt.setString(1, people.getPassword());
			psmt.setString(2, people.getName());
			psmt.setString(3, people.getRole());
			psmt.setString(4, people.getPeoSubdetach());
			psmt.setString(5, people.getPhone());
			
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public boolean insert(People people) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		boolean flag = false;
try {
			
			conn = DBUtil.getConnection();
			String sql = "insert into  "
					+"SYS_PERSONNEL "
					+"values(?,?,?,?,?,?) ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, people.getPeopid());
			psmt.setString(2, people.getPassword());
			psmt.setString(3, people.getName());
			psmt.setString(4, people.getRole());
			psmt.setString(5, people.getPeoSubdetach());
			psmt.setString(6, people.getPhone());
			
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.insert");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}

	public List<People> search(String id,String searchinfo,List<String> depIds,String Roleid) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<People> peoplelist = new ArrayList<People>();
		int i = 0;
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_PERSONNEL "
					+"where PeopName like ? ";
			
			if(depIds.size()>0){
				sql = sql + "and PeopSubdetach in (";
				for(i = 0 ;i<depIds.size()-1;i++)
				{
					sql = sql +" ?, ";
				}
				sql = sql +" ?) ";
			}
			if(Roleid!=null&&Roleid!="null"&&Roleid!="")
				sql = sql + "and PeopRole = ? ";
			if(id!=null&&id!="null"&&id!="")
				sql = sql + "and Peopid = ? ";
			// Pre-compiled sql
			System.out.println(id);
			System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			searchinfo = "%"+searchinfo+"%";
			psmt.setString(1, searchinfo);
			for(i = 0 ;i<depIds.size();i++){
				System.out.println(depIds.get(i));
				psmt.setString(i+2, depIds.get(i));
			}
			if(Roleid!=null&&Roleid!="null"&&Roleid!=""){
				psmt.setString(2+i++,Roleid);
				System.out.println("i+2"+i+2+"Roleid"+Roleid);
			}
			if(id!=null&&id!="null"&&id!=""){
				psmt.setString(2+i++,id);
				System.out.println("i+3"+i+3+"id"+id);
			}
			
			
				
			
			// Query result set
			rs = psmt.executeQuery();
			
			
			while (rs.next()) {
				People people = new People();
				people.setName(rs.getString("PeopName"));
				people.setRole(rs.getString("PeopRole"));
				people.setPassword(rs.getString("PeopPassword"));
				people.setPeoSubdetach(rs.getString("PeopSubdetach"));
				people.setPhone(rs.getString("PeopPhone"));
				people.setPeopid(rs.getString("PeopId"));
				peoplelist.add(people);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.search");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return peoplelist;
		
	}
	
	public boolean delete(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		boolean flag = false;
try {
			
			conn = DBUtil.getConnection();
			String sql = "Delete from "
					+"SYS_PERSONNEL"
					+" where PeopId = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			
			psmt.setString(1, id);
			
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.delete");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
		
	}


	public List<People> getAll() throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<People> list = new ArrayList<People>(); 
		People people = new People();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_PERSONNEL ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			// Query result set
			rs = psmt.executeQuery();
			
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				people.setName(rs.getString("PeopName"));
				people.setRole(rs.getString("PeopRole"));
				people.setPeoSubdetach(rs.getString("PeopSubdetach"));
				people.setPhone(rs.getString("PeopPhone"));
				people.setPeopid(rs.getString("PeopId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("UserDao.getAll");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return list;
		
	}
}