package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.dao.AreaDAO;
import model.dao.FtDAO;
import model.dao.PopDAO;
import model.dao.SalesDAO;
import model.dto.AreaDTO;
import model.dto.PopDTO;
import model.dto.SalesDTO;
import model.dto.PopCompDTO;

public class Service {
	private static AreaDAO areaDAO = AreaDAO.getInstance();
	private static SalesDAO salesDAO = SalesDAO.getInstance();
	private static FtDAO ftDAO = FtDAO.getInstance();
	private static PopDAO popDAO = PopDAO.getInstance();
	
	private static Service instance = new Service();
	private Service() {}
	
	public static Service getInstance() {
		return instance;
	}
	public static HashMap getSigunguCode() throws SQLException{
		HashMap<String,String> matcher = new HashMap();
		JSONParser parser = new JSONParser();
		ArrayList<AreaDTO> area = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\Playdata\\Desktop\\git\\CommercialAreaAnalysis\\sigungu.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray arr = (JSONArray) jsonObject.get("DATA");
			for (int i = 0 ; i<arr.size(); i++) {
				JSONObject j = (JSONObject) arr.get(i);
//				System.out.println(j);
				matcher.put((String)j.get("sig_cd"), (String)j.get("sig_kor_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return matcher;
	}
	

	public static int readAreasFromAPI() throws SQLException, IOException, ParseException{
		HashMap matcher = getSigunguCode();
		
		BufferedReader br = null;
		JSONParser parser = new JSONParser();
		ArrayList<AreaDTO> areas = new ArrayList<>();
		JSONObject obj = null;
		
		int inserted = 0;
		
		int start = 1;
//		int end = 3;
		int end = start + 998;
		
		URL url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/TbgisTrdarRelm/"+start+"/"+end+"/2018");
		
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		
		obj = (JSONObject) parser.parse(br.readLine());
		JSONObject obj2 = (JSONObject) obj.get("TbgisTrdarRelm");
		long dataNum = (long) obj2.get("list_total_count");
//		dataNum = 4;
		JSONArray obj3 = (JSONArray) obj2.get("row");

		for(int i = 0 ; i < obj3.size(); i++) {
			
			JSONObject j =(JSONObject) obj3.get(i);
			AreaDTO nArea = new AreaDTO((String)j.get("TRDAR_SE_CD"),(String)j.get("TRDAR_SE_CD_NM"),Integer.parseInt((String)j.get("TRDAR_CD")), (String)j.get("TRDAR_CD_NM"),(String)matcher.get((String)j.get("SIGNGU_CD")));
//			System.out.println(nArea);
			areas.add(nArea);
		}
		try {
			inserted += areaDAO.addAreas(areas);
			areas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(end+998 < dataNum) {
			
			start = end+1;
			end=start+998;
			
			url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/TbgisTrdarRelm/"+start+"/"+end+"/2018");
			
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			
			
			
			obj = (JSONObject) parser.parse(br.readLine());
			obj2 = (JSONObject) obj.get("TbgisTrdarRelm");
			obj3 = (JSONArray) obj2.get("row");

			for(int i = 0 ; i < obj3.size(); i++) {
				JSONObject j =(JSONObject) obj3.get(i);
				AreaDTO nArea = new AreaDTO((String)j.get("TRDAR_SE_CD"),(String)j.get("TRDAR_SE_CD_NM"),Integer.parseInt((String)j.get("TRDAR_CD")), (String)j.get("TRDAR_CD_NM"),(String)matcher.get((String)j.get("SIGNGU_CD")));
//				System.out.println(nSales);
				areas.add(nArea);
			}
			
			try {
				inserted += areaDAO.addAreas(areas);
				areas = new ArrayList<>();
				System.out.println(inserted);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		start = end+1;
		end=(int) dataNum;
		
		url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/TbgisTrdarRelm/"+start+"/"+end+"/2018");
		
		urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		
		
		obj = (JSONObject) parser.parse(br.readLine());
		obj2 = (JSONObject) obj.get("TbgisTrdarRelm");
		obj3 = (JSONArray) obj2.get("row");

		for(int i = 0 ; i < obj3.size(); i++) {
			JSONObject j =(JSONObject) obj3.get(i);
			AreaDTO nArea = new AreaDTO((String)j.get("TRDAR_SE_CD"),(String)j.get("TRDAR_SE_CD_NM"),Integer.parseInt((String)j.get("TRDAR_CD")), (String)j.get("TRDAR_CD_NM"),(String)matcher.get((String)j.get("SIGNGU_CD")));
//			System.out.println(nSales);
			areas.add(nArea);
		}
//		System.out.println(sales);
		try {
			inserted += areaDAO.addAreas(areas);
			areas = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inserted;
	}
	
	public static int readSalesFromAPI() throws SQLException, IOException, ParseException{
		BufferedReader br = null;
		JSONParser parser = new JSONParser();
		ArrayList<SalesDTO> sales = new ArrayList<>();
		JSONObject obj = null;
		
		int inserted = 0;
		
		int start = 1;
//		int end = 3;
		int end = start + 998;
		
		URL url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarSelngQq/"+start+"/"+end+"/2018");
		
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		
		obj = (JSONObject) parser.parse(br.readLine());
		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarSelngQq");
		long dataNum = (long) obj2.get("list_total_count");
//		dataNum = 3000;
		JSONArray obj3 = (JSONArray) obj2.get("row");

		for(int i = 0 ; i < obj3.size(); i++) {
			
			JSONObject j =(JSONObject) obj3.get(i);
			SalesDTO nSales = new SalesDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),
					(String)j.get("SVC_INDUTY_CD_NM"),(String)j.get("THSMON_SELNG_AMT"),(String)j.get("MDWK_SELNG_AMT"),(String)j.get("WKEND_SELNG_AMT"),(String)j.get("MON_SELNG_AMT"),
					(String)j.get("TUES_SELNG_AMT"),(String)j.get("WED_SELNG_AMT"),(String)j.get("THUR_SELNG_AMT"),(String)j.get("FRI_SELNG_AMT"),(String)j.get("SAT_SELNG_AMT"),
					(String)j.get("SUN_SELNG_AMT"),(String)j.get("TMZON_00_06_SELNG_AMT"),(String)j.get("TMZON_06_11_SELNG_AMT"),(String)j.get("TMZON_11_14_SELNG_AMT"),(String)j.get("TMZON_14_17_SELNG_AMT"),
					(String)j.get("TMZON_17_21_SELNG_AMT"),(String)j.get("TMZON_21_24_SELNG_AMT"),(String)j.get("ML_SELNG_AMT"),(String)j.get("FML_SELNG_AMT"),(String)j.get("AGRDE_10_SELNG_AMT"),
					(String)j.get("AGRDE_20_SELNG_AMT"),(String)j.get("AGRDE_30_SELNG_AMT"),(String)j.get("AGRDE_40_SELNG_AMT"),(String)j.get("AGRDE_50_SELNG_AMT"),(String)j.get("AGRDE_60_SELNG_AMT"),
					(String)j.get("THSMON_SELNG_CO"),(String)j.get("MDWK_SELNG_CO"),(String)j.get("WKEND_SELNG_CO"),
					(String)j.get("MON_SELNG_CO"),(String)j.get("TUES_SELNG_CO"),(String)j.get("WED_SELNG_CO"),(String)j.get("THUR_SELNG_CO"),(String)j.get("FRI_SELNG_CO"),
					(String)j.get("SAT_SELNG_CO"),(String)j.get("SUN_SELNG_CO"),(String)j.get("TMZON_00_06_SELNG_CO"),(String)j.get("TMZON_06_11_SELNG_CO"),(String)j.get("TMZON_11_14_SELNG_CO"),
					(String)j.get("TMZON_14_17_SELNG_CO"),(String)j.get("TMZON_17_21_SELNG_CO"),(String)j.get("TMZON_21_24_SELNG_CO"),(String)j.get("ML_SELNG_CO"),(String)j.get("FML_SELNG_CO"),
					(String)j.get("AGRDE_10_SELNG_CO"),(String)j.get("AGRDE_20_SELNG_CO"),(String)j.get("AGRDE_30_SELNG_CO"),(String)j.get("AGRDE_40_SELNG_CO"),(String)j.get("AGRDE_50_SELNG_CO"),
					(String)j.get("AGRDE_60_SELNG_CO"),(String)j.get("STOR_CO"));
//			System.out.println(nSales);
			sales.add(nSales);
		}
		try {
			inserted += salesDAO.addSales(sales);
			sales = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(end+998 < dataNum) {
			
			start = end+1;
			end=start+998;
			
			url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarSelngQq/"+start+"/"+end+"/2018");
			
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			
			
			
			obj = (JSONObject) parser.parse(br.readLine());
			obj2 = (JSONObject) obj.get("VwsmTrdarSelngQq");
			obj3 = (JSONArray) obj2.get("row");

			for(int i = 0 ; i < obj3.size(); i++) {
				JSONObject j =(JSONObject) obj3.get(i);
				SalesDTO nSales = new SalesDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),
						(String)j.get("SVC_INDUTY_CD_NM"),(String)j.get("THSMON_SELNG_AMT"),(String)j.get("MDWK_SELNG_AMT"),(String)j.get("WKEND_SELNG_AMT"),(String)j.get("MON_SELNG_AMT"),
						(String)j.get("TUES_SELNG_AMT"),(String)j.get("WED_SELNG_AMT"),(String)j.get("THUR_SELNG_AMT"),(String)j.get("FRI_SELNG_AMT"),(String)j.get("SAT_SELNG_AMT"),
						(String)j.get("SUN_SELNG_AMT"),(String)j.get("TMZON_00_06_SELNG_AMT"),(String)j.get("TMZON_06_11_SELNG_AMT"),(String)j.get("TMZON_11_14_SELNG_AMT"),(String)j.get("TMZON_14_17_SELNG_AMT"),
						(String)j.get("TMZON_17_21_SELNG_AMT"),(String)j.get("TMZON_21_24_SELNG_AMT"),(String)j.get("ML_SELNG_AMT"),(String)j.get("FML_SELNG_AMT"),(String)j.get("AGRDE_10_SELNG_AMT"),
						(String)j.get("AGRDE_20_SELNG_AMT"),(String)j.get("AGRDE_30_SELNG_AMT"),(String)j.get("AGRDE_40_SELNG_AMT"),(String)j.get("AGRDE_50_SELNG_AMT"),(String)j.get("AGRDE_60_SELNG_AMT"),
						(String)j.get("THSMON_SELNG_CO"),(String)j.get("MDWK_SELNG_CO"),(String)j.get("WKEND_SELNG_CO"),
						(String)j.get("MON_SELNG_CO"),(String)j.get("TUES_SELNG_CO"),(String)j.get("WED_SELNG_CO"),(String)j.get("THUR_SELNG_CO"),(String)j.get("FRI_SELNG_CO"),
						(String)j.get("SAT_SELNG_CO"),(String)j.get("SUN_SELNG_CO"),(String)j.get("TMZON_00_06_SELNG_CO"),(String)j.get("TMZON_06_11_SELNG_CO"),(String)j.get("TMZON_11_14_SELNG_CO"),
						(String)j.get("TMZON_14_17_SELNG_CO"),(String)j.get("TMZON_17_21_SELNG_CO"),(String)j.get("TMZON_21_24_SELNG_CO"),(String)j.get("ML_SELNG_CO"),(String)j.get("FML_SELNG_CO"),
						(String)j.get("AGRDE_10_SELNG_CO"),(String)j.get("AGRDE_20_SELNG_CO"),(String)j.get("AGRDE_30_SELNG_CO"),(String)j.get("AGRDE_40_SELNG_CO"),(String)j.get("AGRDE_50_SELNG_CO"),
						(String)j.get("AGRDE_60_SELNG_CO"),(String)j.get("STOR_CO"));
//				System.out.println(nSales);
				sales.add(nSales);
			}
			
			try {
				inserted += salesDAO.addSales(sales);
				sales = new ArrayList<>();
				System.out.println(inserted);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		start = end+1;
		end=(int) dataNum;
		
		url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarSelngQq/"+start+"/"+end+"/2018");
		
		urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		
		
		obj = (JSONObject) parser.parse(br.readLine());
		obj2 = (JSONObject) obj.get("VwsmTrdarSelngQq");
		obj3 = (JSONArray) obj2.get("row");

		for(int i = 0 ; i < obj3.size(); i++) {
			JSONObject j =(JSONObject) obj3.get(i);
			SalesDTO nSales = new SalesDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),
					(String)j.get("SVC_INDUTY_CD_NM"),(String)j.get("THSMON_SELNG_AMT"),(String)j.get("MDWK_SELNG_AMT"),(String)j.get("WKEND_SELNG_AMT"),(String)j.get("MON_SELNG_AMT"),
					(String)j.get("TUES_SELNG_AMT"),(String)j.get("WED_SELNG_AMT"),(String)j.get("THUR_SELNG_AMT"),(String)j.get("FRI_SELNG_AMT"),(String)j.get("SAT_SELNG_AMT"),
					(String)j.get("SUN_SELNG_AMT"),(String)j.get("TMZON_00_06_SELNG_AMT"),(String)j.get("TMZON_06_11_SELNG_AMT"),(String)j.get("TMZON_11_14_SELNG_AMT"),(String)j.get("TMZON_14_17_SELNG_AMT"),
					(String)j.get("TMZON_17_21_SELNG_AMT"),(String)j.get("TMZON_21_24_SELNG_AMT"),(String)j.get("ML_SELNG_AMT"),(String)j.get("FML_SELNG_AMT"),(String)j.get("AGRDE_10_SELNG_AMT"),
					(String)j.get("AGRDE_20_SELNG_AMT"),(String)j.get("AGRDE_30_SELNG_AMT"),(String)j.get("AGRDE_40_SELNG_AMT"),(String)j.get("AGRDE_50_SELNG_AMT"),(String)j.get("AGRDE_60_SELNG_AMT"),
					(String)j.get("THSMON_SELNG_CO"),(String)j.get("MDWK_SELNG_CO"),(String)j.get("WKEND_SELNG_CO"),
					(String)j.get("MON_SELNG_CO"),(String)j.get("TUES_SELNG_CO"),(String)j.get("WED_SELNG_CO"),(String)j.get("THUR_SELNG_CO"),(String)j.get("FRI_SELNG_CO"),
					(String)j.get("SAT_SELNG_CO"),(String)j.get("SUN_SELNG_CO"),(String)j.get("TMZON_00_06_SELNG_CO"),(String)j.get("TMZON_06_11_SELNG_CO"),(String)j.get("TMZON_11_14_SELNG_CO"),
					(String)j.get("TMZON_14_17_SELNG_CO"),(String)j.get("TMZON_17_21_SELNG_CO"),(String)j.get("TMZON_21_24_SELNG_CO"),(String)j.get("ML_SELNG_CO"),(String)j.get("FML_SELNG_CO"),
					(String)j.get("AGRDE_10_SELNG_CO"),(String)j.get("AGRDE_20_SELNG_CO"),(String)j.get("AGRDE_30_SELNG_CO"),(String)j.get("AGRDE_40_SELNG_CO"),(String)j.get("AGRDE_50_SELNG_CO"),
					(String)j.get("AGRDE_60_SELNG_CO"),(String)j.get("STOR_CO"));
//			System.out.println(nSales);
			sales.add(nSales);
		}
//		System.out.println(sales);
		try {
			inserted += salesDAO.addSales(sales);
			sales = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inserted;
	}
	
	
//	public static int readFtFromAPI() throws SQLException, IOException, ParseException{
//		ArrayList<String> sexes = new ArrayList<>(Arrays.asList("MAG","FAG"));
//		ArrayList<String> days = new ArrayList<>(Arrays.asList("MONTM","TUETM","WEDTM","THUTM","FRITM","SATTM","SUNTM"));
//		ArrayList<String> times = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));
//		ArrayList<String> ages = new ArrayList<>(Arrays.asList("10","20","30","40","50","60"));
//		
//		BufferedReader br = null;
//		JSONParser parser = new JSONParser();
//		
//		JSONObject obj = null;
//		
//		int inserted = 0;
//		
//		int start = 1;
//		int end = 3;
////		int end = start + 998;
//		URL url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarFlpopQq/"+start+"/"+end+"/2018");
//		
//		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
//		urlconnection.setRequestMethod("GET");
//		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//		
//		obj = (JSONObject) parser.parse(br.readLine());
//		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarFlpopQq");
//		long dataNum = (long) obj2.get("list_total_count");
////		dataNum = 3000;
//		JSONArray obj3 = (JSONArray) obj2.get("row");
//		
////		Set keySet = ((JSONObject) obj3.get(0)).keySet();
////		System.out.println(keySet.getClass().toString());
//		ArrayList<String> keys = new ArrayList<>(((JSONObject) obj3.get(0)).keySet());
//		System.out.println(keys.get(0));
//		int howMuch = 0;
//		for(int i = 0 ; i < obj3.size(); i++) {
//			ArrayList<FtDTO> fts = new ArrayList<>();
//			
//			JSONObject j =(JSONObject) obj3.get(i);
//			for(String k : keys) {
//				//키를 한개씩 불러오면서 _를 기준으로 분리 후 4개의 나이,성별,요일,시간 다 있는지 확인
//				ArrayList<String> sKey = new ArrayList<>(Arrays.asList(k.split("_"))); 
//				
//				if(sKey.size()>=6  ) {
//					
//					int c = 0;
//					//sex,day,time,age
//					String[] info = new String[4];
//					for(String s : sKey) {
//						if(sexes.contains(s)) {
//							c+=1;
//							info[0] = s;
//						}else if(days.contains(s)) {
//							c+=1;
//							info[1] =s;
//						}else if(times.contains(s)) {
//							c+=1;
//							info[2]=s;
//						}else if(ages.contains(s)) {
//							c+=1;
//							info[3]=s;
//						}else if(c==4) {
//							break;
//						}
//					}if(c==4){
	//					FtDTO nFt = new FtDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),info[0],info[3],info[2],info[1],j.get(k)+"");
	////					System.out.println(nFt);
	//					fts.add(nFt);
	////					howMuch+=1;
//					}
//				}
//			}
//			try {
//				ftDAO.addFts(fts);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
////			System.out.println(nSales);
////		}
////		try {
////			inserted += ftDAO.addFts(fts);
////			fts = new ArrayList<>();
////		} catch (Exception e) {
////			e.printStackTrace();
//		}
//		System.out.println(howMuch);
//		//////////////여기까지/////////////////
//		
////		
////		while(end+998 < dataNum) {
////			
////			start = end+1;
////			end=start+998;
////			
////			url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarFlpopQq/"+start+"/"+end+"/2018");
////			
////			urlconnection = (HttpURLConnection) url.openConnection();
////			urlconnection.setRequestMethod("GET");
////			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
////			
////			
////			
////			obj = (JSONObject) parser.parse(br.readLine());
////			obj2 = (JSONObject) obj.get("VwsmTrdarFlpopQq");
////			obj3 = (JSONArray) obj2.get("row");
////
////			for(int i = 0 ; i < obj3.size(); i++) {
////				JSONObject j =(JSONObject) obj3.get(i);
////				SalesDTO nSales = new SalesDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),
////						(String)j.get("SVC_INDUTY_CD_NM"),(String)j.get("THSMON_SELNG_AMT"),(String)j.get("MDWK_SELNG_AMT"),(String)j.get("WKEND_SELNG_AMT"),(String)j.get("MON_SELNG_AMT"),
////						(String)j.get("TUES_SELNG_AMT"),(String)j.get("WED_SELNG_AMT"),(String)j.get("THUR_SELNG_AMT"),(String)j.get("FRI_SELNG_AMT"),(String)j.get("SAT_SELNG_AMT"),
////						(String)j.get("SUN_SELNG_AMT"),(String)j.get("TMZON_00_06_SELNG_AMT"),(String)j.get("TMZON_06_11_SELNG_AMT"),(String)j.get("TMZON_11_14_SELNG_AMT"),(String)j.get("TMZON_14_17_SELNG_AMT"),
////						(String)j.get("TMZON_17_21_SELNG_AMT"),(String)j.get("TMZON_21_24_SELNG_AMT"),(String)j.get("ML_SELNG_AMT"),(String)j.get("FML_SELNG_AMT"),(String)j.get("AGRDE_10_SELNG_AMT"),
////						(String)j.get("AGRDE_20_SELNG_AMT"),(String)j.get("AGRDE_30_SELNG_AMT"),(String)j.get("AGRDE_40_SELNG_AMT"),(String)j.get("AGRDE_50_SELNG_AMT"),(String)j.get("AGRDE_60_SELNG_AMT"),
////						(String)j.get("THSMON_SELNG_CO"),(String)j.get("MDWK_SELNG_CO"),(String)j.get("WKEND_SELNG_CO"),
////						(String)j.get("MON_SELNG_CO"),(String)j.get("TUES_SELNG_CO"),(String)j.get("WED_SELNG_CO"),(String)j.get("THUR_SELNG_CO"),(String)j.get("FRI_SELNG_CO"),
////						(String)j.get("SAT_SELNG_CO"),(String)j.get("SUN_SELNG_CO"),(String)j.get("TMZON_00_06_SELNG_CO"),(String)j.get("TMZON_06_11_SELNG_CO"),(String)j.get("TMZON_11_14_SELNG_CO"),
////						(String)j.get("TMZON_14_17_SELNG_CO"),(String)j.get("TMZON_17_21_SELNG_CO"),(String)j.get("TMZON_21_24_SELNG_CO"),(String)j.get("ML_SELNG_CO"),(String)j.get("FML_SELNG_CO"),
////						(String)j.get("AGRDE_10_SELNG_CO"),(String)j.get("AGRDE_20_SELNG_CO"),(String)j.get("AGRDE_30_SELNG_CO"),(String)j.get("AGRDE_40_SELNG_CO"),(String)j.get("AGRDE_50_SELNG_CO"),
////						(String)j.get("AGRDE_60_SELNG_CO"),(String)j.get("STOR_CO"));
//////				System.out.println(nSales);
////				sales.add(nSales);
////			}
////			
////			try {
////				inserted += salesDAO.addSales(sales);
////				sales = new ArrayList<>();
////				System.out.println(inserted);
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
////			
////		}
////		
////		start = end+1;
////		end=(int) dataNum;
////		
////		url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarFlpopQq/"+start+"/"+end+"/2018");
////		
////		urlconnection = (HttpURLConnection) url.openConnection();
////		urlconnection.setRequestMethod("GET");
////		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
////		
////		
////		obj = (JSONObject) parser.parse(br.readLine());
////		obj2 = (JSONObject) obj.get("VwsmTrdarFlpopQq");
////		obj3 = (JSONArray) obj2.get("row");
////
////		for(int i = 0 ; i < obj3.size(); i++) {
////			JSONObject j =(JSONObject) obj3.get(i);
////			SalesDTO nSales = new SalesDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),
////					(String)j.get("SVC_INDUTY_CD_NM"),(String)j.get("THSMON_SELNG_AMT"),(String)j.get("MDWK_SELNG_AMT"),(String)j.get("WKEND_SELNG_AMT"),(String)j.get("MON_SELNG_AMT"),
////					(String)j.get("TUES_SELNG_AMT"),(String)j.get("WED_SELNG_AMT"),(String)j.get("THUR_SELNG_AMT"),(String)j.get("FRI_SELNG_AMT"),(String)j.get("SAT_SELNG_AMT"),
////					(String)j.get("SUN_SELNG_AMT"),(String)j.get("TMZON_00_06_SELNG_AMT"),(String)j.get("TMZON_06_11_SELNG_AMT"),(String)j.get("TMZON_11_14_SELNG_AMT"),(String)j.get("TMZON_14_17_SELNG_AMT"),
////					(String)j.get("TMZON_17_21_SELNG_AMT"),(String)j.get("TMZON_21_24_SELNG_AMT"),(String)j.get("ML_SELNG_AMT"),(String)j.get("FML_SELNG_AMT"),(String)j.get("AGRDE_10_SELNG_AMT"),
////					(String)j.get("AGRDE_20_SELNG_AMT"),(String)j.get("AGRDE_30_SELNG_AMT"),(String)j.get("AGRDE_40_SELNG_AMT"),(String)j.get("AGRDE_50_SELNG_AMT"),(String)j.get("AGRDE_60_SELNG_AMT"),
////					(String)j.get("THSMON_SELNG_CO"),(String)j.get("MDWK_SELNG_CO"),(String)j.get("WKEND_SELNG_CO"),
////					(String)j.get("MON_SELNG_CO"),(String)j.get("TUES_SELNG_CO"),(String)j.get("WED_SELNG_CO"),(String)j.get("THUR_SELNG_CO"),(String)j.get("FRI_SELNG_CO"),
////					(String)j.get("SAT_SELNG_CO"),(String)j.get("SUN_SELNG_CO"),(String)j.get("TMZON_00_06_SELNG_CO"),(String)j.get("TMZON_06_11_SELNG_CO"),(String)j.get("TMZON_11_14_SELNG_CO"),
////					(String)j.get("TMZON_14_17_SELNG_CO"),(String)j.get("TMZON_17_21_SELNG_CO"),(String)j.get("TMZON_21_24_SELNG_CO"),(String)j.get("ML_SELNG_CO"),(String)j.get("FML_SELNG_CO"),
////					(String)j.get("AGRDE_10_SELNG_CO"),(String)j.get("AGRDE_20_SELNG_CO"),(String)j.get("AGRDE_30_SELNG_CO"),(String)j.get("AGRDE_40_SELNG_CO"),(String)j.get("AGRDE_50_SELNG_CO"),
////					(String)j.get("AGRDE_60_SELNG_CO"),(String)j.get("STOR_CO"));
//////			System.out.println(nSales);
////			sales.add(nSales);
////		}
//////		System.out.println(sales);
////		try {
////			inserted += salesDAO.addSales(sales);
////			sales = new ArrayList<>();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		return inserted;
//	}
	
	
	
	
	
	public static int readPopFromAPI() throws SQLException, IOException, ParseException{
		ArrayList<String> sexes = new ArrayList<>(Arrays.asList("MAG","FAG"));
		ArrayList<String> ages = new ArrayList<>(Arrays.asList("10","20","30","40","50","60"));
		
		BufferedReader br = null;
		JSONParser parser = new JSONParser();
		
		JSONObject obj = null;
		
		int inserted = 0;
		
		int start = 1;
//		int end = 3;
		int end = start + 998;
		URL url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarRepopQq/"+start+"/"+end+"/2018");
		
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		
		obj = (JSONObject) parser.parse(br.readLine());
		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarRepopQq");
		long dataNum = (long) obj2.get("list_total_count");
//		dataNum = 3000;
		JSONArray obj3 = (JSONArray) obj2.get("row");
		
		ArrayList<String> keys = new ArrayList<>(((JSONObject) obj3.get(0)).keySet());
		
		ArrayList<PopDTO> pops = new ArrayList<>();
		for(int i = 0 ; i < obj3.size(); i++) {
			
			JSONObject j =(JSONObject) obj3.get(i);
			for(String k : keys) {
				//키를 한개씩 불러오면서 _를 기준으로 분리 후 2개의 나이,성별 다 있는지 확인
				ArrayList<String> sKey = new ArrayList<>(Arrays.asList(k.split("_"))); 
				
				if(sKey.size()>=4  ) {
					
					int c = 0;
					//sex,age
					String[] info = new String[2];
					for(String s : sKey) {
						if(sexes.contains(s)) {
							c+=1;
							info[0] = s;
						}else if(ages.contains(s)) {
							c+=1;
							info[1]=s;
						}else if(c==2) {
							break;
						}
					}
					if(c==2) {
						PopDTO nPop = new PopDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),info[0],info[1],j.get(k)+"");
//						System.out.println(nPop);
						pops.add(nPop);
					}
				}
			}
		}
		try {
			inserted += popDAO.addPops(pops);
			pops = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//////////////여기까지/////////////////
		
		
		while(end+998 < dataNum) {
			
			start = end+1;
			end=start+998;
			
			url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarRepopQq/"+start+"/"+end+"/2018");
			
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			
			
			
			obj = (JSONObject) parser.parse(br.readLine());
			obj2 = (JSONObject) obj.get("VwsmTrdarRepopQq");
			obj3 = (JSONArray) obj2.get("row");

			pops = new ArrayList<>();
			for(int i = 0 ; i < obj3.size(); i++) {
				
				JSONObject j =(JSONObject) obj3.get(i);
				for(String k : keys) {
					//키를 한개씩 불러오면서 _를 기준으로 분리 후 2개의 나이,성별 다 있는지 확인
					ArrayList<String> sKey = new ArrayList<>(Arrays.asList(k.split("_"))); 
					
					if(sKey.size()>=4  ) {
						
						int c = 0;
						//sex,age
						String[] info = new String[2];
						for(String s : sKey) {
							if(sexes.contains(s)) {
								c+=1;
								info[0] = s;
							}else if(ages.contains(s)) {
								c+=1;
								info[1]=s;
							}else if(c==2) {
								break;
							}
						}
						if(c==2) {
							PopDTO nPop = new PopDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),info[0],info[1],j.get(k)+"");
//							System.out.println(nPop);
							pops.add(nPop);
						}
					}
				}
			}
			try {
				inserted += popDAO.addPops(pops);
				pops=new ArrayList<>();
				System.out.println(inserted);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		start = end+1;
		end=(int) dataNum;
		
		url = new URL("http://openapi.seoul.go.kr:8088/74576a536f7368653330436847626c/json/VwsmTrdarRepopQq/"+start+"/"+end+"/2018");
		
		urlconnection = (HttpURLConnection) url.openConnection();
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		
		
		obj = (JSONObject) parser.parse(br.readLine());
		obj2 = (JSONObject) obj.get("VwsmTrdarRepopQq");
		obj3 = (JSONArray) obj2.get("row");

		pops = new ArrayList<>();
		for(int i = 0 ; i < obj3.size(); i++) {
			
			JSONObject j =(JSONObject) obj3.get(i);
			for(String k : keys) {
				//키를 한개씩 불러오면서 _를 기준으로 분리 후 2개의 나이,성별 다 있는지 확인
				ArrayList<String> sKey = new ArrayList<>(Arrays.asList(k.split("_"))); 
				
				if(sKey.size()>=4  ) {
					
					int c = 0;
					//sex,age
					String[] info = new String[2];
					for(String s : sKey) {
						if(sexes.contains(s)) {
							c+=1;
							info[0] = s;
						}else if(ages.contains(s)) {
							c+=1;
							info[1]=s;
						}else if(c==2) {
							break;
						}
					}
					if(c==2) {
						PopDTO nPop = new PopDTO((String)j.get("TRDAR_CD"),(String)j.get("STDR_YY_CD"),(String)j.get("STDR_QU_CD"),info[0],info[1],j.get(k)+"");
//						System.out.println(nPop);
						pops.add(nPop);
					}
				}
			}
		}
		try {
			inserted += popDAO.addPops(pops);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inserted;
	}
	

	public String getPopSet(String areaId) throws SQLException {
		ArrayList<PopCompDTO> popSet =  popDAO.getPopSet(areaId);
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(popSet.get(0).toString());
		for(int i = 1 ; i<popSet.size() ; i++) {
			builder.append(","+ popSet.get(i).toString());
		}
		builder.append("]");
		
		
		return builder.toString();
	}
	
	
//	public static void main(String[] args) {
//		try {
//			readAreasFromAPI();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//static 지우기
//		
//		
//	}
		
}
