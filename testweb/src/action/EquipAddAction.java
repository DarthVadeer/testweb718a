package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import util.AppException;
import dao.EquiDao;
import model.Equipment;

public class EquipAddAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EquipAddAction() {
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
	
		String name = request.getParameter("equipName");
		String point = request.getParameter("equipPoint");
		String state = request.getParameter("equipState");
		
		System.out.println("name"+name);
		Calendar c= Calendar.getInstance();
		String str1=Integer.toString(c.get(Calendar.YEAR));
		String str2=Integer.toString(c.get(Calendar.MONTH));
		String str3=Integer.toString(c.get(Calendar.DAY_OF_MONTH));
		String str4=Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String str5=Integer.toString(c.get(Calendar.MINUTE));
		String str6=Integer.toString(c.get(Calendar.SECOND));
		String id=str1+str2+str3+str4+str5+str6;
		

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
			flag = equipdao.addEquipment(equip);

		
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(equip);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());		
		PrintWriter writer = response.getWriter();
		if(flag)
			writer.println(jsonArray.toString());
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
