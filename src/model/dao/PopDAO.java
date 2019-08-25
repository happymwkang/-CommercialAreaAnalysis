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
				pstmt = con.prepareStatement("insert into commercial.pop values(?,?,?,?,?,?)");
				
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
	
	public static PopCompDTO getPop(String year, String quater, String areaId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//10,20,30,40,50,60 대 별 상주인구
		PopCompDTO populations = new PopCompDTO(0,0,0,0,0,0);
//		ArrayList<String> populations = new ArrayList<>();
		
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement("select age,pop from commercial.pop where year=? and quater = ? and area_id = ?");
//			pstmt = con.prepareStatement("select pop from commercial.pop where year='2018' and quater = '1' and area_id = '1000001' and sex = 'FAG' and age='10'");
			pstmt.setString(1, year);
			pstmt.setString(2, quater);
			pstmt.setString(3, areaId);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
//				System.out.println(+rset.getString(1));
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
			pstmt.close();
			System.out.println(populations);
		
			//json object value값 다 더해서 0이면 notexistexception 날리는 예외처리 해야함
			
		}finally{
			DBUtil.close(con, pstmt);
		}
		System.out.println(populations.toString());
		return populations;
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
