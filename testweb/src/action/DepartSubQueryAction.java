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

import model.Department;
import util.AppException;
import dao.DepartDao;

public class DepartSubQueryAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DepartSubQueryAction() {
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
		List<Department> list = new ArrayList<Department>();
		DepartDao departdao=new DepartDao();	
		String id = request.getParameter("DepId");
		
		
		try {
			list=departdao.getChild2(id);
			for(int i =0;i<list.size();i++){
				 departdao.getParentName(list.get(i));
			}
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
			//System.out.println("listObject:"+listObject.toString());
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
