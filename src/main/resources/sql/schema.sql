drop database if exists hardware_managment_system;
create database hardware_managment_system;

use hardware_managment_system;

CREATE TABLE customer(
                         id VARCHAR(6) PRIMARY KEY,
                         name VARCHAR(30),
                         address VARCHAR(30),
                         email varchar(30) unique key,
                        contactnumber varchar(10) unique key
);

CREATE TABLE orders
(
    id         VARCHAR(6) PRIMARY KEY,
    date       DATE,
    customerId VARCHAR(6) NOT NULL,

    CONSTRAINT FOREIGN KEY (customerId) REFERENCES customer (id) on Delete Cascade on Update Cascade


);

CREATE TABLE item(
                     code VARCHAR(6) PRIMARY KEY,
                     description VARCHAR(50) unique key,
                     unitPrice varchar(10),
                     qtyOnHand varchar(5)

);

CREATE TABLE order_detail
(
    orderId VARCHAR(6),
    description VARCHAR(50) ,
    qty int(10),
    unitprice int(20),
    amount varchar(20),

    CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders (id) on Delete Cascade on Update Cascade


);



create table Supplier(
SID VARCHAR (10) primary key,
SName varchar (20),
contactnumber varchar (15),
imeladdress varchar (35)
);


create table Supplierdetails(
SID VARCHAR (10),
 code VARCHAR(6),
price varchar (20),
description varchar (29),
    qtyonHenad varchar (30),
 foreign key (SID) references Supplier (SID),
foreign key (code) references item (code)
);


create table payment(
p_code varchar (20)primary key,
amount varchar (29),
pdate DATE,
email varchar(30) unique key,

foreign key (email) references customer (email)


);


CREATE TABLE Transport (
T_id varchar (15) primary key ,
T_QTY varchar (20)
);

create table Transportdetails(
                                 T_area varchar (17),
                                 t_time varchar (10),
                                 T_id varchar (15),
                                 id VARCHAR (6) primary key ,
                                 T_Date date,
                                 foreign key (T_id) references Vehical(VID)
);

create table Vehical (
VID VARCHAR (19) PRIMARY KEY ,
Vmodel varchar (25)

);


create table Employee (
eid varchar (20) primary key ,
contactnumber varchar (20) unique key ,
address varchar (28),
name varchar (25)
);


create table Mechine (
code varchar (14) primary key ,
Model varchar (38)

);
create table admin(
username varchar (7) primary key,
email varchar(50),
password varchar (12)
);

insert into admin values ('Dinu', 'dinu@gmail.com', '1234');
