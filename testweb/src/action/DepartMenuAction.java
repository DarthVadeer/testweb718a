package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.DepartForMenu;
import net.sf.json.JSONArray;
import util.AppException;
import dao.DepartDao;

public class DepartMenuAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DepartMenuAction() {
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
		
		List<DepartForMenu> list = new ArrayList<DepartForMenu>();
		DepartDao departdao=new DepartDao();	
		HttpSession session =request.getSession();
		String x = (String)session.getAttribute("userId");
		
		//String id = request.getParameter("departid");
		
		try {
			list=departdao.getForMenu(x);
			request.setAttribute("Deplist",list);
			//System.out.println("listObject:"+listObject.toString());
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		request.getRequestDispatcher("/Views/Personel/Personnel.jsp").forward(request,response);
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
