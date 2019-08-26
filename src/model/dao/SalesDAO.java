package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.PopCompDTO;
import model.dto.SalesAmountDTO;
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
	
//	ArrayList<SalesAmountDTO>
	public static ArrayList<ArrayList<SalesAmountDTO>> getSalesAmount(String areaId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] yearSet = new String[] {"2018"};
		String[] quaterSet = new String[] {"1","2","3","4"};
		
		ArrayList<ArrayList<SalesAmountDTO>> salesSet = new ArrayList<>();
		
		String areaName = "";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select area_name from area where area_code = ?");
			pstmt.setString(1, areaId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				areaName=rset.getString(1);
			}
			//else notexist 던져야해요
		} finally {
			pstmt.close();
		}
		
		for(String year : yearSet) {
			for(String quater : quaterSet) {
				ArrayList<SalesAmountDTO> salesQuater = new ArrayList<>();
				try{
					pstmt = con.prepareStatement("select * from sales where year=? and quater = ? and area_id = ?");
					pstmt.setString(1, year);
					pstmt.setString(2, quater);
					pstmt.setString(3, areaId);
					rset = pstmt.executeQuery();
					while(rset.next()) {
						SalesAmountDTO sales = new SalesAmountDTO(areaName,year, quater, null,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
						sales.setBusiness(rset.getString(4));
						sales.setMonthSales(rset.getInt(5));
						sales.setWkSales(rset.getInt(6));
						sales.setWkendSales(rset.getInt(7));
						sales.setMonSales(rset.getInt(8));
						sales.setTueSales(rset.getInt(9));
						sales.setWedSales(rset.getInt(10));
						sales.setThuSales(rset.getInt(11));
						sales.setFriSales(rset.getInt(12));
						sales.setSatSales(rset.getInt(13));
						sales.setSunSales(rset.getInt(14));
						sales.setT0006Sales(rset.getInt(15));
						sales.setT0611Sales(rset.getInt(16));
						sales.setT1114Sales(rset.getInt(17));
						sales.setT1417Sales(rset.getInt(18));
						sales.setT1721Sales(rset.getInt(19));
						sales.setT2124Sales(rset.getInt(20));
						sales.setMSales(rset.getInt(21));
						sales.setWSales(rset.getInt(22));
						sales.setA10Sales(rset.getInt(23));
						sales.setA20Sales(rset.getInt(24));
						sales.setA30Sales(rset.getInt(25));
						sales.setA40Sales(rset.getInt(26));
						sales.setA50Sales(rset.getInt(27));
						sales.setA60Sales(rset.getInt(28));
						
						salesQuater.add(sales);
//						System.out.println(sales);
					}
				}finally {
					pstmt.close();
				}
				salesSet.add(salesQuater);

			}
		}
		
			//json object value값 다 더해서 0이면 notexistexception 날리는 예외처리 해야함
			
		DBUtil.close(con, pstmt);
		
		return salesSet;
	}
	
//	public static void main(String[] args) {
//		try {
//			getSalesAmount("1000001");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
