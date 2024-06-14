create table locations (id bigint auto_increment, loc_name varchar(50), lat double, lon double, primary key (id));

insert into locations(loc_name, lat, lon) values ('CHICAGO', 41.52, -87.47);
insert into locations(loc_name, lat, lon) values ('WASHINGTON', 38.52, -77.02);