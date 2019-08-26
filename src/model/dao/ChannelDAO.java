package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ChannelDTO;
import model.dto.TestDTO;
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
				pstmt = con.prepareStatement("insert into channel values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, channelInfo.getYear());
				pstmt.setString(2, channelInfo.getQuarter());
				pstmt.setString(3, channelInfo.getDivisionCd());
				pstmt.setString(4, channelInfo.getDivisionNm());
				pstmt.setString(5, channelInfo.getAreaCd());
				pstmt.setString(6, channelInfo.getAreaNm());
				pstmt.setString(7, channelInfo.getBusinessCd());
				pstmt.setString(8, channelInfo.getBusinessNm());
				pstmt.setDouble(9, channelInfo.getShopCnt());
				pstmt.setDouble(10, channelInfo.getSimilrCnt());
				pstmt.setDouble(11, channelInfo.getOpenCnt());
				pstmt.setDouble(12, channelInfo.getCloseCnt());
				pstmt.setDouble(13, channelInfo.getFrcShopCnt());
				
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
	
	public static ArrayList<ChannelDTO> getAllChannel() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ChannelDTO> list = new ArrayList<ChannelDTO>();
		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select * from channel");
				rset = pstmt.executeQuery();
				
				while(rset.next()){
					list.add( new ChannelDTO(rset.getString(1), rset.getString(2), rset.getString(3), 
											rset.getString(4), rset.getString(5), rset.getString(6),rset.getString(7),
											rset.getString(8), rset.getDouble(9), rset.getDouble(10),rset.getDouble(11),
											rset.getDouble(12), rset.getDouble(13)));
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	public static ArrayList<TestDTO> testChannel() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<TestDTO> list = new ArrayList<TestDTO>();
		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select year,business_name, sum(shopcnt) shopcnt from channel group by year, business_name order by business_name");
				rset = pstmt.executeQuery();
				
				while(rset.next()){
					list.add( new TestDTO(rset.getString(1), rset.getString(2), rset.getDouble(3)));
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
