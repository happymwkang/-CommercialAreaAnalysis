package model;

import java.util.ArrayList;
import java.util.Arrays;


import model.dto.ChannelDTO;

import model.dto.AreaChangeDTO;
import model.dto.ChannelDTO;
import model.dto.FootTrafficDetailDTO;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONParse {

	public static ArrayList<ChannelDTO> JSONObjectParse(JSON json) {
		// 상권-점포
		ArrayList<ChannelDTO> channel = new ArrayList<ChannelDTO>();
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarStorQq");
		JSONArray data = (JSONArray) obj2.get("row");
		for (int i = 0; i < data.size(); i++) {
			JSONObject dataObj = (JSONObject) data.get(i);
			channel.add(new ChannelDTO((String) dataObj.get("STDR_YY_CD"), (String) dataObj.get("STDR_QU_CD"),
					(String) dataObj.get("TRDAR_SE_CD"), (String) dataObj.get("TRDAR_SE_CD_NM"),
					(String) dataObj.get("TRDAR_CD"), (String) dataObj.get("TRDAR_CD_NM"),(String) dataObj.get("SVC_INDUTY_CD"),
					(String) dataObj.get("SVC_INDUTY_CD_NM"), (double) dataObj.get("STOR_CO"),
					(double) dataObj.get("SIMILR_INDUTY_STOR_CO"), (double) dataObj.get("OPBIZ_STOR_CO"),
					(double) dataObj.get("CLSBIZ_STOR_CO"), (double) dataObj.get("FRC_STOR_CO")));
		}
		return channel;
	}

	public static ArrayList<AreaChangeDTO> JSONObjectParse1(JSON json) {
		// 상권-상권변화지표
		ArrayList<AreaChangeDTO> AreaChangeIx = new ArrayList<AreaChangeDTO>();
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarIxQq");
		JSONArray data = (JSONArray) obj2.get("row");
		for (int i = 0; i < data.size(); i++) {
			JSONObject dataObj = (JSONObject) data.get(i);
			AreaChangeIx.add(new AreaChangeDTO((String) dataObj.get("STDR_YY_CD"), (String) dataObj.get("STDR_QU_CD"),
					(String) dataObj.get("TRDAR_SE_CD"), (String) dataObj.get("TRDAR_SE_CD_NM"), (String) dataObj.get("TRDAR_CD"),
					(String) dataObj.get("TRDAR_CD_NM"), (String) dataObj.get("TRDAR_CHNGE_IX"), (String) dataObj.get("TRDAR_CHNGE_IX_NM"), 
					(String) dataObj.get("OPR_SALE_MT_AVRG"), (String) dataObj.get("CLS_SALE_MT_AVRG"), (String) dataObj.get("SU_OPR_SALE_MT_AVRG"),
					(String) dataObj.get("SU_CLS_SALE_MT_AVRG")));
		}
		return AreaChangeIx;
	}

	public static ArrayList<FootTrafficDetailDTO> JSONObjectParse2(JSON json) {
		// 상권-유동인구
		ArrayList<FootTrafficDetailDTO> footTraffic = new ArrayList<FootTrafficDetailDTO>();
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarFlpopQq");
		JSONArray data = (JSONArray) obj2.get("row");
		ArrayList<String> sexes = new ArrayList<>(Arrays.asList("MAG", "FAG"));
		ArrayList<String> days = new ArrayList<>(Arrays.asList("MONTM", "TUETM", "WEDTM", "THUTM", "FRITM", "SATTM", "SUNTM"));
		ArrayList<String> times = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
		ArrayList<String> ages = new ArrayList<>(Arrays.asList("10", "20", "30", "40", "50", "60"));
		int count = 0;

		for (int i = 0; i < data.size(); i++) {
			JSONObject dataObj = (JSONObject) data.get(i);
			ArrayList<String> arr =  new ArrayList<>(dataObj.keySet()); // 29부터 시작
			for (int j = 28; j < arr.size(); j++) {
				ArrayList<String> please = new ArrayList<>(Arrays.asList(arr.get(j).split("_")));
				ArrayList<String> result = new ArrayList<>();
				for(int k = 0; k < please.size() ; k++) {
					if (sexes.contains(please.get(k))) {
						count++;
						result.add(please.get(k).substring(0, 1));
					} 
					if (ages.contains(please.get(k))) {
						count++;
						result.add(please.get(k));
					}
					if (days.contains(please.get(k))) {
						count++;
						result.add(please.get(k).substring(0, 3));
					}
					if (times.contains(please.get(k))) {
						count++;
						result.add(please.get(k));
					}
					if (count == 4) {
						footTraffic.add(new FootTrafficDetailDTO((String) dataObj.get("STDR_YY_CD"),
								(String) dataObj.get("STDR_QU_CD"), (String) dataObj.get("TRDAR_SE_CD"), (String) dataObj.get("TRDAR_SE_CD_NM"), 
								(String) dataObj.get("TRDAR_CD"), (String) dataObj.get("TRDAR_CD_NM"), (String)result.get(0), 
								(String)result.get(1), (String)result.get(2), (String)result.get(3), (double) dataObj.get(arr.get(j))));
						count=0;
						break;
					}
				}
			}
		}
		return footTraffic;
	}
}
