package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.PopDTO;
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
}
