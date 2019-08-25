package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	
	public boolean addArea(AreaDTO area) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into area values(?, ?, ?, ?)");
			pstmt.setString(1, area.getDivision());
			pstmt.setInt(2, area.getId());
			pstmt.setString(3, area.getName());
			pstmt.setString(4, area.getSigungu());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public int addAreas(ArrayList<AreaDTO> area) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int inserted = 0;
		try{
			con = DBUtil.getConnection();
			for(AreaDTO a : area) {
				
				pstmt = con.prepareStatement("insert into commercial.area values(?, ?, ?,?)");
				
				pstmt.setString(1, a.getDivision());
				pstmt.setInt(2, a.getId());
				pstmt.setString(3, a.getName());
				pstmt.setString(4, a.getSigungu());
				inserted += pstmt.executeUpdate();
				
				pstmt.close();
			}
			
		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return inserted;
	}
}
