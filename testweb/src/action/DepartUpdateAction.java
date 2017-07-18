package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AppException;
import dao.DepartDao;
import model.Department;

public class DepartUpdateAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DepartUpdateAction() {
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
		boolean flag=false;
		Department depart =new Department();
		DepartDao departdao=new DepartDao();
		
		String id=request.getParameter("DepId");
		String type=request.getParameter("DepType");
		String name=request.getParameter("DepName");
		String slevel=request.getParameter("DepLevel");
		String parent=request.getParameter("ParentDep");
		String principal=request.getParameter("DepPrincipal");
		String phone=request.getParameter("DepPhone");
		String remarks=request.getParameter("DepDesc");
		String code=request.getParameter("Depcode");
		String shortname=request.getParameter("DepAbb");
		String officer=request.getParameter("DepOfficer");
		
		type = java.net.URLDecoder.decode(type,"UTF-8");
		name = java.net.URLDecoder.decode(name,"UTF-8");
		slevel = java.net.URLDecoder.decode(slevel,"UTF-8");
		parent = java.net.URLDecoder.decode(parent,"UTF-8");
		principal = java.net.URLDecoder.decode(principal,"UTF-8");
		phone = java.net.URLDecoder.decode(phone,"UTF-8");
		remarks = java.net.URLDecoder.decode(remarks,"UTF-8");
		code = java.net.URLDecoder.decode(code,"UTF-8");
		shortname = java.net.URLDecoder.decode(shortname,"UTF-8");
		officer = java.net.URLDecoder.decode(officer,"UTF-8");
		id = java.net.URLDecoder.decode(id,"UTF-8");
		
		int level=0;
		if(!slevel.equals(null)){
			level=Integer.parseInt(slevel);
		}
		
		depart.setDepartID(id);
		depart.setDepartType(type);
		depart.setDepartName(name);
		depart.setDepartLevel(level);
		depart.setDepartParent(parent);
		depart.setDepartPrincipal(principal);
		depart.setDepartPhone(phone);
		depart.setDepartRemarks(remarks);
		depart.setDepartCode(code);
		depart.setDepartSName(shortname);
		depart.setDepartOfficer(officer);
		
		try {
			flag = departdao.update(depart);
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
		doGet(request,response);
	}

}
