package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Department;
import model.People;
import util.AppException;
import dao.DepartDao;
import dao.UserDao;

public class DepartQueryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DepartQueryAction() {
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
		List<Department> list = new ArrayList<Department>();
		DepartDao departdao=new DepartDao();	
		HttpSession session = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		System.out.println("uid:"+userId);
		UserDao userdao = new UserDao();
		
		try {
			People people = new People();
			people = userdao.getbyId(userId);
			list=departdao.search(people.getPeoSubdetach());
			request.setAttribute("Deplist",list);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		request.getRequestDispatcher("/Views/Department/Departments.jsp").forward(request,response);
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
