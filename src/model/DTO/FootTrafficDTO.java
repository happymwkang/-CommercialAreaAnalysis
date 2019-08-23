package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootTrafficDTO {
	private String year;
	private String quarter;
	private String level2Name;
	private double ftCnt;
	private double mFtCnt;
	private double wFtCnt;
	private double a10FtCnt;
	private double a20FtCnt;
	private double a30FtCnt;
	private double a40FtCnt;
	private double a50FtCnt;
	private double a60FtCnt;
	private double t1FtCnt;
	private double t2FtCnt;
	private double t3FtCnt;
	private double t4FtCnt;
	private double t5FtCnt;
	private double t6FtCnt;
	private double monFtCnt;
	private double tueFtCnt;
	private double wedFtCnt;
	private double thuFtCnt;
	private double friFtCnt;
	private double satFtCnt;
	private double sunFtCnt;
}
