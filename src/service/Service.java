package service;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.dao.AreaDAO;
import model.dao.SalesDAO;
import model.dto.AreaDTO;
import model.dto.SalesDTO;

public class Service {
	private static AreaDAO areaDAO = AreaDAO.getInstance();
	private static SalesDAO salesDAO = SalesDAO.getInstance();
	
	private static Service instance = new Service();
	private Service() {}
	
	public static Service getInstance() {
		return instance;
	}
	public boolean addArea(AreaDTO area) throws SQLException {
		return areaDAO.addArea(area);
	}
	public boolean addAreas(ArrayList<AreaDTO> area) throws SQLException {
		return areaDAO.addAreas(area);
	}
	
	public ArrayList<AreaDTO> readAreaFromFile() throws SQLException{
		JSONParser parser = new JSONParser();
		ArrayList<AreaDTO> area = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\Playdata\\Downloads\\commercialArea.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray arr = (JSONArray) jsonObject.get("DATA");
			for (int i = 0 ; i<arr.size(); i++) {
				JSONObject j = (JSONObject) arr.get(i);
//				System.out.println(j);
				AreaDTO nArea = new AreaDTO((String)j.get("trdar_se_cd_nm"),Integer.parseInt((String)j.get("trdar_cd")), (String)j.get("trdar_cd_nm"));
				//addArea(nArea);
				area.add(nArea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return area;
	}
	
	
	
	//Sales
	public static ArrayList<SalesDTO> readSalesFromFile() throws SQLException{
		JSONParser parser = new JSONParser();
		ArrayList<SalesDTO> sales = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\Playdata\\Downloads\\maechul2018.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray arr = (JSONArray) jsonObject.get("DATA");
			System.out.println(arr.size());
			for (int i = 0 ; i<10; i++) {
				JSONObject j = (JSONObject) arr.get(i);
//				System.out.println(j);
//				System.out.println(j.get("tues_selng_amt"));
				System.out.println((String)j.get("tmzon_06_11_selng_amt"));
//				SalesDTO nSales = new SalesDTO((String)j.get("trdar_se_cd_nm"),(String)j.get("trdar_cd"), (String)j.get("trdar_cd_nm"),(String)j.get("stdr_yy_cd"),(String)j.get("stdr_qu_cd"),
//						(String)j.get("svc_induty_cd_nm"),(String)j.get("thsmon_selng_amt"),(String)j.get("mdwk_selng_amt"),(String)j.get("wkend_selng_amt"),(String)j.get("mon_selng_amt"),
//						(String)j.get("tues_selng_amt"),(String)j.get("wed_selng_amt"),(String)j.get("thur_selng_amt"),(String)j.get("fri_selng_amt"),(String)j.get("sat_selng_amt"),
//						(String)j.get("sun_selng_amt"),(String)j.get("tmzon_00_06_selng_amt"),(String)j.get("tmzon_06_11_selng_amt"),(String)j.get("TMZON_11_14_SELNG_AMT"),(String)j.get("TMZON_14_17_SELNG_AMT"),
//						(String)j.get("TMZON_17_21_SELNG_AMT"),(String)j.get("TMZON_21_24_SELNG_AMT"),(String)j.get("ML_SELNG_AMT"),(String)j.get("FML_SELNG_AMT"),(String)j.get("AGRDE_10_SELNG_AMT"),
//						(String)j.get("AGRDE_20_SELNG_AMT"),(String)j.get("AGRDE_30_SELNG_AMT"),(String)j.get("AGRDE_40_SELNG_AMT"),(String)j.get("AGRDE_50_SELNG_AMT"),(String)j.get("AGRDE_60_SELNG_AMT"),
//						(String)j.get("THSMON_SELNG_CO"),(String)j.get("MDWK_SELNG_CO"),(String)j.get("WKEND_SELNG_CO"),
//						(String)j.get("MON_SELNG_CO"),(String)j.get("TUES_SELNG_CO"),(String)j.get("WED_SELNG_CO"),(String)j.get("THUR_SELNG_CO"),(String)j.get("FRI_SELNG_CO"),
//						(String)j.get("SAT_SELNG_CO"),(String)j.get("SUN_SELNG_CO"),(String)j.get("TMZON_00_06_SELNG_CO"),(String)j.get("TMZON_06_11_SELNG_CO"),(String)j.get("TMZON_11_14_SELNG_CO"),
//						(String)j.get("TMZON_14_17_SELNG_CO"),(String)j.get("TMZON_17_21_SELNG_CO"),(String)j.get("TMZON_21_24_SELNG_CO"),(String)j.get("ML_SELNG_CO"),(String)j.get("FML_SELNG_CO"),
//						(String)j.get("AGRDE_10_SELNG_CO"),(String)j.get("AGRDE_20_SELNG_CO"),(String)j.get("AGRDE_30_SELNG_CO"),(String)j.get("AGRDE_40_SELNG_CO"),(String)j.get("AGRDE_50_SELNG_CO"),
//						(String)j.get("AGRDE_60_SELNG_CO"),(String)j.get("STOR_CO"));
//						
//				System.out.println(nSales);
//				sales.add(nSales);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sales;
	}
	public static void main(String[] args) {
		try {
			readSalesFromFile();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//static 지우기
	}
		
}
