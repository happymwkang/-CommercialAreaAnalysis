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


ALTER TABLE CHANNEL  ADD FOREIGN KEY (area_id) REFERENCES area  (area_id);