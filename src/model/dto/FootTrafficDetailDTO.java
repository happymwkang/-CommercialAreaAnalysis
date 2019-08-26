package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootTrafficDetailDTO {
	private String year;
	private String quarter;
	private String divisionCd;
	private String divisionNm;
	private String areaCd;
	private String areaNm;
	private String sex;
	private String age;
	private String day;
	private String times;
	private double ft;
}
