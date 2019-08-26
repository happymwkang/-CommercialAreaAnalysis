
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


alter table CHANNEL add constraint CHANNEL_pk primary key(year, quarter, division_code, division_name, area_code, area_name, business_code, business_name); 

alter table AREA_CHANGE_INDEX add constraint AREA_CHANGE_INDEX_pk primary key(year, quarter, division_code, division_name, area_code, area_name, area_change_code, area_change_name);

alter table FOOT_TRAFFIC_DETAIL add constraint FOOT_TRAFFIC_DETAIL_pk primary key(year, quarter, division_code, division_name, area_code, area_name, sex, age, day, times);
