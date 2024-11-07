if not exists(select * from sys.databases where name = 'didaticsweb')
	create database didaticsweb
go

use didaticsweb
go

insert user_role values (2, 'Free'), (10, 'Simple'), (1000 * 1000, 'Premium')
select * from user_role