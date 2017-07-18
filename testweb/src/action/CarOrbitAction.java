package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
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
import dao.EquiDao;
import dao.PastcarDao;
import dao.PointDao;
import model.Equipment;
import model.Location;
import model.PastCar;
import model.Point;

public class CarOrbitAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CarOrbitAction() {
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
		PastcarDao pcardao = new PastcarDao();
	
		List<Location> list3 = new ArrayList<Location>();
		String begin = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		String carnum = request.getParameter("condition");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		if (carnum ==null)
			carnum="";
		try {
			
			java.util.Date beginDate1 =  format.parse(begin);
			java.sql.Date beginDate = new java.sql.Date(beginDate1.getTime());
			java.util.Date endDate1 = format.parse(end);
			java.sql.Date endDate = new java.sql.Date(endDate1.getTime());
			
			list3 = pcardao.getbyCarnumAndDate(carnum,beginDate,endDate);
		
			
			
			JSONArray jsonArray = JSONArray.fromObject(list3);
			System.out.println(jsonArray.toString());
			
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
