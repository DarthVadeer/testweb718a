package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AppException;
import dao.RoadDao;
import model.Road;

public class RoadAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RoadAddAction() {
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
		
		RoadDao roaddao= new RoadDao();
		Road road=new Road();
		boolean flag=false;
		String id = "";
		String name = request.getParameter("RoadName");
		String point=request.getParameter("RoadPoint");
		String cpoint = request.getParameter("RoadCPoint");
		String mpoint = request.getParameter("RoadPosition");
		System.out.println("cpoint:"+cpoint);
		name = java.net.URLDecoder.decode(name,"UTF-8");
		point = java.net.URLDecoder.decode(point,"UTF-8");
		cpoint = java.net.URLDecoder.decode(cpoint,"UTF-8");
		mpoint = java.net.URLDecoder.decode(mpoint,"UTF-8");
		System.out.println("cpoint:"+cpoint);
		String[] toid=cpoint.split(",");
		for(int i=0;i<toid.length;i++){
			id+=toid[i];
		}
		System.out.println("id:"+id);
		id = id.replace(".","");
		road.setRoadID(id);
		road.setRoadName(name);
		road.setRoadPoint(point);
		road.setRoadCPoint(cpoint);
		road.setRoadMPoint(mpoint);
			
		try {
			flag = roaddao.insert(road);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter writer = response.getWriter();
		if(flag)
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
