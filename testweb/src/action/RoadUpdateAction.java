package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Road;
import util.AppException;
import dao.RoadDao;

public class RoadUpdateAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RoadUpdateAction() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		RoadDao roaddao= new RoadDao();
		Road road=new Road();
		boolean flag=false;
		String id = request.getParameter("RoadId");
		String name = request.getParameter("RoadName");
		String point = request.getParameter("RoadPoint");
		String cpoint = request.getParameter("RoadCPoint");
		String mpoint = request.getParameter("RoadPosition");
		
		
		id = java.net.URLDecoder.decode(id,"UTF-8");
		name = java.net.URLDecoder.decode(name,"UTF-8");
		point = java.net.URLDecoder.decode(point,"UTF-8");
		cpoint = java.net.URLDecoder.decode(cpoint,"UTF-8");
		mpoint = java.net.URLDecoder.decode(mpoint,"UTF-8");
		
		road.setRoadID(id);
		road.setRoadName(name);
		road.setRoadPoint(point);
		road.setRoadCPoint(cpoint);
		System.out.println("c+"+cpoint);
		road.setRoadMPoint(mpoint);
			
		try {
			flag = roaddao.update(road);
		} catch (AppException e) {
			// TODO Auto-generated catch block
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
