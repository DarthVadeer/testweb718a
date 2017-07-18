package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PointDao;
import util.AppException;
import model.Point;

/**
 * Servlet implementation class PointAddAction
 */
public class PointAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointAddAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Longitude =request.getParameter("PointLongitude");
		System.out.println(Longitude);
		String Latitude = request.getParameter("PointLatitude");
		String PointName = request.getParameter("PointName");
		System.out.println(PointName);
		String pId = Longitude+Latitude;
		Longitude = java.net.URLDecoder.decode(Longitude,"UTF-8");
		Latitude = java.net.URLDecoder.decode(Latitude,"UTF-8");
		PointName = java.net.URLDecoder.decode(PointName,"UTF-8");
		System.out.println(PointName);
		pId = java.net.URLDecoder.decode(pId,"UTF-8");
		System.out.println(pId);
		pId = pId.replace(".","");
		Point point = new Point();
		point.setPointid(pId);
		point.setPointname(PointName);
		point.setLongitude(Longitude);
		point.setLatitude(Latitude);
		point.setEquipment(0);
		boolean flag = false;
		try{
			PointDao pointdao = new PointDao();
			flag=pointdao.addPoint(point);
			
		}
		catch (AppException e) {
			e.printStackTrace();
	}
		PrintWriter writer = response.getWriter();
		if(flag == true)
		
		writer.println("1");
		else 
			writer.println("2");
		
		writer.flush();
		writer.close();
		

	}
}
