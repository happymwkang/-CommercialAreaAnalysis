package model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO {

	private String year;
	private String quarter;
	private String level1;
	private String level1Name;
	private String level2;
	private String level2Name;
	private String business;
	private double shopCnt;
	private double similrCnt;
	private double openCnt;
	private double closeCnt;
	private double frcShopCnt;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 년 : ");
		builder.append(year + "\n");
		builder.append("2. 분기 : ");
		builder.append(quarter + "\n");
		builder.append("3. Level1_상권코드 : ");
		builder.append(level1 + "\n");
		builder.append("4. Level1_상권명 : ");
		builder.append(level1Name + "\n");
		builder.append("5. Level2_상권코드 : ");
		builder.append(level2 + "\n");
		builder.append("6. Level2_상권명 : ");
		builder.append(level2Name + "\n");
		builder.append("7. 업종명 : ");
		builder.append(business + "\n");
		builder.append("8. 점포수 : ");
		builder.append(shopCnt + "\n");
		builder.append("9. 유사점포수 : ");
		builder.append(similrCnt + "\n");
		builder.append("10. 개업수 : ");
		builder.append(openCnt + "\n");
		builder.append("11. 폐업수 : ");
		builder.append(closeCnt + "\n");
		builder.append("12. 프랜차이즈 점포수 : ");
		builder.append(frcShopCnt + "\n");
		return builder.toString();
	}
}
