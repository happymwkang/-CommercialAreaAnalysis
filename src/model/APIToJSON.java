package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.DAO.ChannelDAO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class APIToJSON {

	public static void main(String[] args) {
		try {
			System.out.println(ChannelDAO.APIaddDataChannel(JSONParse.JSONObjectParse(GetChannel())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(GetChannel());
	}

	public static JSON GetChannel() {
		BufferedReader br = null;
		String result = "";
		JSONObject obj = null;
		String url = null;
		try {
			url = "http://openapi.seoul.go.kr:8088/797a42666568617038304266515253/json/VwsmTrdarStorQq/1/5/2017"; 
//					+ dateConfig;
			URL urlstr = new URL(url);
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

	public static String urlConfig(int i) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse("20190501");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, i); // 

		return format.format(cal.getTime());
	}

}
