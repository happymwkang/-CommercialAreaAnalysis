package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO {

	private String year;
	private String quarter;
	private String divisionCd;
	private String divisionNm;
	private String areaCd;
	private String areaNm;
	private String businessCd;
	private String businessNm;
	private double shopCnt;
	private double similrCnt;
	private double openCnt;
	private double closeCnt;
	private double frcShopCnt;
	
}
