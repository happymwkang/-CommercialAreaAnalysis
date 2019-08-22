package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AreaChangeDTO;
import util.DBUtil;

public class AreaChangeDAO {
	public static int APIaddDataAreaChanIx(ArrayList<AreaChangeDTO> areaChanIx) throws SQLException {
		int insertCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			
			for (int j = 0; j < areaChanIx.size(); j++) {
				AreaChangeDTO areaChanInfo = areaChanIx.get(j);
				pstmt = con.prepareStatement("insert into AreaChangeIndex values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, areaChanInfo.getYear());
				pstmt.setString(2, areaChanInfo.getQuarter());
				pstmt.setString(3, areaChanInfo.getDivision());
				pstmt.setString(4, areaChanInfo.getLevel2Name());
				pstmt.setString(5, areaChanInfo.getAreaChangeNm());
				pstmt.setString(6, areaChanInfo.getOpenAvg());
				pstmt.setString(7, areaChanInfo.getCloseAvg());
				pstmt.setString(8, areaChanInfo.getSeoulOpenAvg());
				pstmt.setString(9, areaChanInfo.getSeoulCloseAvg());
				
				int result = pstmt.executeUpdate();
				DBUtil.close(pstmt);
				
				if (result != 1) {
					return result;
				}
				insertCnt++;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return insertCnt;
	}
}
