package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

import model.dao.AreaChangeDAO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class APIToJSON {
	static Properties propertiesApiInfo = new Properties();
	static {
		try {
			propertiesApiInfo.load(new FileInputStream("api.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		int[] a = {1,3};
		
		System.out.println(JSONParse.JSONObjectParse2(GetFootTraffic(a)));
		
//		for (int i = 0; i < 1000000; i++) {\
//			try {
//				System.out.println(ChannelDAO.APIaddDataChannel(JSONParse.JSONObjectParse(GetChannel(urlConfig(i)))));
//				if (ChannelDAO.APIaddDataChannel(JSONParse.JSONObjectParse(GetChannel(urlConfig(i)))) <= 0) {
//					break;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
////		System.out.println(GetChannel());
////		urlConfig(3);\
//		}
		
//		for (int i = 0; i < 1000000; i++) {
//			try {
//				System.out.println(AreaChangeDAO.APIaddDataAreaChanIx(JSONParse.JSONObjectParse1(GetAreaChangeIx(urlConfig(i)))));
//				if (AreaChangeDAO.APIaddDataAreaChanIx(JSONParse.JSONObjectParse1(GetAreaChangeIx(urlConfig(i)))) <= 0) {
//					break;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
////		System.out.println(GetChannel());
////		urlConfig(3);
//		}
		
	}

	public static JSON GetChannel(int[] requestNum) {
		BufferedReader br = null;
		String result = "";
		JSONObject obj = null;
		String key = "";

		try {
			key = propertiesApiInfo.getProperty("key");
//					+ dateConfig;

			URL urlstr = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/VwsmTrdarStorQq/" + requestNum[0]
					+ "/" + requestNum[1] + "/2018");
			HttpURLConnection urlconnection = (HttpURLConnection) urlstr.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			obj = JSONObject.fromObject(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obj;
	}

	public static int[] urlConfig(int n) {
		int[] requestNum = { 1, 999 };

		for (int i = 0; i <= n; i++) {
			if (i > 1) {
				requestNum[0] = requestNum[0] + 1000;
				requestNum[1] = requestNum[1] + 1000;
			}
		}
		if (requestNum[0] > 1) {
			requestNum[0] = requestNum[0] - 1;
		}
		return requestNum;
	}
	
	public static JSON GetAreaChangeIx(int[] requestNum) {
		BufferedReader br = null;
		String result = "";
		JSONObject obj = null;
		String key = "";

		try {
			key = propertiesApiInfo.getProperty("key");
//					+ dateConfig;

			URL urlstr = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/VwsmTrdarIxQq/" + requestNum[0]
					+ "/" + requestNum[1] + "/2018");
			HttpURLConnection urlconnection = (HttpURLConnection) urlstr.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			obj = JSONObject.fromObject(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obj;
	}
	
	public static JSON GetFootTraffic(int[] requestNum) {
		BufferedReader br = null;
		String result = "";
		JSONObject obj = null;
		String key = "";

		try {
			key = propertiesApiInfo.getProperty("key");
//					+ dateConfig;

			URL urlstr = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/VwsmTrdarFlpopQq/" + requestNum[0]
					+ "/" + requestNum[1] + "/2018");
			HttpURLConnection urlconnection = (HttpURLConnection) urlstr.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			obj = JSONObject.fromObject(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obj;
	}

}
