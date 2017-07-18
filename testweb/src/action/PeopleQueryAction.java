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
import dao.DepartDao;
import dao.UserDao;
import model.Department;
import model.People;

public class PeopleQueryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PeopleQueryAction() {
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
		response.setContentType("text/html;charset=UTF-8");
		
		UserDao userdao = new UserDao();
		DepartDao departdao=new DepartDao();
		List<String> list = new ArrayList<String>();
		List<People> list1 = new ArrayList<People>();
		String id = request.getParameter("poepleId");
		String name = request.getParameter("poepleName");
		String zongid = request.getParameter("poepleDep");
		
		String Roleid = request.getParameter("roleId");
		if(id==null)
			id="";
		
		System.out.println("Roleid:"+Roleid);
		System.out.println("name:"+name);
		System.out.println("zongid:"+zongid);
		try {
			list = departdao.getAllChild2(zongid);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			list1 = userdao.search(id,name, list, Roleid);
			JSONArray jsonArray = JSONArray.fromObject(list1);
			System.out.println("re"+jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		String id = request.getParameter("peopid");
		
		
		try {
			people = userdao.getbyId(id);
			request.setAttribute("people", people);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
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
