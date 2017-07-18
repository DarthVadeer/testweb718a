package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Point;
import model.Road;
import util.AppException;
import dao.PointDao;
import dao.RoadDao;

public class RoadQueryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RoadQueryAction() {
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
		List<String> namelist = new  ArrayList<String>();
		RoadDao roaddao= new RoadDao();
		String names="";
		String name = request.getParameter("condition");
		if(name==null)
			name="";
		List<Road> list =new ArrayList<Road>();
		List<Point> pointlist = new ArrayList<Point>();
			
		try {
			PointDao pointdao = new PointDao();
			pointlist = pointdao.pointList("");
			request.setAttribute("pointlist", pointlist);
			list = roaddao.getbyName(name);
			
			for(int i=0;i<list.size();i++){
				names="";
				if(list.get(i).getRoadPoint()!=null&&!list.get(i).getRoadPoint().equals("")){
					namelist = pointdao.getNamesbyIDs(list.get(i).getRoadPoint());
				   for(int j=0;j<namelist.size();j++){
					names += namelist.get(j)+"\n";
					System.out.println("names"+names);
					list.get(i).setRoadpointname(names);
				}
				 

				}
				else
					   list.get(i).setRoadpointname("ÔÝÎÞ");
			}
			
			request.setAttribute("list", list);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/Views/Point/Road.jsp").forward(request,response);
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
