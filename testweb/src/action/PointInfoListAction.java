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
import model.Point;
import util.AppException;
import dao.PointDao;

/**
 * Servlet implementation class PointInfoListAction
 */
public class PointInfoListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointInfoListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String condition = request.getParameter("condition");
		List<Point> pointlist = new ArrayList<Point>();
		if(condition==null)
			condition = "";
		System.out.println("condition:"+condition);
		try{
			PointDao pointdao = new PointDao();
			pointlist = pointdao.pointList(condition);
			JSONArray jsonArray = JSONArray.fromObject(pointlist);
			System.out.println(jsonArray.toString());
			
			PrintWriter writer = response.getWriter();
			writer.println(jsonArray.toString());
			writer.flush();
			writer.close();
		}
		catch (AppException e) {
			e.printStackTrace();
	}
		
	}

}
