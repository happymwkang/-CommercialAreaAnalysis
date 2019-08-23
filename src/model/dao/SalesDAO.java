package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.SalesDTO;
import util.DBUtil;

public class SalesDAO {
	static SalesDAO instance = new SalesDAO();
	private SalesDAO() {}
	
	public static SalesDAO getInstance() {
		return instance;
	}
	
	public int addSales(ArrayList<SalesDTO> sales) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		try{
			con = DBUtil.getConnection();
			for(SalesDTO s : sales) {
				pstmt = con.prepareStatement("insert into sales values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				//pstmt.setString(1, s.getDivision());
				pstmt.setString(1, s.getId());
				//pstmt.setString(3, s.getName());
				pstmt.setString(2, s.getYear());
				pstmt.setString(3, s.getQuater());
				pstmt.setString(4, s.getBusiness());
				pstmt.setString(5, s.getMonthSales());
				pstmt.setString(6, s.getWkSales());
				pstmt.setString(7, s.getWkendSales());
				pstmt.setString(8,s.getMonSales());
				pstmt.setString(9,s.getTueSales());
				pstmt.setString(10,s.getWedSales());
				pstmt.setString(11,s.getThuSales());
				pstmt.setString(12,s.getFriSales());
				pstmt.setString(13,s.getSatSales());
				pstmt.setString(14,s.getSunSales());
				pstmt.setString(15,s.getT0006Sales());
				pstmt.setString(16,s.getT0611Sales());
				pstmt.setString(17,s.getT1114Sales());
				pstmt.setString(18,s.getT1417Sales());
				pstmt.setString(19,s.getT1721Sales());
				pstmt.setString(20,s.getT2124Sales());
				pstmt.setString(21,s.getMSales());
				pstmt.setString(22,s.getWSales());
				pstmt.setString(23,s.getA10Sales());
				pstmt.setString(24,s.getA20Sales());
				pstmt.setString(25,s.getA30Sales());
				pstmt.setString(26,s.getA40Sales());
				pstmt.setString(27,s.getA50Sales());
				pstmt.setString(28,s.getA60Sales());
				pstmt.setString(29,s.getMonthNum());
				//
				pstmt.setString(30, s.getWkNum());
				pstmt.setString(31, s.getWkendNum());
				pstmt.setString(32,s.getMonNum());
				pstmt.setString(33,s.getTueNum());
				pstmt.setString(34,s.getWedNum());
				pstmt.setString(35,s.getThuNum());
				pstmt.setString(36,s.getFriNum());
				pstmt.setString(37,s.getSatNum());
				pstmt.setString(38,s.getSunNum());
				pstmt.setString(39,s.getT0006Num());
				pstmt.setString(40,s.getT0611Num());
				pstmt.setString(41,s.getT1114Num());
				pstmt.setString(42,s.getT1417Num());
				pstmt.setString(43,s.getT1721Num());
				pstmt.setString(44,s.getT2124Num());
				pstmt.setString(45,s.getMNum());
				pstmt.setString(46,s.getWNum());
				pstmt.setString(47,s.getA10Num());
				pstmt.setString(48,s.getA20Num());
				pstmt.setString(49,s.getA30Num());
				pstmt.setString(50,s.getA40Num());
				pstmt.setString(51,s.getA50Num());
				pstmt.setString(52,s.getA60Num());
				pstmt.setString(53,s.getStoreNum());
				
				num += pstmt.executeUpdate();
				
				pstmt.close();
			}
			
		
		}finally{
			DBUtil.close(con, pstmt);
		}
		return num;
	}
	
}
