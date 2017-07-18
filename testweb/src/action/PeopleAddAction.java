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
import dao.UserDao;
import model.People;

public class PeopleAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PeopleAddAction() {
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
		People people = new People();
		List<People> list = new ArrayList<People>();
		UserDao userdao = new UserDao();
		boolean flag = false;
		
		String id = request.getParameter("PersonnelId");
		String password = request.getParameter("PersonnelPassword");
		String name = request.getParameter("PersonnelName");
		String role = request.getParameter("PersonnelRole");
		String subdetach = request.getParameter("PersonnelDepId");
		String phone=request.getParameter("PersonnelPhone");
		
		id = java.net.URLDecoder.decode(id,"UTF-8");
		password = java.net.URLDecoder.decode(password,"UTF-8");
		name = java.net.URLDecoder.decode(name,"UTF-8");
		role = java.net.URLDecoder.decode(role,"UTF-8");
		subdetach = java.net.URLDecoder.decode(subdetach,"UTF-8");
		phone = java.net.URLDecoder.decode(phone,"UTF-8");
		
		people.setPeopid(id);
		people.setName(name);
		people.setPassword(password);
		people.setRole(role);
		people.setPeoSubdetach(subdetach);
		people.setPhone(phone);
		
		try {
			flag = userdao.insert(people);
			if(flag)
			list.add(people);
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter writer = response.getWriter();
		if(flag == false)
		
		
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
