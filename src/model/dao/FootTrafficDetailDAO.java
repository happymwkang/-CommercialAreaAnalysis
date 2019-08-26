package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AreaChangeDTO;
import model.dto.FootTrafficDetailDTO;
import util.DBUtil;

public class FootTrafficDetailDAO {
	public static int APIaddDataFootTrafficDt(ArrayList<FootTrafficDetailDTO> footTraffic) throws SQLException {
		int insertCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			
			for (int j = 0; j < footTraffic.size(); j++) {
				FootTrafficDetailDTO footTrafficInfo = footTraffic.get(j);
				pstmt = con.prepareStatement("insert into foot_traffic_detail values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, footTrafficInfo.getYear());
				pstmt.setString(2, footTrafficInfo.getQuarter());
				pstmt.setString(3, footTrafficInfo.getDivisionCd());
				pstmt.setString(4, footTrafficInfo.getDivisionNm());
				pstmt.setString(5, footTrafficInfo.getAreaCd());
				pstmt.setString(6, footTrafficInfo.getAreaNm());
				pstmt.setString(7, footTrafficInfo.getSex());
				pstmt.setString(8, footTrafficInfo.getAge());
				pstmt.setString(9, footTrafficInfo.getDay());
				pstmt.setString(10, footTrafficInfo.getTimes());
				pstmt.setDouble(11, footTrafficInfo.getFt());
				
				int result = pstmt.executeUpdate();
				pstmt.close();
				
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
