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
	
	public boolean addAreas(ArrayList<SalesDTO> sales) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			int num = 0;
			for(SalesDTO s : sales) {
				
				pstmt = con.prepareStatement("insert Stringo area values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pstmt.setString(1, s.getDivision());
				pstmt.setString(2, s.getId());
				pstmt.setString(3, s.getName());
				pstmt.setString(4, s.getYear());
				pstmt.setString(5, s.getQuater());
				pstmt.setString(6, s.getBusiness());
				pstmt.setString(7, s.getMonthSales());
				pstmt.setString(8, s.getWkSales());
				pstmt.setString(9, s.getWkendSales());
				pstmt.setString(10,s.getMonSales());
				pstmt.setString(11,s.getTueSales());
				pstmt.setString(12,s.getWedSales());
				pstmt.setString(13,s.getThuSales());
				pstmt.setString(14,s.getFriSales());
				pstmt.setString(15,s.getSatSales());
				pstmt.setString(16,s.getSunSales());
				pstmt.setString(17,s.getT0006Sales());
				pstmt.setString(18,s.getT0611Sales());
				pstmt.setString(19,s.getT1114Sales());
				pstmt.setString(20,s.getT1417Sales());
				pstmt.setString(21,s.getT1721Sales());
				pstmt.setString(22,s.getT2124Sales());
				pstmt.setString(23,s.getMSales());
				pstmt.setString(24,s.getWSales());
				pstmt.setString(25,s.getA10Sales());
				pstmt.setString(26,s.getA20Sales());
				pstmt.setString(27,s.getA30Sales());
				pstmt.setString(28,s.getA40Sales());
				pstmt.setString(29,s.getA50Sales());
				pstmt.setString(30,s.getA60Sales());
				pstmt.setString(31,s.getMonthNum());
				//
				pstmt.setString(32, s.getWkNum());
				pstmt.setString(33, s.getWkendNum());
				pstmt.setString(34,s.getMonNum());
				pstmt.setString(35,s.getTueNum());
				pstmt.setString(36,s.getWedNum());
				pstmt.setString(37,s.getThuNum());
				pstmt.setString(38,s.getFriNum());
				pstmt.setString(39,s.getSatNum());
				pstmt.setString(40,s.getSunNum());
				pstmt.setString(41,s.getT0006Num());
				pstmt.setString(42,s.getT0611Num());
				pstmt.setString(43,s.getT1114Num());
				pstmt.setString(44,s.getT1417Num());
				pstmt.setString(45,s.getT1721Num());
				pstmt.setString(46,s.getT2124Num());
				pstmt.setString(47,s.getMNum());
				pstmt.setString(48,s.getWNum());
				pstmt.setString(49,s.getA10Num());
				pstmt.setString(50,s.getA20Num());
				pstmt.setString(51,s.getA30Num());
				pstmt.setString(52,s.getA40Num());
				pstmt.setString(53,s.getA50Num());
				pstmt.setString(54,s.getA60Num());
				pstmt.setString(55,s.getStoreNum());
				
				num += pstmt.executeUpdate();
				
				pstmt.close();
			}
			
		
			if(num != 0){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
}
