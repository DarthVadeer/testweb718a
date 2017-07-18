package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import model.DepartForMenu;
import model.Department;
import model.People;
import model.Role;
import util.AppException;
import util.DBUtil;

public class DepartDao {
	
	//得到所有部门
	public List<Department> getAll() throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<Department> list = new ArrayList<Department>();	
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select  * "
					+"from SYS_DEPARTMENT";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'

			// Query result set
			rs = psmt.executeQuery();
						
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				Department depart=new Department();
				
				depart.setDepartID(rs.getString("DevID"));
				depart.setDepartType(rs.getString("DevType"));
				depart.setDepartName(rs.getString("DevName"));
				depart.setDepartLevel(rs.getInt("DevLevel"));
				depart.setDepartParent(rs.getString("DevParent"));
				depart.setDepartPrincipal(rs.getString("DevPrincipal"));
				depart.setDepartPhone(rs.getString("DevPhone"));
				depart.setDepartRemarks(rs.getString("DevRemarks"));
				depart.setDepartCode(rs.getString("DevDeptCode"));
				depart.setDepartSName(rs.getString("DevShort_Name"));
				depart.setDepartOfficer(rs.getString("DevOfficer"));

				list.add(depart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getAll");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;	
	}

	//插入部门
	public boolean insert(Department depart) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "insert into"
					+" SYS_DEPARTMENT "
					+"values(?,?,?,?,?,?,?,?,?,?,?)";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, depart.getDepartID());
			psmt.setString(2, depart.getDepartType());
			psmt.setString(3, depart.getDepartName());
			psmt.setInt(4, depart.getDepartLevel());
			psmt.setString(5, depart.getDepartParent());
			psmt.setString(6, depart.getDepartPrincipal());
			psmt.setString(7, depart.getDepartPhone());
			psmt.setString(8, depart.getDepartRemarks());
			psmt.setString(9, depart.getDepartCode());
			psmt.setString(10, depart.getDepartSName());
			psmt.setString(11, depart.getDepartOfficer());			
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.insert");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}
	
	//用ID或名称查询
	public List<Department> search(String serchinfo) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Department> list =new ArrayList<>();
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where DevID=?";
			

			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, serchinfo);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				Department depart=new Department();
				
				depart.setDepartID(rs.getString("DevID"));
				depart.setDepartType(rs.getString("DevType"));
				depart.setDepartName(rs.getString("DevName"));
				depart.setDepartLevel(rs.getInt("DevLevel"));
				depart.setDepartParent(rs.getString("DevParent"));
				depart.setDepartPrincipal(rs.getString("DevPrincipal"));
				depart.setDepartPhone(rs.getString("DevPhone"));
				depart.setDepartRemarks(rs.getString("DevRemarks"));
				depart.setDepartCode(rs.getString("DevDeptCode"));
				depart.setDepartSName(rs.getString("DevShort_Name"));
				depart.setDepartOfficer(rs.getString("DevOfficer"));

				list.add(depart);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.search");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;
	}

	//修改部门信息
	public boolean update(Department depart) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "Update"
					+" SYS_DEPARTMENT set DevType=?,DevName=?,DevLevel=?,DevParent=?,DevPrincipal=?,DevPhone=?,DevRemarks=?,DevDeptCode=?,DevShort_Name=?,DevOfficer=?"
					+" where DevID=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, depart.getDepartType());
			psmt.setString(2, depart.getDepartName());
			psmt.setInt(3, depart.getDepartLevel());
			psmt.setString(4, depart.getDepartParent());
			psmt.setString(5, depart.getDepartPrincipal());
			psmt.setString(6, depart.getDepartPhone());
			psmt.setString(7, depart.getDepartRemarks());
			psmt.setString(8, depart.getDepartCode());
			psmt.setString(9, depart.getDepartSName());
			psmt.setString(10, depart.getDepartOfficer());		
			psmt.setString(11, depart.getDepartID());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();
			
			
			if (result>0) {
				flag = true;
			}
						
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

	//获得 部门+子部门
	public List<Department> getChild(List<Department> list) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Department depart=null;
		if(list!=null){
			depart=list.get(0);
		}

			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where devparent=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, depart.getDepartID());
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				Department childdepart=new Department();
				
				childdepart.setDepartID(rs.getString("DevID"));
				childdepart.setDepartType(rs.getString("DevType"));
				childdepart.setDepartName(rs.getString("DevName"));
				childdepart.setDepartLevel(rs.getInt("DevLevel"));
				childdepart.setDepartParent(rs.getString("DevParent"));
				childdepart.setDepartPrincipal(rs.getString("DevPrincipal"));
				childdepart.setDepartPhone(rs.getString("DevPhone"));
				childdepart.setDepartRemarks(rs.getString("DevRemarks"));
				childdepart.setDepartCode(rs.getString("DevDeptCode"));
				childdepart.setDepartSName(rs.getString("DevShort_Name"));
				childdepart.setDepartOfficer(rs.getString("DevOfficer"));
				list.add(childdepart);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getChild");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;
	}

	
	public boolean delete(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "Delete from"
					+" SYS_DEPARTMENT"
					+" where devid=?";
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
			throw new AppException("DepartDao.delete");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}


	public List<People> getStaff(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<People> list =new ArrayList<>();
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_PERSONNEL "
					+"where peopsubdetach=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, id);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				People people=new People();
				
				people.setPeopid(rs.getString("peopid"));
				people.setPassword(rs.getString("peopname"));
				people.setName(rs.getString("peopname"));
				people.setRole(rs.getString("peoprole"));
				people.setPeoSubdetach(rs.getString("peopsubdetach"));
				people.setPhone(rs.getString("peopphone"));

				list.add(people);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getStaff");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;
	}

	// 通过等级查询部门
	public List<Department> getbyLevel(int level) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Department> list =new ArrayList<>();
		
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where Devlevel=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setInt(1, level);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				Department depart=new Department();
				
				depart.setDepartID(rs.getString("DevID"));
				depart.setDepartType(rs.getString("DevType"));
				depart.setDepartName(rs.getString("DevName"));
				depart.setDepartLevel(rs.getInt("DevLevel"));
				depart.setDepartParent(rs.getString("DevParent"));
				depart.setDepartPrincipal(rs.getString("DevPrincipal"));
				depart.setDepartPhone(rs.getString("DevPhone"));
				depart.setDepartRemarks(rs.getString("DevRemarks"));
				depart.setDepartCode(rs.getString("DevDeptCode"));
				depart.setDepartSName(rs.getString("DevShort_Name"));
				depart.setDepartOfficer(rs.getString("DevOfficer"));

				list.add(depart);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getbyLevel");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;
	}

	//获得部门的子部门
	public List<Department> getChild2(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where devparent=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, id);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				Department childdepart=new Department();
				
				childdepart.setDepartID(rs.getString("DevID"));
				childdepart.setDepartType(rs.getString("DevType"));
				childdepart.setDepartName(rs.getString("DevName"));
				childdepart.setDepartLevel(rs.getInt("DevLevel"));
				childdepart.setDepartParent(rs.getString("DevParent"));
				childdepart.setDepartPrincipal(rs.getString("DevPrincipal"));
				childdepart.setDepartPhone(rs.getString("DevPhone"));
				childdepart.setDepartRemarks(rs.getString("DevRemarks"));
				childdepart.setDepartCode(rs.getString("DevDeptCode"));
				childdepart.setDepartSName(rs.getString("DevShort_Name"));
				childdepart.setDepartOfficer(rs.getString("DevOfficer"));

				list.add(childdepart);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getChild2");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list;
	}


	public List<Department> getbyUser(String userid) throws AppException{
/*		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;*/
		People user= new People();
		UserDao userdao = new UserDao();
		List<Department> list = new ArrayList<Department>();
		List<Department> list1 = new ArrayList<Department>();
		Department depart = new Department();
		DepartDao departdao=new DepartDao();		
			
		user = userdao.getbyId(userid);
		String role = user.getRole();
		
		if(!role.equals("")){
			String subdetach = user.getPeoSubdetach();
			depart = departdao.search(subdetach).get(0);
			list.add(depart);
			depart = departdao.getParentName(depart);
			while(!depart.getDepartParent().equals("000")){
				depart = departdao.search(depart.getDepartParent()).get(0);
				depart = departdao.getParentName(depart);
				list.add(depart);
			}
		}
		else{
			list = departdao.getbyLevel(1);
		}
		for(int i = list.size();i>0;i--){
			list1.add(list.get(i));
		}
/*		//String role = user.getRole();
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where devid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, subdetach);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				Department childdepart=new Department();
				
				childdepart.setDepartID(rs.getString("DevID"));
				childdepart.setDepartType(rs.getString("DevType"));
				childdepart.setDepartName(rs.getString("DevName"));
				childdepart.setDepartLevel(rs.getInt("DevLevel"));
				String departid = rs.getString("DevParent");
				String departname=departdao.search(departid).get(0).getDepartName();
				String idname = departname +","+departid;
				childdepart.setDepartParent(idname);
				childdepart.setDepartPrincipal(rs.getString("DevPrincipal"));
				childdepart.setDepartPhone(rs.getString("DevPhone"));
				childdepart.setDepartRemarks(rs.getString("DevRemarks"));
				childdepart.setDepartCode(rs.getString("DevDeptCode"));
				childdepart.setDepartSName(rs.getString("DevShort_Name"));
				childdepart.setDepartOfficer(rs.getString("DevOfficer"));

				list.add(childdepart);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getChild2");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		*/
		return list1;
	}


	public List<String> getAllChild(String zongid,String zhiid,String daid,String zhongid) throws AppException{
/*		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;*/
		List<String> idlist = new ArrayList<String>();
		List<Department> list = new ArrayList<Department>();
		DepartDao departdao=new DepartDao();
			
		
		//String role = user.getRole();
		try {
			//conn = DBUtil.getConnection();
			
			if(zongid.equals("")){
				list=departdao.getAll();
				for(int i=0;i<list.size();i++){
					idlist.add(list.get(i).getDepartID());
				}
			}			
			else if(!zongid.equals("") && zhiid.equals("")){
				List<Department> zhilist = new ArrayList<Department>();
				List<Department> dalist = new ArrayList<Department>();
				List<Department> zhonglist = new ArrayList<Department>();
				zhilist = departdao.getChild2(zongid);
				for(int i=0;i<zhilist.size();i++){
					dalist.addAll(departdao.getChild2(zhilist.get(i).getDepartID()));
				}
				for(int i=0;i<dalist.size();i++){
					zhonglist.addAll(departdao.getChild2(dalist.get(i).getDepartID()));
				}
				list.addAll(zhilist);
				list.addAll(dalist);
				list.addAll(zhonglist);
				
				idlist.add(zongid);
				for(int i=0;i<list.size();i++){
					idlist.add(list.get(i).getDepartID());
				}
			}
			else if(!zongid.equals("") && !zhiid.equals("") && daid.equals("")){
				List<Department> dalist = new ArrayList<Department>();
				List<Department> zhonglist = new ArrayList<Department>();
				
				dalist = departdao.getChild2(zhiid);
				for(int i=0;i<dalist.size();i++){
					zhonglist.addAll(departdao.getChild2(dalist.get(i).getDepartID()));
				}
				
				list.addAll(dalist);
				list.addAll(zhonglist);
				
				idlist.add(zhiid);
				for(int i=0;i<list.size();i++){
					idlist.add(list.get(i).getDepartID());
				}
			}
			else if(!zongid.equals("") && !zhiid.equals("") && !daid.equals("") && zhongid.equals("")){
				List<Department> zhonglist = new ArrayList<Department>();
				
				zhonglist = departdao.getChild2(daid);
				
				idlist.add(daid);
				for(int i=0;i<list.size();i++){
					idlist.add(zhonglist.get(i).getDepartID());
				}
			}
			else if(!zongid.equals("") && !zhiid.equals("") && !daid.equals("") && !zhongid.equals("")){
				list = departdao.search(zhongid);
				
				for(int i=0;i<list.size();i++){
					idlist.add(list.get(i).getDepartID());
				}
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} finally {
			// Close the database operation object, release resources
/*			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);*/
		}
		
		return idlist;
	}


	public String getNamebyID(String id) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Department> list =new ArrayList<>();
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where  DevID=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, id);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				Department depart=new Department();
				
				depart.setDepartID(rs.getString("DevID"));
				depart.setDepartType(rs.getString("DevType"));
				depart.setDepartName(rs.getString("DevName"));
				depart.setDepartLevel(rs.getInt("DevLevel"));
				depart.setDepartParent(rs.getString("DevParent"));
				depart.setDepartPrincipal(rs.getString("DevPrincipal"));
				depart.setDepartPhone(rs.getString("DevPhone"));
				depart.setDepartRemarks(rs.getString("DevRemarks"));
				depart.setDepartCode(rs.getString("DevDeptCode"));
				depart.setDepartSName(rs.getString("DevShort_Name"));
				depart.setDepartOfficer(rs.getString("DevOfficer"));

				list.add(depart);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getNamebyID");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return list.get(0).getDepartName();
	}


	public List<DepartForMenu> getForMenu(String userid) throws AppException{
		People user= new People();
		UserDao userdao = new UserDao();
		List<Department> list = new ArrayList<Department>();
		List<DepartForMenu> menulist = new ArrayList<DepartForMenu>();
		Department depart = new Department();
		DepartForMenu menudepart = new DepartForMenu();
		DepartDao departdao=new DepartDao();
			
		user = userdao.getbyId(userid);
		String subdetach = user.getPeoSubdetach();
		String role = user.getRole();
		
		if(!role.equals("")){
			depart = departdao.search(subdetach).get(0);
			menudepart.setDepartID(depart.getDepartID());
			menudepart.setDepartName(depart.getDepartName());
			list.add(depart);
			menulist.add(menudepart);
			while(!depart.getDepartParent().equals("000")){
				depart = departdao.search(depart.getDepartParent()).get(0);
				DepartForMenu menudepart1 = new DepartForMenu();
				menudepart1.setDepartID(depart.getDepartID());
				menudepart1.setDepartName(depart.getDepartName());
				list.add(depart);
				menulist.add(menudepart1);
			}
		}
		else{
			list = departdao.getbyLevel(1);
			for(int i=0;i<list.size();i++){
				DepartForMenu menudepart1 = new DepartForMenu();
				menudepart1.setDepartID(list.get(i).getDepartID());
				menudepart1.setDepartName(list.get(i).getDepartName());
				menulist.add(menudepart1);
			}
		}
		return menulist;
	}


	public List<DepartForMenu> getChildMenu(String departid) throws AppException{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
		List<DepartForMenu> menulist = new ArrayList<DepartForMenu>();
			
		try {
			
			conn = DBUtil.getConnection();
			String sql = "select * "
					+"from SYS_DEPARTMENT "
					+"where devparent=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setString(1, departid);
			// Query result set
			rs = psmt.executeQuery();
			
			while(rs.next()){
				DepartForMenu child = new DepartForMenu();
				
				child.setDepartID(rs.getString("DevID"));	
				child.setDepartName(rs.getString("DevName"));

				menulist.add(child);
			}
			
			// Save user's information by using Pole entity object when queried the results set 
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("DepartDao.getChildMenu");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return menulist;
	}

	
	public Department getParentName(Department depart) throws AppException{
		DepartDao departdao = new DepartDao();
		Department pdepart = new Department();
		pdepart = departdao.search(depart.getDepartParent()).get(0);
		String pname = pdepart.getDepartName();
		String str = depart.getDepartParent()+","+pname;
		
		depart.setDepartParent(str);
		
		return depart;
	}


	public List<String> getAllChild2(String departid) throws AppException{
		DepartDao departdao = new DepartDao();
		List<Department> list = new ArrayList<Department>();
		List<Department> newlist = new ArrayList<Department>();
		List<String> idlist = new ArrayList<String>();
		int j= 0;
		
		list = departdao.getChild2(departid);
		while(true){
			for(int i = j; i<list.size()-j;i++){
				newlist.addAll(departdao.getChild2(list.get(i).getDepartID()));
			}
			if(newlist.size() != 0){
				j=list.size();
				list.addAll(newlist);
				newlist.clear();
			}
			else
				break;
		}
		
		idlist.add(departid);
		for(int i = 0;i<list.size();i++){
			idlist.add(list.get(i).getDepartID());
		}
		
		return idlist;
	}
}
