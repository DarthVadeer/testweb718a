package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.BlackList;
import model.People;
import util.AppException;
import util.DBUtil;

public class BlacklistDao {
	// 插入一条黑名单记录
	public boolean insert(BlackList blist) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {

			conn = DBUtil.getConnection();
			String sql = "insert into" + " SYS_BLACKLIST " + "values(?,?,?,?,?,?,?)";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setString(1, blist.getBlackID());

			psmt.setString(2, blist.getBlackCarNum());
			psmt.setDate(3, Date.valueOf(blist.getBlackDcoDate()));
			psmt.setInt(4, blist.getBlackState());
			psmt.setString(5, blist.getBlackApplicant());
			psmt.setString(6, blist.getBlackConPoint());
			psmt.setString(7, blist.getBlackReason());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();

			if (result > 0) {
				flag = true;
			}

			// Save user's information by using Pole entity object when queried
			// the results set

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Blacklist.insert");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	// 查询所有的黑名单记录
	public List<BlackList> getAll() throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();
			String sql = "select  * " + "from SYS_BLACKLIST";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			if (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));

				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));
				blist.setBlackReason(rs.getString("blackReason"));
				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getAll");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;

	}

	// 通过黑名单状态查询黑名单信息
	public List<BlackList> getbyState(int state) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();
			String sql = "select  * " + "from SYS_BLACKLIST" + " where blackstate=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setInt(1, state);
			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			if (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));

				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));
				blist.setBlackReason(rs.getString("blackReason"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyState");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	// 通过申请时间查询黑名单信息
	public List<BlackList> getbyDate(Date beginDate, Date endDate) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();

			if (beginDate.equals("") && endDate.equals(""))
				sql = "select * from SYS_BLACKLIST";
			else if (!beginDate.equals("") && !endDate.equals("")) {
				sql = "select  * " + "from SYS_BLACKLIST" + " where balckdcollectiondate>? and balckdcollection<?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setDate(1, beginDate);
				psmt.setDate(2, endDate);
			} else if (!beginDate.equals("") && endDate.equals("")) {
				sql = "select  * " + "from SYS_BLACKLIST" + " where balckdcollectiondate>?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setDate(1, beginDate);
			} else if (beginDate.equals("") && !endDate.equals("")) {
				sql = "select  * " + "from SYS_BLACKLIST" + " where balckdcollection<?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setDate(1, endDate);
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			if (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));

				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));
				blist.setBlackReason(rs.getString("blackReason"));
				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyDate");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	// 通过车牌查询黑名单信息
	public List<BlackList> getbyCarnum(String carnum) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();

			if (carnum.equals(""))
				sql = "select * from SYS_BLACKLIST";
			else {
				sql = "select  * " + "from SYS_BLACKLIST" + " where Blackcarnumber=?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setString(1, carnum);
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			if (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));

				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));
				blist.setBlackReason(rs.getString("blackReason"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyCarnum");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	// 通过黑名单编号、状态查询黑名单信息
	public List<BlackList> getbyNumState(String carnum, int State) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();

			if (carnum.equals(""))
				sql = "select * from SYS_BLACKLIST";
			else {

				if (State == 100) {
					sql = "select  * " + "from SYS_BLACKLIST"
							+ " where (Blackcarnumber=? and BlackState = 2) or (Blackcarnumber=? and BlackState = 3)";
					// Pre-compiled sql
					psmt = conn.prepareStatement(sql);
					// Set values for the placeholder '?'
					psmt.setString(1, carnum);
					psmt.setString(2, carnum);

				} else {

					sql = "select  * " + "from SYS_BLACKLIST" + " where Blackcarnumber=? and BlackState = ?";
					// Pre-compiled sql
					psmt = conn.prepareStatement(sql);
					// Set values for the placeholder '?'
					psmt.setString(1, carnum);
					psmt.setInt(2, State);

				}
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			if (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blackid"));

				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplicant"));
				blist.setBlackConPoint(rs.getString("blackconpoint"));
				blist.setBlackReason(rs.getString("blackReason"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyCarnum");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	// 通过号牌种类查询黑名单信息
	public List<BlackList> getbyLictype(String type) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();
			if (type.equals(""))
				sql = "select * from SYS_BLACKLIST";
			else {
				sql = "select  * " + "from SYS_BLACKLIST,SYS_PASTCAR"
						+ " where SYS_BLACKLIST.blackcarnumber=SYS_PASTCAR.pastcarnumber and SYS_PASTCAR.pastlicensetype=?";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setString(1, type);
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			if (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));

				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));
				blist.setBlackReason(rs.getString("blackReason"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyLictype");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	public boolean update(BlackList blist) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {

			conn = DBUtil.getConnection();
			String sql = "Update"
					+ " SYS_BLACKLIST set blackcarnumber=?,blackcollectiondate=?,blackstate=?,blackapplication=?,blackconpoint=?, blackreason=?"
					+ " where blackid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setString(7, blist.getBlackID());

			psmt.setString(1, blist.getBlackCarNum());
			psmt.setDate(2, Date.valueOf(blist.getBlackDcoDate()));
			psmt.setInt(3, blist.getBlackState());
			psmt.setString(4, blist.getBlackApplicant());
			psmt.setString(5, blist.getBlackConPoint());
			psmt.setString(6, blist.getBlackReason());
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();

			if (result > 0) {
				flag = true;
			}

			// Save user's information by using Pole entity object when queried
			// the results set

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	// 布控更新 由ID更新布控点位、状态
	public boolean BKupdate(BlackList blist) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {

			conn = DBUtil.getConnection();
			String sql = "Update" + " SYS_BLACKLIST set blackstate=?,blackconpoint=?" + " where blackid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setString(3, blist.getBlackID());
			psmt.setInt(1, blist.getBlackState());
			psmt.setString(2, blist.getBlackConPoint());

			// Query result set
			int result = -1;
			result = psmt.executeUpdate();

			if (result > 0) {
				flag = true;
			}
			// Save user's information by using Pole entity object when queried
			// the results set

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	public boolean BKRevokeUpdate(BlackList blist) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {

			conn = DBUtil.getConnection();
			String sql = "Update" + " SYS_BLACKLIST set blackstate=?" + " where blackid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'

			psmt.setInt(1, blist.getBlackState());
			psmt.setString(2, blist.getBlackID());

			// Query result set
			int result = -1;
			result = psmt.executeUpdate();

			if (result > 0) {
				flag = true;
			}
			// Save user's information by using Pole entity object when queried
			// the results set

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.update");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	public List<BlackList> getbyID(String carnum) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();

			if (carnum.equals("") || carnum.equals(null))
				sql = "select * from SYS_BLACKLIST";
			else {
				sql = "select  * " + "from SYS_BLACKLIST" + " where Blackid = ? ";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setString(1, carnum);
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			while (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));
				blist.setBlackReason(rs.getString("blackReason"));
				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyCarnum");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	public List<BlackList> getbyApplicant(String carnum) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();

			if (carnum.equals("") || carnum.equals(null))
				sql = "select * from SYS_BLACKLIST";
			else {
				sql = "select  * " + "from SYS_BLACKLIST" + " where Blackapplicant = ? ";
				// Pre-compiled sql
				psmt = conn.prepareStatement(sql);
				// Set values for the placeholder '?'
				psmt.setString(1, carnum);
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			while (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));
				blist.setBlackReason(rs.getString("blackReason"));
				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getbyCarnum");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	public boolean setState(String id, int state) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try {

			conn = DBUtil.getConnection();
			String sql = "Update" + " SYS_BLACKLIST set blackstate=?" + " where blackid=?";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setString(2, id);
			psmt.setInt(1, state);
			// Query result set
			int result = -1;
			result = psmt.executeUpdate();

			if (result > 0) {
				flag = true;
			}

			// Save user's information by using Pole entity object when queried
			// the results set

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.setState");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;

	}

	public List<BlackList> getBKList(String carnum, int state) throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();

		try {

			conn = DBUtil.getConnection();

			sql = "select * from SYS_BLACKLIST where blackcarnumber like ? and blackstate = ?";

			carnum = "%" + carnum + "%";

			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setString(1, carnum);
			psmt.setInt(2, state);
			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			while (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));
				blist.setBlackReason(rs.getString("blackReason"));
				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getBKList");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}

	private String dateToString(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String str = sdf.format(date);
		return str;
	}

	public List<BlackList> getBKList2(String begin, String end, String state, String id)
			throws AppException, ParseException {
		DepartDao departdao = new DepartDao();
		List<String> idlist = new ArrayList<String>();
		List<People> plist = new ArrayList<People>();
		List<String> pidlist = new ArrayList<String>();
		People people = new People();
		UserDao userdao = new UserDao();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		people = userdao.getbyId(id);
		idlist = departdao.getAllChild2(people.getPeoSubdetach());
		plist = userdao.search("", "", idlist, "");
		System.out.println("plist" + plist.size());
		for (int i = 0; i < plist.size(); i++) {
			pidlist.add(plist.get(i).getPeopid());
		}
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = null;

		List<BlackList> list = new ArrayList<BlackList>();
		System.out.println(begin + end);
		try {

			conn = DBUtil.getConnection();

			sql = "select * from SYS_BLACKLIST where blackcarnumber like ?";

			String carnum = "";
			carnum = "%" + carnum + "%";

			if (pidlist.size() > 0) {
				sql = sql + " and BlackApplicant in (";
				for (int i = 0; i < pidlist.size() - 1; i++) {
					sql = sql + "?,";
				}
				sql = sql + "?)";
			}

			if (state != null && !state.equals("") && !state.equals("100")) {
				sql = sql + " and blackstate = ?";
			}

			if (begin != null && !begin.equals("")) {
				sql = sql + " and Blackdcollectiondate > ?";
			}

			if (end != null && !end.equals("")) {
				sql = sql + " and Blackdcollectiondate < ?";
			}

			System.out.println(sql);
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setString(1, carnum);

			if (pidlist.size() > 0) {
				for (int i = 0; i < pidlist.size(); i++) {
					psmt.setString(2 + i, pidlist.get(i));
				}
			}
			int j = 2 + pidlist.size();
			System.out.println("j1:" + j);
			if (state != null && !state.equals("") && !state.equals("100")) {
				int a = Integer.parseInt(state);
				psmt.setInt(j, a);
				j = j + 1;
				System.out.println("j2:" + j);
			}

			if (begin != null && !begin.equals("")) {
				java.util.Date beginDate1 = format.parse(begin);
				java.sql.Date beginDate = new java.sql.Date(beginDate1.getTime());
				psmt.setDate(j, beginDate);
				j = j + 1;
				System.out.println("j3:" + j);
			}

			if (end != null && !end.equals("")) {
				java.util.Date endDate1 = format.parse(begin);
				java.sql.Date endDate = new java.sql.Date(endDate1.getTime());
				psmt.setDate(j, endDate);
				j = j + 1;
				System.out.println("j4:" + j);
			}

			// Query result set
			rs = psmt.executeQuery();

			// Save user's information by using Pole entity object when queried
			// the results set
			while (rs.next()) {
				BlackList blist = new BlackList();

				blist.setBlackID(rs.getString("blacklist"));
				blist.setBlackReason(rs.getString("blackReason"));
				blist.setBlackCarNum(rs.getString("blackcarnumber"));
				blist.setBlackDcoDate(dateToString(rs.getDate("blackdcollectiondate")));
				blist.setBlackState(rs.getInt("blackstate"));
				blist.setBlackApplicant(rs.getString("blackapplication"));
				blist.setBlackConPoint(rs.getString("blackpoint"));

				list.add(blist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("BlacklistDao.getBKList2");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return list;
	}
}
