package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import util.AppException;
import model.Equipment;
import model.PastCar;
import model.Point;
import dao.EquiDao;
import dao.PastcarDao;
import dao.PointDao;

public class CarInfoAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CarInfoAction() {
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

		EquiDao equipdao = new EquiDao();
		Equipment equip = new Equipment();
		Point point = new Point();
		PointDao pointdao = new PointDao();
	
		
		String id = request.getParameter("equipId");
		try {
			equip=equipdao.getbyID(id);
			point= pointdao.getbyid(equip.getEquipBPoint());
			
			String location ="";
			location+=point.getLongitude();
			location+=",";
			location+=point.getLatitude();
	
			
			PrintWriter writer = response.getWriter();
			writer.println(location);
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
