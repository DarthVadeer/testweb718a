package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AppException;
import dao.PointDao;

/**
 * Servlet implementation class PointDeleteAction
 */
public class PointDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String PointId = request.getParameter("PointId");
		System.out.println("PointId"+PointId);
		boolean flag = false;
		try{
			PointDao pointdao = new PointDao();
			flag=	pointdao.removePointbyid(PointId);
			
		}
		catch (AppException e) {
			e.printStackTrace();
	}
		PrintWriter writer = response.getWriter();
		if(flag == true)
		
		writer.println("1");
		else 
			writer.println("2");
		
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
