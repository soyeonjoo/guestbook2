package kr.ac.sungkyul.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.guestbook.dao.GuestBookDao;
import kr.ac.sungkyul.guestbook.vo.GuestBookVo;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {//mvc 반영 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		
		if("list".equals(actionName)){

			GuestBookDao vo = new GuestBookDao();
			List<GuestBookVo> list = vo.getList();
			
			request.setAttribute("list", list);//이름 지정과 객체 
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp"); //어디로 보낼꺼냐
			rd.forward(request, response);
			
		}else if("insert".equals(actionName)){

		    String name = request.getParameter("name");
		    String password = request.getParameter("pass");
		    String content = request.getParameter("content");

		    GuestBookDao dao = new GuestBookDao();
		    GuestBookVo vo  = new GuestBookVo();

		   	vo.setName(name);
			vo.setPassword(password);
		   	vo.setContent(content);


		    dao.insert(vo);
		    
		    
		    response.sendRedirect("/guestbook2/gb");
			
			
		}else if("deleteform".equals(actionName)){ //delete와 deleteform 두개 만들어줘야함 
		
		    String no = request.getParameter("no");
		    Long number1 = Long.parseLong(no);
		    
		    request.setAttribute("no", number1);
			
		    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp"); //어디로 보낼꺼냐
			rd.forward(request, response);
							
		}
		
		else if("delete".equals(actionName)){
		

			 String number = request.getParameter("no");
		    String password = request.getParameter("password");
			
		    
		    Long rno = Long.parseLong(number);
		 
		    
		    GuestBookDao dao = new GuestBookDao();
		    GuestBookVo vo  = new GuestBookVo();

		    vo.setNo(rno);
		    vo.setPassword(password);
		   
		    dao.delete(vo);
		
   
		    
		    response.sendRedirect("/guestbook2/gb");
		    
		}else{
			GuestBookDao vo = new GuestBookDao();
			List<GuestBookVo> list = vo.getList();
			
			request.setAttribute("list", list);//이름 지정과 객체 
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp"); //어디로 보낼꺼냐
			rd.forward(request, response);
			
		}
		
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	
	}

}
