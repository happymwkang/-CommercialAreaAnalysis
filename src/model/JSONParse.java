package model;

import java.util.ArrayList;
import java.util.Arrays;

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
					(String) dataObj.get("TRDAR_CD"), (String) dataObj.get("TRDAR_CD_NM"),
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
					(String) dataObj.get("TRDAR_SE_CD_NM"), (String) dataObj.get("TRDAR_CD_NM"),
					(String) dataObj.get("TRDAR_CHNGE_IX_NM"), (String) dataObj.get("OPR_SALE_MT_AVRG"),
					(String) dataObj.get("CLS_SALE_MT_AVRG"), (String) dataObj.get("SU_OPR_SALE_MT_AVRG"),
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
//	for (int i =0 ; i < data.size() ; i++) {
//		JSONObject dataObj = (JSONObject) data.get(i);
//		AreaChangeIx.add(new AreaChangeDTO((String) dataObj.get("STDR_YY_CD"), (String) dataObj.get("STDR_QU_CD"),
//				(String) dataObj.get("TRDAR_SE_CD_NM"),(String) dataObj.get("TRDAR_CD_NM"), (String) dataObj.get("TRDAR_CHNGE_IX_NM"), 
//				(String) dataObj.get("OPR_SALE_MT_AVRG"),(String) dataObj.get("CLS_SALE_MT_AVRG"),(String) dataObj.get("SU_OPR_SALE_MT_AVRG"),
//				(String) dataObj.get("SU_CLS_SALE_MT_AVRG")));
//	}
//	JSONObject dataObj = (JSONObject) data.get(1);
//	footTraffic.addAll(dataObj.keySet());
//	System.out.println(footTraffic.get(1));
		String[] a = {};
//	for(int i = 0 ; i < footTraffic.size(); i++) {
//		
//	}
		System.out.println(footTraffic);
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> sexes = new ArrayList<>(Arrays.asList("MAG", "FAG"));
		ArrayList<String> days = new ArrayList<>(Arrays.asList("MONTM", "TUETM", "WEDTM", "THUTM", "FRITM", "SATTM", "SUNTM"));
		ArrayList<String> times = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
		ArrayList<String> ages = new ArrayList<>(Arrays.asList("10", "20", "30", "40", "50", "60"));

		int count = 0;

		for (int i = 0; i < data.size(); i++) {
			JSONObject dataObj = (JSONObject) data.get(i);
			arr.addAll(dataObj.keySet());
			for (int j = 0; j < arr.size(); j++) {
				ArrayList<String> please = new ArrayList<>(Arrays.asList(arr.get(i).split("_")));
				System.out.println(please);
				ArrayList<String> result = new ArrayList<>();
				if (sexes.contains(please.get(j))) {
					count++;
					result.add(please.get(0));
				} else if (days.contains(please.get(j))) {
					count++;
					result.add(please.get(1));
				} else if (times.contains(please.get(j))) {
					count++;
					result.add(please.get(2));
				} else if (ages.contains(please.get(j))) {
					count++;
					result.add(please.get(3));
				} else if (count == 4) {
					footTraffic.add(new FootTrafficDetailDTO((String) dataObj.get("STDR_YY_CD"),
							(String) dataObj.get("STDR_QU_CD"), (String) dataObj.get("TRDAR_SE_CD_NM"),
							(String) dataObj.get("TRDAR_CD_NM"), (String)result.get(0), (String)result.get(1), (String)result.get(2), (String)result.get(3),
							(String) dataObj.get(arr.get(i))));
					break;
				}
			}

			System.out.println(footTraffic + "test");


		}
		return footTraffic;
	}
}
