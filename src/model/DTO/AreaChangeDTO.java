package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaChangeDTO {
	private String year;
	private String quarter;
	private String divisionCd;
	private String divisionNm;
	private String areaCd;
	private String areaNm;
	private String areaChangeCd;
	private String areaChangeNm;
	private String openAvg;
	private String closeAvg;
	private String SeoulOpenAvg;
	private String SeoulCloseAvg;
}
