package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

		private String year;
		private String businessNm;
		private double shopCnt;
		
		@Override
		public String toString() {
			 StringBuilder builder = new StringBuilder();
		      builder.append("['");
		      builder.append(businessNm);
//		      builder.append("년',");
//		      builder.append("'");
//		      builder.append(businessNm);
		      builder.append("',");
		      builder.append(shopCnt);
		      builder.append("]");
		      
		      return builder.toString();
		}
}
