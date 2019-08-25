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
	
	public int addAreas(ArrayList<AreaDTO> area) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int inserted = 0;
		try{
			con = DBUtil.getConnection();
			for(AreaDTO a : area) {
				
				pstmt = con.prepareStatement("insert into commercial.area values(?,?, ?, ?,?)");
				
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
}
