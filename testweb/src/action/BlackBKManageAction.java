package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlacklistDao;
import model.BlackList;
import net.sf.json.JSONArray;
import util.AppException;

public class BlackBKManageAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BlackBKManageAction() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BlacklistDao bdao = new BlacklistDao();
		int a = 0;
		List<BlackList> blist1 = new ArrayList<BlackList>();

		String carnum = request.getParameter("BlackCarNum");
		String state = request.getParameter("SelectState");
		// 发送 车牌号码、状态 返回 符合条件的黑名单信息对象集合
		try {
			if (state == null || state.equals("")) {
				a = 0;
			} else
				a = Integer.parseInt(state);
			if (carnum == null) {
				carnum = "";
			}
			blist1 = bdao.getbyNumState(carnum, a);

			JSONArray jsonArray = JSONArray.fromObject(blist1);
			System.out.println(jsonArray.toString());

			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();

		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
