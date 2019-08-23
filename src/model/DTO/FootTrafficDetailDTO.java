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
	private String level1Name;
	private String level2Name;
	private String sex;
	private String day;
	private String times;
	private String age;
	private String ft;
}
