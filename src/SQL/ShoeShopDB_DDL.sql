drop database if exists shoe_shop_db;
create database shoe_shop_db;
use shoe_shop_db;

CREATE table Brand (
	ID int not null auto_increment primary key,
	name varchar(30) not null
);

CREATE table Shoe (
	ID int not null auto_increment primary key,
	name varchar(30) not null,
	brandID int not null,
	color varchar(30),
	shoe_size int not null,
	price double not null,
    stock int not null,
	foreign key (brandID) references Brand(ID)
);

CREATE table Category (
	ID int not null auto_increment primary key,
	name varchar(30)
);

CREATE table Belongs_to_Category (
	ID int not null auto_increment primary key,
	shoeID int not null,
	categoryID int not null,
	foreign key (shoeID) references Shoe(ID),
	foreign key (categoryID) references Category(ID)
);

CREATE table Customer (
	ID int not null auto_increment primary key,
	name varchar(50) not null,
    password varchar(50) not null,
	address varchar(30) not null,
    city varchar(30) not null,
	email varchar(50) not null
);

CREATE table Placed_Order (
	ID int not null auto_increment primary key,
	orderdate timestamp default current_timestamp,
	customerID int not null,
    expedited boolean not null default false,
	foreign key (customerID) references Customer(ID)
);

CREATE table order_content (
	ID int not null auto_increment primary key,
	shoeID int not null,
	placed_orderID int not null,
	foreign key (shoeID) references Shoe(ID),
	foreign key (placed_orderID) references Placed_Order(ID)
);

CREATE table Grade (
	ID int not null auto_increment primary key,
	rating varchar(30) not null	
);

CREATE table Review (
	ID int not null auto_increment primary key,
	shoeID int not null,
	customerID int not null,
	gradeID int not null,
    comment varchar(140),
	foreign key (shoeID) references Shoe(ID),
	foreign key (customerID) references Customer(ID),
	foreign key (gradeID) references Grade(ID)
);

CREATE table OutOfStock (
	id int not null auto_increment primary key,
	shoeID int not null,
	date timestamp default current_timestamp
);


INSERT into Brand (name) values
	('Balenciaga'), ('Converse'), ('Nike'), ('Crocs'), ('Vans'), ('Adidas');


INSERT into Category (name) values
	('Sport'), ('Premium'), ('Casual'), ('Kids'), ('Football'), ('Running'), ('Men'), ('Women'), ('Unisex');


INSERT into Shoe (name, brandID, color, shoe_size, price, stock) values
	('SUPERSTAR', (select ID from Brand where name = 'ADIDAS'), 'White', 45, 799.50, 1),
	('SWIFT RUN', (select ID from Brand where name = 'ADIDAS'), 'Black', 42, 719.50, 5),
	('Stan Smith', (select ID from Brand where name = 'ADIDAS'), 'White', 41, 764.00, 10),
	('Chuck Taylor All Star Hi-Tops', (select ID from Brand where name = 'Converse'), 'Black', 40, 679.00, 3),
	('Chuck Taylor All Star Hi-Tops', (select ID from Brand where name = 'Converse'), 'White', 52, 679.00, 6),
	('Chuck Taylor All Star Hi-Tops', (select ID from Brand where name = 'Converse'), 'Green', 43, 679.00, 4),
	('Chuck Taylor All Star Hi-Tops', (select ID from Brand where name = 'Converse'), 'Black', 40, 679.00, 25),
	('CROCBAND', (select ID from Brand where name = 'CROCS'), 'Grey', 31, 499.00, 2),
	('CROCBAND', (select ID from Brand where name = 'CROCS'), 'Black', 32, 499.00, 8),
	('CROCBAND', (select ID from Brand where name = 'CROCS'), 'Green', 43, 499.00, 7),
	('CROCBAND', (select ID from Brand where name = 'CROCS'), 'Blue', 43, 499.00, 14),
	('AIR MAX', (select ID from Brand where name = 'NIKE'), 'White', 43, 499.00, 3),
	('MERCURIAL', (select ID from Brand where name = 'NIKE'), 'Black', 43, 499.00, 9),
	('AIR VAPORMAX', (select ID from Brand where name = 'NIKE'), 'Grey', 47, 2145.00, 10),
	('Triple S low-top trainers', (select ID from Brand where name = 'BALENCIAGA'), 'Grey', 43, 7442.35, 1),
	('SK8-HI', (select ID from Brand where name = 'VANS'), 'Red', 44, 699.00, 4),
	('OLD SKOOL', (select ID from Brand where name = 'VANS'), 'Black', 40, 719.00, 1);
	
	
INSERT into Belongs_to_Category (shoeID, categoryID) values
	(1,3), (1,7),
	(2,3), (2,8), (2,1),
	(3,1), (3,7),
	(4,3), (4,9),
	(5,3), (5,9),
	(6,3), (6,9),
	(7,3), (7,9),
	(8,3), (8,9), (8,4),
	(9,3), (9,9), (9,4),
	(10,3), (10,9),
	(11,3), (11,9),
	(12,1), (12,3), (12,9),
	(13,1), (13,5), (13,7),
	(14,1), (14,6), (14,9),
	(15,2), (15,9),
	(16,3), (16,9),
	(17,3), (17,9);
	
	
INSERT into Customer (name, password, address, city, email) values 
	('Marcus Damsgaard Jensen', 'password123', 'Ringvägen 19', 'Stockholm', 'Marcus_DJ@randomemailclient.com'),
	('Kalle Karlsson', 'password123', 'Kakelstigen 61', 'Stockholm', 'Kalle_K@randomemailclient.com'),
	('Sara Svensson', 'password123', 'Lindegårdsvägen 21', 'Bålsta', 'Sara_S@randomemailclient.com'),
	('Ami Elsert', 'password123', 'Näldenvägen 14', 'Östersund', 'Ami_E@randomemailclient.com'),
	('Andreas Löfstrand', 'password123', 'Pettersbergsvägen 21', 'Stockholm', 'Andreas_L@randomemailclient.com');
	
	
INSERT into Placed_Order (customerID, orderdate, expedited) values
	(1, '2018-11-20 00:00:00', true), (1, '2018-11-21 00:00:00', true), (2, '2018-11-22 00:00:00', true), 
	(3, '2018-11-20 00:00:00', true), (4, '2016-10-20 00:00:00', true), (4, current_timestamp, true), (4, '2018-01-01 00:00:00', true), 
	(5, '2018-11-21 00:00:00', true), (5, current_timestamp, true), (5, current_timestamp, true);
	

INSERT into order_content(placed_orderID, shoeID) values
	(1,4), (1,1), (1,2), (2,17), (3,1), (4,9), (5,10), (6,15), (7,16), (8,5), (9,12), (10,3);
		

INSERT into Grade (rating) values
	('Very Dissatisfied'), ('Dissatisfied'), ('Ambivalent'), ('Satisfied'), ('Verry Satisfied');
	
	
INSERT into Review (GradeID, CustomerID, ShoeID) values
	(1,1,1), (2,1,17), (3,5,3), (3,2,1), (3,3,1);
	

SET @@global.time_zone = '+01:00';
SET @@session.time_zone = '+01:00';