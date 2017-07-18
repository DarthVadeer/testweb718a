package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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

public class BlackApplyAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public BlackApplyAction() {
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
		boolean flag = false;
		BlackList blist = new BlackList();
		List<BlackList> list = new ArrayList<BlackList>();
		BlacklistDao blistdao = new BlacklistDao();
		
		String carnum = request.getParameter("Blackcarnumber");
		String sdate = request.getParameter("BlackApplydate");
		String peopid = request.getParameter("BlackApplicant");
		String reason = request.getParameter("BlackReason");
		Calendar c= Calendar.getInstance();
		String str1=Integer.toString(c.get(Calendar.YEAR));
		String str2=Integer.toString(c.get(Calendar.MONTH));
		String str3=Integer.toString(c.get(Calendar.DAY_OF_MONTH));
		String str4=Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String str5=Integer.toString(c.get(Calendar.MINUTE));
		String str6=Integer.toString(c.get(Calendar.SECOND));
		String id=str1+str2+str3+str4+str5+str6;
		
		carnum = java.net.URLDecoder.decode(carnum,"UTF-8");
		sdate = java.net.URLDecoder.decode(sdate,"UTF-8");
		peopid = java.net.URLDecoder.decode(peopid,"UTF-8");
		reason = java.net.URLDecoder.decode(reason,"UTF-8");
		id = java.net.URLDecoder.decode(id,"UTF-8");


		
		

		blist.setBlackID(id);
		blist.setBlackCarNum(carnum);
		blist.setBlackDcoDate(sdate);
		blist.setBlackReason(reason);
		blist.setBlackState(0);
		HttpSession session =request.getSession();
		String x = (String) session.getAttribute("userId");
		blist.setBlackApplicant(x);
		
		try {
			flag = blistdao.insert(blist);

			list.add(blist);
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray.toString());		
			PrintWriter writer = response.getWriter();
			if(flag)
				writer.println("1");
			else
				writer.println("2");
			
			writer.flush();
			writer.close();
		} catch (AppException e) {
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
