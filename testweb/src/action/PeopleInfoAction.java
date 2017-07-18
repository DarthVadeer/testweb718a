package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.People;
import util.AppException;
import dao.DepartDao;
import dao.RoleDao;
import dao.UserDao;

public class PeopleInfoAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PeopleInfoAction() {
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
		UserDao userdao=new UserDao();
		People people = new People();
		DepartDao departdao =new DepartDao();
			RoleDao roledao = new RoleDao();
		HttpSession session =request.getSession();
		String x = (String)session.getAttribute("userId");
		
		try {
			people = userdao.getbyId(x);
			String departname = departdao.getNamebyID(people.getPeoSubdetach());
			people.setPeoSubdetachName(departname);
			String rolename = roledao.getbyid(people.getRole()).getRolename();
			people.setRoleName(rolename);
			request.setAttribute("people", people);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/Views/Personel/Personal.jsp").forward(request, response);
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
