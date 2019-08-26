package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONObject;

import model.dto.PopDTO;
import model.dto.PopCompDTO;
import util.DBUtil;

public class PopDAO {
	

	static PopDAO instance = new PopDAO();
	private PopDAO() {}
	public static PopDAO getInstance() {
		return instance;
	}
	
	public int addPops(ArrayList<PopDTO> pops) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try{
			con = DBUtil.getConnection();
			for(PopDTO s : pops) {
//				pstmt = con.prepareStatement("insert into commercial.pop values(?,?,?,?,?,?)");
				pstmt = con.prepareStatement("insert into pop values(?,?,?,?,?,?)");
				
				pstmt.setString(1, s.getId());
				pstmt.setString(2, s.getYear());
				pstmt.setString(3, s.getQuater());
				pstmt.setString(4, s.getSex());
				pstmt.setString(5, s.getAge());
				pstmt.setString(6, s.getPop());
				
				
				num += pstmt.executeUpdate();
				
				pstmt.close();
			}
			
		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return num;
	}
	
	public static ArrayList<PopCompDTO> getPopSet(String areaId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] yearSet = new String[] {"2018"};
		String[] quaterSet = new String[] {"1","2","3","4"};
		
		//10,20,30,40,50,60 대 별 상주인구
		ArrayList<PopCompDTO> populationsList = new ArrayList<>();
		String areaName = "";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select area_name from area where area_code = ?");
			pstmt.setString(1, areaId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				areaName=rset.getString(1);
			}
			//else notexist 던져야해요
		} finally {
			pstmt.close();
		}
		
		for(String year : yearSet) {
			for(String quater : quaterSet) {
				PopCompDTO populations = new PopCompDTO(year, quater, areaName, 0,0,0,0,0,0);
				try{
					pstmt = con.prepareStatement("select age,pop from pop where year=? and quater = ? and area_id = ?");
					pstmt.setString(1, year);
					pstmt.setString(2, quater);
					pstmt.setString(3, areaId);
					rset = pstmt.executeQuery();
					while(rset.next()) {
						if(rset.getString(1).equals("10")) { 
							populations.setA10(populations.getA10()+(int)Double.parseDouble(rset.getString(2)));
						}else if(rset.getString(1).equals("20")) {
							populations.setA20(populations.getA20()+(int)Double.parseDouble(rset.getString(2)));
						}else if(rset.getString(1).equals("30")) {
							populations.setA30(populations.getA30()+(int)Double.parseDouble(rset.getString(2)));
						}else if(rset.getString(1).equals("40")) {
							populations.setA40(populations.getA40()+(int)Double.parseDouble(rset.getString(2)));
						}else if(rset.getString(1).equals("50")) {
							populations.setA50(populations.getA50()+(int)Double.parseDouble(rset.getString(2)));
						}else if(rset.getString(1).equals("60")) {
							populations.setA60(populations.getA60()+(int)Double.parseDouble(rset.getString(2)));
						}
					}
					populationsList.add(populations);
	
				}finally {
					pstmt.close();
				}

			}	
		}
			
			
		
			//json object value값 다 더해서 0이면 notexistexception 날리는 예외처리 해야함
			
		DBUtil.close(con, pstmt);
		
		return populationsList;
	}
//	public static void main(String[] args) {
//		try {
//			getPop("2018","1","1000001");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
