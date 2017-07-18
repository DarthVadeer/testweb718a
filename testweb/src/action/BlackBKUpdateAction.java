package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlacklistDao;
import model.BlackList;
import util.AppException;

public class BlackBKUpdateAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BlackBKUpdateAction() {
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
		BlackList blacklist = new BlackList();
		BlacklistDao bdao = new BlacklistDao();
		boolean flag = false;

		String points = request.getParameter("BlackConPoint");
		String id = request.getParameter("BlackId");

		blacklist.setBlackConPoint(points);
		blacklist.setBlackID(id);
		blacklist.setBlackState(2);

		try {
			flag = bdao.BKRevokeUpdate(blacklist);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter writer = response.getWriter();
		if (flag)
			writer.println("1");
		else
			writer.println("2");

		writer.flush();
		writer.close();
	}

}
