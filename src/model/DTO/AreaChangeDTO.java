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
	private String division;
	private String level2Name;
	private String areaChangeNm;
	private String openAvg;
	private String closeAvg;
	private String SeoulOpenAvg;
	private String SeoulCloseAvg;
}
