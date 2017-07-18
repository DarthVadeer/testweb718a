package Filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		  // ��������������Ҫ�õ�request,response,session����
		  HttpServletRequest servletRequest = (HttpServletRequest) request;
		  HttpServletResponse servletResponse = (HttpServletResponse) response;
		  HttpSession session = servletRequest.getSession();
		  // ����û������URI
		  String path = servletRequest.getRequestURI();
		  //System.out.println(path);
		  // ��session��ȡԱ��������Ϣ
		  String userid = (String) session.getAttribute("userId");
		  /*������Constants.java������д����������˵�ҳ��
		  for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {
		   if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		    chain.doFilter(servletRequest, servletResponse);
		    return;
		   }
		  }*/
		  // ��½ҳ���������
		  if(path.endsWith("/Login.jsp") || path.endsWith("/LoginAction")) {
		   chain.doFilter(servletRequest, servletResponse);
		   return;
		  }
		  // �ж����û��ȡ��Ա����Ϣ,����ת����½ҳ��
		  if (userid == null || "".equals(userid)) {
		   // ��ת����½ҳ��
		   servletResponse.sendRedirect("http://localhost:8080/testweb/Views/Frame/Login.jsp");
		  } else {
		   // �Ѿ���½,�����˴�����
		   chain.doFilter(request, response);
		  }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
