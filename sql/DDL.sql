drop table area cascade constraint;

drop table CHANNEL cascade constraint;
--점포
CREATE TABLE CHANNEL
(
  year        VARCHAR2(100),
  quarter     VARCHAR2(100),
  area_id      VARCHAR2(100),
  business    VARCHAR2(100),
  shopCnt     NUMBER,
  similrCnt   NUMBER,    
  openCnt     NUMBER,      
  closeCnt    NUMBER,     
  frcShopCnt  NUMBER    
);
--지역정보(Master table)
create table area(
	division_id varchar2(20),
	division_name varchar2(100),
	area_id varchar2(20) primary key,
	area_name varchar2(100),
	city_id varchar2(12)
);


ALTER TABLE CHANNEL  ADD FOREIGN KEY (area_id) REFERENCES area  (area_id);