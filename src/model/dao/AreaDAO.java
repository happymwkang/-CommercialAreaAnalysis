package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AreaDTO;
import util.DBUtil;

public class AreaDAO {
	static AreaDAO instance = new AreaDAO();
	private AreaDAO() {}
	
	public static AreaDAO getInstance() {
		return instance;
	}
	
	public int addAreas(ArrayList<AreaDTO> area) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int inserted = 0;
		try{
			con = DBUtil.getConnection();
			for(AreaDTO a : area) {
				
//				pstmt = con.prepareStatement("insert into commercial.area values(?,?, ?, ?,?)");
				pstmt = con.prepareStatement("insert into area values(?,?, ?, ?,?)");
				
				pstmt.setString(1, a.getDivisionId());
				pstmt.setString(2, a.getDivision());
				pstmt.setInt(3, a.getId());
				pstmt.setString(4, a.getName());
				pstmt.setString(5, a.getSigungu());
				inserted += pstmt.executeUpdate();
				
				pstmt.close();
			}
			
		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return inserted;
	}
	
	public ArrayList<String> getSelectDiv(String sigungu) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> divSet = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select distinct division_name from area where sigungu = ?");
			pstmt.setString(1, sigungu);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				divSet.add(rset.getString(1));
			}
			//else notexist 던져야해요
		} finally {
			pstmt.close();
		}
		
		return divSet;
	}
	
	public ArrayList<ArrayList<String>> getSelectArea(String sigungu, String div) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ArrayList<String>> divSet = new ArrayList<>();
		ArrayList<String> codeSet = new ArrayList<>();
		ArrayList<String> nameSet = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select distinct area_code, area_name from area where sigungu = ? and division_name = ?");
			pstmt.setString(1, sigungu);
			pstmt.setString(2, div);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				codeSet.add(rset.getString(1));
				nameSet.add(rset.getString(2));
			}
			//else notexist 던져야해요
		} finally {
			pstmt.close();
		}
		divSet.add(codeSet);
		divSet.add(nameSet);
		return divSet;
	}
}
