package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.PopCompDTO;
import service.Service;

@WebServlet("/commercial.do")
public class Controller extends HttpServlet {
	private static Service service = Service.getInstance();
	
	public Controller() {
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
		System.out.println(command);
		try{
			if(command.equals("probonoProjectAll")){
			}else if(command.equals("insertAreas")){
				System.out.println("시작");
				readAreasFromAPI();
				System.out.println("끝");
			}else if(command.equals("getPopulationComposition")){
				getPopulationComposition(request,response);
			}else if(command.equals("recipient")){
			}else if(command.equals("activistInsert")){
			}else if(command.equals("activistUpdateReq")){
			}else if(command.equals("activistUpdate")){
			}else if(command.equals("activistDelete")){
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}
	
	public static void getPopulationComposition(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1");
		try {
			PopCompDTO result = service.getPop("2018","1","1000001");
			request.setAttribute("popComp", result);
			request.getRequestDispatcher("popCompChart.jsp").forward(request, response);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readAreasFromAPI() {
		System.out.println("1");
		try {
			int result = service.readAreasFromAPI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		getPopulationComposition();
//	}

}
