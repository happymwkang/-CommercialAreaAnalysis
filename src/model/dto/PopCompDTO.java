package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class PopCompDTO {
	private String year;
	private String quater;
	private String areaId;
	private int a10;
	private int a20;
	private int a30;
	private int a40;
	private int a50;
	private int a60;
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[[");
		builder.append(year);
		builder.append(" ,");
		builder.append(quater);
		builder.append(",'");
		builder.append(areaId);
		builder.append("'],[['나이', '상주인구'],");
		builder.append("['10  대' ,");
		builder.append(a10);
		builder.append("],[ '20 대' ,");
		builder.append(a20);
		builder.append("],[ '30 대' ,");
		builder.append(a30);
		builder.append("],[ '40 대' ,");
		builder.append(a40);
		builder.append("],[ '50 대' ,");
		builder.append(a50);
		builder.append("],[ '60 대' ,");
		builder.append(a60);
		builder.append("]]]");
		return builder.toString();
	}
}
