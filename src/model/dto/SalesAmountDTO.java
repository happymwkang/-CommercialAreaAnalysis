package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class SalesAmountDTO {
	private String id;
	private String year;
	private String quater;
	private String business;//업종 6
	private int monthSales;//월매출  7
	private int wkSales;//주중매출 8
	private int wkendSales;//주말매출 9
	private int monSales;//월요일 10
	private int tueSales;//화 11
	private int wedSales;
	private int thuSales;
	private int friSales;
	private int satSales;
	private int sunSales;
	private int t0006Sales; //시간대별 매출 17
	private int t0611Sales;
	private int t1114Sales;
	private int t1417Sales;
	private int t1721Sales;
	private int t2124Sales;
	private int mSales; //남성매출
	private int wSales; //여성매출 24
	private int a10Sales; //10대 매출
	private int a20Sales;
	private int a30Sales;
	private int a40Sales;
	private int a50Sales;
	private int a60Sales;
	
	// 0:정보, 1:총합, 2: 요일별, 3: 시간대별 , 4: 성별별, 5 : 연령별
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[['");
		builder.append(id);
		builder.append("' ,");
		builder.append(year);
		builder.append(" ,");
		builder.append(quater);
		builder.append(" ,'");
		builder.append(business);
		builder.append("'] ,[ '월 총' ,");
		builder.append(monthSales);
		builder.append(" , '주중' ,");
		builder.append(wkSales);
		builder.append(" , '주말' ,");
		builder.append(wkendSales);
		builder.append("] ,[ '월요일',");
		builder.append(monSales);
		builder.append(" , '화요일',");
		builder.append(tueSales);
		builder.append(" , '수요일' ,");
		builder.append(wedSales);
		builder.append(" , '목요일',");
		builder.append(thuSales);
		builder.append(" , '금요일' ,");
		builder.append(friSales);
		builder.append(" , '토요일',");
		builder.append(satSales);
		builder.append(" , '일요일' ,");
		builder.append(sunSales);
		builder.append("] ,[ '시간대1' ,");
		builder.append(t0006Sales);
		builder.append(" , '시간대2' ,");
		builder.append(t0611Sales);
		builder.append(" , '시간대3' ,");
		builder.append(t1114Sales);
		builder.append(" , '시간대4' ,");
		builder.append(t1417Sales);
		builder.append(" , '시간대5' ,");
		builder.append(t1721Sales);
		builder.append(" , '시간대6' ,");
		builder.append(t2124Sales);
		builder.append("] ,[ '남성' ,");
		builder.append(mSales);
		builder.append(" , '여성' ,");
		builder.append(wSales);
		builder.append("] ,[ '10대' ,");
		builder.append(a10Sales);
		builder.append(" , '20대' ,");
		builder.append(a20Sales);
		builder.append(" , '30대' ,");
		builder.append(a30Sales);
		builder.append(" , '40대' ,");
		builder.append(a40Sales);
		builder.append(" , '50대' ,");
		builder.append(a50Sales);
		builder.append(" , '60대 이상' ,");
		builder.append(a60Sales);
		builder.append("]]");
		return builder.toString();
	}
}
