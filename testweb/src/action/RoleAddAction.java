package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleDao;
import model.Role;
import util.AppException;

public class RoleAddAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RoleAddAction() {
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
		Role role = new Role();
		RoleDao roledao=new RoleDao();

		boolean flag = false;
		
		String name = request.getParameter("RoleName");
		String menuname = request.getParameter("Rolemenunames");
		String menuid = request.getParameter("Rolemenuids");
		String remarks = request.getParameter("RoleDesc");
		 
		String id = name;
		System.out.println(name);
		 
		    try {
				Thread.sleep(1500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		role.setRoleid(id);
		role.setRolename(name);
		role.setRolemenunames(menuname);
		role.setRolemenuids(menuid);
		role.setRolerenarks(remarks);
		
		try {
			flag=roledao.adderole(role);
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
		System.out.println("Ҳ����");
		doGet(request,response);
	}

}
