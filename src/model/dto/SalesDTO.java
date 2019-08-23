package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class SalesDTO {
	//private String division;//상권구분 1
	private String id;
	//private String name;
	private String year;
	private String quater;
	private String business;//업종 6
	private String monthSales;//월매출  7
	private String wkSales;//주중매출 8
	private String wkendSales;//주말매출 9
	private String monSales;//월요일 10
	private String tueSales;//화 11
	private String wedSales;
	private String thuSales;
	private String friSales;
	private String satSales;
	private String sunSales;
	private String t0006Sales; //시간대별 매출 17
	private String t0611Sales;
	private String t1114Sales;
	private String t1417Sales;
	private String t1721Sales;
	private String t2124Sales;
	private String mSales; //남성매출
	private String wSales; //여성매출 24
	private String a10Sales; //10대 매출
	private String a20Sales;
	private String a30Sales;
	private String a40Sales;
	private String a50Sales;
	private String a60Sales;
	private String monthNum;//월건수 31
	private String wkNum;//주중건스
	private String wkendNum;//주말건수
	private String monNum;//월요일
	private String tueNum;//화
	private String wedNum;
	private String thuNum;
	private String friNum;
	private String satNum;
	private String sunNum;
	private String t0006Num; //시간대별 건수 41
	private String t0611Num;
	private String t1114Num;
	private String t1417Num;
	private String t1721Num;
	private String t2124Num;
	private String mNum; //남성건수 47
	private String wNum; //여성건수
	private String a10Num; //10대 건수
	private String a20Num;
	private String a30Num;
	private String a40Num;
	private String a50Num;
	private String a60Num;
	private String storeNum; //점포수 55
}
