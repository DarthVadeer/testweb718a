package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BlacklistDao;
import model.BlackList;
import net.sf.json.JSONArray;
import util.AppException;

public class BlackVerifyQueryAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BlackVerifyQueryAction() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BlacklistDao bdao = new BlacklistDao();
		List<BlackList> blist1=new ArrayList<BlackList>();
		HttpSession session =request.getSession();
		String x = (String) session.getAttribute("userId");
		
		String state = request.getParameter("ApplyState");
		String begin = request.getParameter("BeginDate");
		String end = request.getParameter("EndDate");

		try {
			blist1 = bdao.getBKList2(begin, end, state, x);
			System.out.println("blist1" + blist1.size());
			JSONArray jsonArray = JSONArray.fromObject(blist1);
			System.out.println(jsonArray.toString());		
			System.out.println("000");
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
