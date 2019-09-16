drop table Channel;

drop table AREA_CHANGE_INDEX;

drop table FOOT_TRAFFIC_DETAIL;

CREATE TABLE CHANNEL
(
  year        VARCHAR2(100),
  quarter     VARCHAR2(100),
  division_code      VARCHAR2(100),
  division_name      VARCHAR2(100),
  area_code      VARCHAR2(100),
  area_name  VARCHAR2(100),
  business_code    VARCHAR2(100),
  business_name    VARCHAR2(100),
  shopCnt     NUMBER,
  similrCnt   NUMBER,    
  openCnt     NUMBER,      
  closeCnt    NUMBER,     
  frcShopCnt  NUMBER    
);

CREATE TABLE AREA_CHANGE_INDEX
(
  year        VARCHAR2(100),
  quarter     VARCHAR2(100),
  division_code      VARCHAR2(100),
  division_name      VARCHAR2(100),
  area_code      VARCHAR2(100),
  area_name  VARCHAR2(100),
  area_change_code    VARCHAR2(100),
  area_change_name    VARCHAR2(100),
  opencnt     NUMBER,
  closecnt   NUMBER,    
  seoul_OpenCnt    NUMBER,     
  seoul_CloseCnt  NUMBER    
);

CREATE TABLE FOOT_TRAFFIC_DETAIL
(
  year        VARCHAR2(100),
  quarter     VARCHAR2(100),
  division_code      VARCHAR2(100),
  division_name      VARCHAR2(100),
  area_code      VARCHAR2(100),
  area_name  VARCHAR2(100),
  sex    VARCHAR2(100),
  age    VARCHAR2(100),
  day     VARCHAR2(100),
  times	  VARCHAR2(100),   
  ft    NUMBER
);

create table sales(
	area_code VARCHAR2(100),
	year VARCHAR2(100),
	quater VARCHAR2(100),
	business VARCHAR2(100),
	monthSales VARCHAR2(100),
	wkSales VARCHAR2(100),
	wkendSales VARCHAR2(100),
	monSales VARCHAR2(100),
	tueSales VARCHAR2(100),
	wedSales VARCHAR2(100),
	thuSales VARCHAR2(100),
	friSales VARCHAR2(100),
	satSales VARCHAR2(100),
	sunSales VARCHAR2(100),
	t0006Sales VARCHAR2(100),
	t0611Sales VARCHAR2(100),
	t1114Sales VARCHAR2(100),
	t1417Sales VARCHAR2(100),
	t1721Sales VARCHAR2(100),
	t2124Sales VARCHAR2(100),
	mSales VARCHAR2(100),
	wSales VARCHAR2(100),
	a10Sales VARCHAR2(100),
	a20Sales VARCHAR2(100),
	a30Sales VARCHAR2(100),
	a40Sales VARCHAR2(100),
	a50Sales VARCHAR2(100),
	a60Sales VARCHAR2(100),
	monthNum VARCHAR2(100),
	wkNum VARCHAR2(100),
	wkendNum VARCHAR2(100),
	monNum VARCHAR2(100),
	tueNum VARCHAR2(100),
	wedNum VARCHAR2(100),
	thuNum VARCHAR2(100),
	friNum VARCHAR2(100),
	satNum VARCHAR2(100),
	sunNum VARCHAR2(100),
	t0006Num VARCHAR2(100),
	t0611Num VARCHAR2(100),
	t1114Num VARCHAR2(100),
	t1417Num VARCHAR2(100),
	t1721Num VARCHAR2(100),
	t2124Num VARCHAR2(100),
	mNum VARCHAR2(100),
	wNum VARCHAR2(100),
	a10Num VARCHAR2(100),
	a20Num VARCHAR2(100),
	a30Num VARCHAR2(100),
	a40Num VARCHAR2(100),
	a50Num VARCHAR2(100),
	a60Num VARCHAR2(100),
	storeNum VARCHAR2(100)
);

create table ft
(
	area_code VARCHAR2(100),
	year VARCHAR2(100),
	quater VARCHAR2(100),
	sex VARCHAR2(100),
	age VARCHAR2(100),
	times VARCHAR2(100),
	day VARCHAR2(100),
	ft VARCHAR2(100)
);

create table pop
(
	area_code VARCHAR2(100),
	year VARCHAR2(100),
	quater VARCHAR2(100),
	sex VARCHAR2(100),
	age VARCHAR2(100),
	pop VARCHAR2(100)
);

create table area(
	division_code varchar2(100),
	division_name varchar2(100),
	area_code varchar2(20),
	area_name varchar2(100),
    sigungu varchar2(100)
);

alter table sales add constraint area_pk primary key(year, quater, area_code, business);

ALTER TABLE sales  ADD FOREIGN KEY (area_code) REFERENCES area  (area_code);


ALTER TABLE CHANNEL  ADD FOREIGN KEY (area_code) REFERENCES area  (area_code);

alter table CHANNEL add constraint CHANNEL_pk primary key(year, quarter, division_code, division_name, area_code, area_name, business_code, business_name); 

alter table AREA_CHANGE_INDEX add constraint AREA_CHANGE_INDEX_pk primary key(year, quarter, division_code, division_name, area_code, area_name, area_change_code, area_change_name);

alter table FOOT_TRAFFIC_DETAIL add constraint FOOT_TRAFFIC_DETAIL_pk primary key(year, quarter, division_code, division_name, area_code, area_name, sex, age, day, times);

