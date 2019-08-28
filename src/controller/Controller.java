package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			if(command.equals("getSelectArea")){
				getSelectArea(request, response);
			}else if(command.equals("insertAreas")){
				System.out.println("시작");
				readAreasFromAPI();
				System.out.println("끝");
			}else if(command.equals("getAll")){
				getAll(request,response);
			}else if(command.equals("getSalesAmount")){
				getSalesAmount(request,response);
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
	
	public static void getSelectArea(HttpServletRequest request, HttpServletResponse response) {
		String url = "showError.jsp";
		try {
			String sigungu = request.getParameter("guNm");
			String div = request.getParameter("divNm");
			System.out.println("aaaaaaaa");
			System.out.println(sigungu);
			System.out.println(div);
			String results = null;
			if(div==null) {
				results = service.getSelectDiv(sigungu);
			}else {
				results = service.getSelectArea(sigungu,div);
			}
			System.out.println(results);
			request.setAttribute("sigungu", results);
			url = "dataView.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void getAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			String areaNm = request.getParameter("customSelect3");
			System.out.println(areaNm);
			
			String result = service.getPopSet(areaNm);
			request.setAttribute("popComp", result);
			System.out.println(result);
			//////////////////////
			String result2 = service.getSalesAmount(areaNm);
			System.out.println(result2);
			request.setAttribute("salesAmounts", result2);

			
			
			request.getRequestDispatcher("index2.jsp").forward(request, response);
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
	
	public static void getSalesAmount(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			String result = service.getSalesAmount("1000001");
			System.out.println(result);
			request.setAttribute("salesAmounts", result);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		getPopulationComposition();
//	}

}
