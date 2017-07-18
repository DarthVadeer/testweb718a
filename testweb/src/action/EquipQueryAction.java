package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import util.AppException;
import dao.EquiDao;
import dao.PointDao;
import dao.RoadDao;
import model.Equipment;
import model.Point;

public class EquipQueryAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EquipQueryAction() {
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
		HashSet<Point> plist = new HashSet<Point>();
		List<Point> plist2 =new ArrayList<Point>();
		List<Equipment> elist = new ArrayList<Equipment>();
 		List<String> elist1 = new ArrayList<String>();
		List<String> elist2 = new ArrayList<String>();
		int state = 0;
		
		RoadDao roaddao = new RoadDao();
		PointDao pointdao = new PointDao();
		EquiDao equipdao = new EquiDao();
		
		String roadname = request.getParameter("conditionRoad");
		String pointname = request.getParameter("conditionPoint");
		String equipname = request.getParameter("conditionName");
		String equipstate = request.getParameter("conditionState");
		
		try {
			plist = roaddao.getPoints(roadname);			
			System.out.println("1:"+plist.size());	
			for(Point p:plist){
				elist1.add(p.getPointid());
			}

			plist2 = pointdao.getbyName2(pointname);		
			System.out.println("1:"+plist2.size());	
			for(Point p:plist2){
				elist2.add(p.getPointid());
			}
			System.out.println("1:"+elist2.size());	
			System.out.println("2:"+elist1.size());	
			elist2.retainAll(elist1);
			System.out.println("3:"+elist2.size());	
			for(int i=0;i<elist2.size();i++){
				System.out.println("elist2:"+elist2.get(i));
			}
			System.out.println("equipname"+equipname);
			System.out.println("equipstate"+equipstate);
			/*if(equipstate != null && !equipstate.equals(""))
				state = Integer.parseInt(equipstate);
			else
				state = 0;*/
			elist = equipdao.search(equipname, elist2, equipstate);
	/*		for(int i=0;i<equipdao.getbyName(equipname).size();i++){
				elist3.add(equipdao.getbyName(equipname).get(i).getEquipID());
			}
			for(int i=0;i<equipdao.getbyState(equipstate).size();i++){
				elist4.add(equipdao.getbyState(equipstate).get(i).getEquipID());
			}
			
			elist1.add("123");
			elist1.retainAll(elist2);
			elist1.add("123");
			elist1.retainAll(elist3);
			elist1.add("123");
			elist1.retainAll(elist4);
			
			for(int i=0;i<elist1.size();i++){
				elist.add(equipdao.getbyID(elist1.get(i)));
			}
			*/
			JSONArray jsonArray = JSONArray.fromObject(elist);
			System.out.println(jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
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
