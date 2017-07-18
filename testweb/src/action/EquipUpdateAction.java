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
import dao.EquiDao;
import model.Equipment;

public class EquipUpdateAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EquipUpdateAction() {
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
		boolean flag = false;
		request.setCharacterEncoding("UTF-8");
		Equipment equip = new Equipment();
		EquiDao equipdao = new EquiDao();
		List<Equipment> list = new ArrayList<Equipment>();
		
		String id = request.getParameter("equipId");
		String name = request.getParameter("equipName");
		String state = request.getParameter("equipState");
		String point = request.getParameter("equipPoint");
		
		point = java.net.URLDecoder.decode(point,"UTF-8");
		name = java.net.URLDecoder.decode(name,"UTF-8");
		state = java.net.URLDecoder.decode(state,"UTF-8");
		id = java.net.URLDecoder.decode(id,"UTF-8");
		
		int level=0;
		if(state.equals(null)){
			level=Integer.parseInt(state);
		}
		
		equip.setEquipID(id);
		equip.setEquipName(name);
		equip.setEquipState(level);
		equip.setEquipBPoint(point);
		
		try {
			flag=equipdao.update(equip);
	
			PrintWriter writer = response.getWriter();
			if(flag)
				writer.println("1");
			else
				writer.println("2");
			
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
