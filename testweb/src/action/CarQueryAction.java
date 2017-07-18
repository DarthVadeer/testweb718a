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
import model.PastCar;
import model.Point;

public class CarQueryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CarQueryAction() {
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
		response.setContentType("text/html;charset=UTF-8");
		PastcarDao pcardao=new PastcarDao();

		List<PastCar> list = new ArrayList<PastCar>();
	
	
		//List<PastCar> list2 = new ArrayList<PastCar>();
		
		//String carnum = request.getParameter("CarNum");
		//String type = request.getParameter("LicenseType");
		String pointName = request.getParameter("pointName");
		String begin = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		if(pointName==null)
			pointName="";
		System.out.println(begin);
		System.out.println(end);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			
			java.util.Date beginDate1 =  format.parse(begin);
			java.sql.Date beginDate = new java.sql.Date(beginDate1.getTime());
			java.util.Date endDate1 = format.parse(end);
			java.sql.Date endDate = new java.sql.Date(endDate1.getTime());
			
			list = pcardao.getbyDcodatePointName(beginDate, endDate,pointName);
			//list2 = pcardao.getbyType(type);
			
			
			
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (ParseException | AppException e) {
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
