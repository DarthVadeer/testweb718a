package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Point;
import net.sf.json.JSONArray;
import util.AppException;
import dao.PointDao;

/**
 * Servlet implementation class PointInfoByIdAction
 */
public class PointInfoByIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointInfoByIdAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String condition = request.getParameter("PointId");
		Point point = new Point();
		List<Point> pointlist = new ArrayList<Point>();
		try{
			PointDao pointdao = new PointDao();
			point = pointdao.getbyid(condition);
			pointlist.add(point);
			
		}
		catch (AppException e) {
			e.printStackTrace();
	}
		JSONArray jsonArray = JSONArray.fromObject(pointlist);
		System.out.println(jsonArray.toString());
		
		PrintWriter writer = response.getWriter();
		writer.println(jsonArray.toString());
		writer.flush();
		writer.close();
		
	}
}


