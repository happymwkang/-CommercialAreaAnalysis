package model;

import java.util.ArrayList;

import model.DTO.ChannelDTO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONParse {
	
public static ArrayList<ChannelDTO> JSONObjectParse(JSON json) {
		
		ArrayList<ChannelDTO> channel = new ArrayList<ChannelDTO>();
		JSONObject obj = JSONObject.fromObject(json);
		JSONObject obj2 = (JSONObject) obj.get("VwsmTrdarStorQq");
		JSONArray data = (JSONArray) obj2.get("row");
		for (int i =0 ; i < data.size() ; i++) {
			JSONObject dataObj = (JSONObject) data.get(i);
			channel.add(new ChannelDTO((String) dataObj.get("STDR_YY_CD"), (String) dataObj.get("STDR_QU_CD"),
					(String) dataObj.get("TRDAR_SE_CD"),(String) dataObj.get("TRDAR_SE_CD_NM"), (String) dataObj.get("TRDAR_CD"), 
					(String) dataObj.get("TRDAR_CD_NM"),(String) dataObj.get("SVC_INDUTY_CD_NM"),(double) dataObj.get("STOR_CO"),
					(double) dataObj.get("SIMILR_INDUTY_STOR_CO"), (double) dataObj.get("OPBIZ_STOR_CO"), 
					(double) dataObj.get("CLSBIZ_STOR_CO"), (double) dataObj.get("FRC_STOR_CO")) );
		}
		return channel;
	}

}
