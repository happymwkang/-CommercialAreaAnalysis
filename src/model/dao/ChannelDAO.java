package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ChannelDTO;
import util.DBUtil;

public class ChannelDAO {
	public static int APIaddDataChannel(ArrayList<ChannelDTO> channel) throws SQLException {
		int insertCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			
			for (int j = 0; j < channel.size(); j++) {
				ChannelDTO channelInfo = channel.get(j);
				pstmt = con.prepareStatement("insert into channel values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, channelInfo.getYear());
				pstmt.setString(2, channelInfo.getQuarter());
				pstmt.setString(3, channelInfo.getLevel1());
				pstmt.setString(4, channelInfo.getLevel1Name());
				pstmt.setString(5, channelInfo.getLevel2());
				pstmt.setString(6, channelInfo.getLevel2Name());
				pstmt.setString(7, channelInfo.getBusiness());
				pstmt.setDouble(8, channelInfo.getShopCnt());
				pstmt.setDouble(9, channelInfo.getSimilrCnt());
				pstmt.setDouble(10, channelInfo.getOpenCnt());
				pstmt.setDouble(11, channelInfo.getCloseCnt());
				pstmt.setDouble(12, channelInfo.getFrcShopCnt());
				
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
