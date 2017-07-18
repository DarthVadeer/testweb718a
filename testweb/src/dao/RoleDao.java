package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.People;
import model.Role;
import util.AppException;
import util.DBUtil;

public class RoleDao {
	public Role getbyid (String rId) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Role role = new Role();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_ROLE "
					+"where  roleid = ? ";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, rId);
			
			// Query result set
			rs = psmt.executeQuery();
			
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				role.setRoleid(rs.getString("roleid"));
				role.setRolename(rs.getString("rolename"));
				role.setRolemenunames(rs.getString("RoleMenuNames"));
				role.setRolemenuids(rs.getString("RoleMenuIds"));
				role.setRolerenarks(rs.getString("RoleRenarks"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("RoleDao.getbyid");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return role;
	}

	public boolean adderole(Role role) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "insert into SYS_ROLE " 
				+"values(?,?,?,?,?)";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(1, role.getRoleid());
			psmt.setString(2, role.getRolename());
			psmt.setString(3, role.getRolemenunames());
			psmt.setString(4, role.getRolemenuids());
			psmt.setString(5, role.getRolerenarks());
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			
		
		
		
			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.RoleDao.adderole");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	public boolean updaterole(Role role) throws AppException{
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		System.out.println("updaterole");
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "update SYS_ROLE " 
				+"set RoleName= ?, RoleMenuNames = ?,RoleMenuIds= ?, RoleRenarks = ? "
					+"where roleid = ? ";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

			// Set values for the placeholder 
			psmt.setString(5, role.getRoleid());
			psmt.setString(1, role.getRolename());
			psmt.setString(2, role.getRolemenunames());
			psmt.setString(3, role.getRolemenuids());
			psmt.setString(4, role.getRolerenarks());
			
			int result = -1;
			result = psmt.executeUpdate();// Execute update 
			System.out.println("result : " + result);

			if (result>0) {
				flag = true; // If affected lines greater than 0, so operation success

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.RoleDao.updaterole");
		} finally {
			// Close database object operation, release resources
			
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean removerole(String rId) throws AppException{
		boolean flag = true;
		boolean flag2 = true;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		UserDao userdao =new UserDao();
		List<People> list = new ArrayList<People>(); 
		
		list = userdao.getAll();
		
		for(int i = 0;i<list.size();i++){
			if(list.get(i).getRole().equals(rId))
				flag = false;
		}
		
		if(flag == true){
			try {
				// Create database connection
				conn = DBUtil.getConnection();
				//Declare operation statement,save contract information, "?" is a placeholder
				String sql = "delete from  SYS_ROLE " 
					+"where roleid = ?";
					
				// Pre-compiled sql, and return primary key
	            psmt = conn.prepareStatement(sql); 

				// Set values for the placeholder 
				psmt.setString(1, rId);

				int result = -1;
				result = psmt.executeUpdate();// Execute update 

				if (result>0) {
					flag2 = true; // If affected lines greater than 0, so
									// operation success
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException(
				"dao.RoleDao.removerole");
			} finally {
				// Close database object operation, release resources
				
				DBUtil.closeStatement(psmt);
				DBUtil.closeConnection(conn);
			}
		}
		
		return flag2;
	}
	
	public List<Role> getrolelist() throws AppException{
		
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Role> rolelist = new ArrayList<Role>();
		
		
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "select * from  SYS_ROLE " ;
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql); 

		
			
		
		
			rs = psmt.executeQuery();// Execute update 
			
		
		
		
			while (rs.next()) {
				Role role = new Role();
				role.setRoleid(rs.getString("roleid"));
				role.setRolename(rs.getString("rolename"));
				role.setRolemenunames(rs.getString("RoleMenuNames"));
				role.setRolemenuids(rs.getString("RoleMenuIds"));
				role.setRolerenarks(rs.getString("RoleRenarks"));
				rolelist.add(role);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.RoleDao.getrolelist");
		} finally {
			// Close database object operation, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return rolelist;
	}

}
