package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import util.AppException;
import dao.AlarmDao;


import model.Alarm;



public class AlarmQueryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AlarmQueryAction() {
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
		
		List<Alarm> list = new ArrayList<Alarm>();
		AlarmDao alarmdao=new AlarmDao();
		String blackCarNum = request.getParameter("Blackcarnumber");
		Date beginTime=null;
		Date endTime=null;
		try {
			beginTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("BeginTime"));
			endTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("EndTime"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		try {
			
			list=alarmdao.getbyCarnumAndDate(blackCarNum,beginTime,endTime);
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(".jsp").forward(request, response);
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
