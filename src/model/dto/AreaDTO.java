package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class AreaDTO {
	private String division; //상권구분
	private int id; //지역 코드
	private String name; //지역 이름
	private String sigungu; //시군구
}
