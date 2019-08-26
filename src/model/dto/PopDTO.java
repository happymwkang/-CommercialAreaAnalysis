package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopDTO {
	private String id;
	private String year;
	private String quater;
	private String sex;
	private String age;
	private String pop;
}
