drop table CHANNEL cascade constraint;

drop table area cascade constraint;

CREATE TABLE CHANNEL
(
  year VARCHAR2(100),
  quarter VARCHAR2(100),
  area_id VARCHAR2(100),
  business VARCHAR2(100),
  shopCnt NUMBER,
  similrCnt NUMBER,    
  openCnt NUMBER,      
  closeCnt NUMBER,     
  frcShopCnt NUMBER    
);

create table area(
	division_name varchar2(100),
	area_id varchar2(20) primary key,
	area_name varchar2(100)
);

create table sales(
	area_id VARCHAR2(100),
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
	area_id VARCHAR2(100),
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
	area_id VARCHAR2(100),
	year VARCHAR2(100),
	quater VARCHAR2(100),
	sex VARCHAR2(100),
	age VARCHAR2(100),
	pop VARCHAR2(100)
);

ALTER TABLE CHANNEL  ADD FOREIGN KEY (area_id) REFERENCES area  (area_id);