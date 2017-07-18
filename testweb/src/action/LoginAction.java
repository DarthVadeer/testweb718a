package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.People;
import model.Role;
import model.StatusCode;
import dao.RoleDao;
import dao.UserDao;
import util.AppException;

/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			
			UserDao userdao = new UserDao();
			RoleDao roledao =new RoleDao();
			People people = null;
		
			people  = userdao.login(name, password);
			Role role = null;
			if (people.getRole() != null) { 
				HttpSession session = null;
				session = request.getSession();
				session.setAttribute("userName", people.getName());
				session.setAttribute("userId", people.getPeopid());
				
				role = roledao.getbyid(people.getRole());
				String funcIds = role.getRolemenuids();
				for (String id : funcIds.split(","))
					session.setAttribute(id, "1");
				
				request.getRequestDispatcher("/Views/Frame/Frame.jsp").forward(request,response);	
			}else {// Login failed
				// Set prompt message
				int result  = StatusCode.ERROR_NOUSERNAME;
				request.setAttribute("result", result); // Save prompt message into request
				// Save user name into request
				request.setAttribute("userName", name);	
				// Forward to login page
				request.getRequestDispatcher("/Views/Frame/Login.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// Redirect to exception page
			response.sendRedirect("toError");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
