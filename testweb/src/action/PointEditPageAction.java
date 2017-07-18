package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Flags.Flag;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AppException;
import dao.PointDao;
import model.Point;

/**
 * Servlet implementation class PointEditPageAction
 */
public class PointEditPageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointEditPageAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String PointId = request.getParameter("PointId");
		String PointName = request.getParameter("PointName");
		String Longitude = request.getParameter("PointLongitude");
		String Latitude = request.getParameter("PointLatitude");
		Longitude = java.net.URLDecoder.decode(Longitude,"UTF-8");
		Latitude = java.net.URLDecoder.decode(Latitude,"UTF-8");
		PointName = java.net.URLDecoder.decode(PointName,"UTF-8");
		PointId = java.net.URLDecoder.decode(PointId,"UTF-8");
		boolean flag = false;
		Point point = new Point();
		point.setPointid(PointId);
		point.setPointname(PointName);
		point.setLongitude(Longitude);
		point.setLatitude(Latitude);
		try{
			PointDao pointdao = new PointDao();
			flag = pointdao.updatePoint(point);
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		request.setCharacterEncoding("UTF-8");
	}

}
