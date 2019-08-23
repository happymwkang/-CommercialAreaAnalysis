package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.FtDTO;
import util.DBUtil;

public class FtDAO {
	static FtDAO instance = new FtDAO();
	private FtDAO() {}
	
	public static FtDAO getInstance() {
		return instance;
	}
	
	public int addFts(ArrayList<FtDTO> fts) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try{
			con = DBUtil.getConnection();
			for(FtDTO s : fts) {
				pstmt = con.prepareStatement("insert into ft values(?,?,?,?,?,?,?,?)");
				
				//pstmt.setString(1, s.getDivision());
				pstmt.setString(1, s.getId());
				//pstmt.setString(3, s.getName());
				pstmt.setString(2, s.getYear());
				pstmt.setString(3, s.getQuater());
				pstmt.setString(4, s.getSex());
				pstmt.setString(5, s.getAge());
				pstmt.setString(6, s.getTime());
				pstmt.setString(7, s.getDay());
				pstmt.setString(8, s.getFt());
				
				
				num += pstmt.executeUpdate();
				
				pstmt.close();
			}
			
		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return num;
	}
}
