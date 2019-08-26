package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommertialService;

@WebServlet("/CommertialController")
public class CommertialController extends HttpServlet {
    public CommertialController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//command pattern
		String command = request.getParameter("command");
		try{
			if(command.equals("getAllChannel")){//모든 probono project 정보 검색
				System.out.println("test");
				addChannel(request, response);
			}else if(command.equals("testChannel")) {
				System.out.println("test111");
				testChannel(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}

	//상권-점포 API 데이터 검색
	public void addChannel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			System.out.println("실행되니");
			request.setAttribute("getAllChannel", CommertialService.getAllChannel());
			url = "getAllChannel.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//상권-점포 API test 데이터 검색
		public void testChannel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "showError.jsp";
			try {
				System.out.println("실행되니111");
				request.setAttribute("testChannel", CommertialService.testChannel());
				System.out.println(CommertialService.testChannel());
				url = "googleChartTest1.jsp";
			}catch(Exception s){
				request.setAttribute("errorMsg", s.getMessage());
			}
			request.getRequestDispatcher(url).forward(request, response);
		}

}
