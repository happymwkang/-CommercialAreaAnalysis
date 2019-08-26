drop table channel;
drop table area;
alter database commercial character set utf8;
alter table area convert to character set utf8;

CREATE TABLE CHANNEL
(
  year varchar(100),
  quarter varchar(100),
  area_id varchar(100),
  business varchar(100),
  shopCnt varchar(100),
  similrCnt varchar(100),    
  openCnt varchar(100),      
  closeCnt varchar(100),     
  frcShopCnt varchar(100)    
);

create table area(
	division_code varchar(100),
	division_name varchar(100),
	area_code varchar(20) primary key,
	area_name varchar(100),
    sigungu varchar(100)
    character set utf8
);

create table sales(
	area_id varchar(100),
	year varchar(100),
	quater varchar(100),
	business varchar(100),
	monthSales varchar(100),
	wkSales varchar(100),
	wkendSales varchar(100),
	monSales varchar(100),
	tueSales varchar(100),
	wedSales varchar(100),
	thuSales varchar(100),
	friSales varchar(100),
	satSales varchar(100),
	sunSales varchar(100),
	t0006Sales varchar(100),
	t0611Sales varchar(100),
	t1114Sales varchar(100),
	t1417Sales varchar(100),
	t1721Sales varchar(100),
	t2124Sales varchar(100),
	mSales varchar(100),
	wSales varchar(100),
	a10Sales varchar(100),
	a20Sales varchar(100),
	a30Sales varchar(100),
	a40Sales varchar(100),
	a50Sales varchar(100),
	a60Sales varchar(100),
	monthNum varchar(100),
	wkNum varchar(100),
	wkendNum varchar(100),
	monNum varchar(100),
	tueNum varchar(100),
	wedNum varchar(100),
	thuNum varchar(100),
	friNum varchar(100),
	satNum varchar(100),
	sunNum varchar(100),
	t0006Num varchar(100),
	t0611Num varchar(100),
	t1114Num varchar(100),
	t1417Num varchar(100),
	t1721Num varchar(100),
	t2124Num varchar(100),
	mNum varchar(100),
	wNum varchar(100),
	a10Num varchar(100),
	a20Num varchar(100),
	a30Num varchar(100),
	a40Num varchar(100),
	a50Num varchar(100),
	a60Num varchar(100),
	storeNum varchar(100)
);

create table ft
(
	area_id varchar(100),
	year varchar(100),
	quater varchar(100),
	sex varchar(100),
	age varchar(100),
	times varchar(100),
	day varchar(100),
	ft varchar(100)
);

create table pop
(
	area_id varchar(100),
	year varchar(100),
	quater varchar(100),
	sex varchar(100),
	age varchar(100),
	pop varchar(100)
);

drop procedure if exists resPopByAge;
delimiter $$
create procedure resPopByAge(
	in year varchar(100),
    in quater varchar(100),
    in area_id varchar(100),
    in sex varchar(100),
    out res10  varchar(100),
    out res20  varchar(100),
    out res30  varchar(100),
    out res40  varchar(100),
    out res50  varchar(100),
    out res60  varchar(100)
)
begin
	select pop into res10 from pop where year=year and area_id=area_id and quater=quater and sex='FAG' and age = 10;
    select pop into res20 from pop where year=year and area_id=area_id and quater=quater and sex='FAG' and age = 20;
    select pop into res30 from pop where year=year and area_id=area_id and quater=quater and sex='FAG' and age = 30;
    select pop into res40 from pop where year=year and area_id=area_id and quater=quater and sex='FAG' and age = 40;
    select pop into res50 from pop where year=year and area_id=area_id and quater=quater and sex='FAG' and age = 50;
    select pop into res60 from pop where year=year and area_id=area_id and quater=quater and sex='FAG' and age = 60;
end$$
delimiter ;
    

ALTER TABLE CHANNEL  ADD FOREIGN KEY (area_id) REFERENCES area  (area_id);