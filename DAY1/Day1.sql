create database bankingAPP;

use bankingAPP;

create table Bank(
bank_id INT Primary key auto_increment,
bank_name varchar(100) not null,
bank_branch varchar(100) not null 
);

insert into Bank(bank_name, bank_branch)values(
'ABC','Main Branch'
);

insert into Bank(bank_name, bank_branch)values(
'XYZ','Main Branch'
);

select * from Bank;

create table Account(
account_id INT PRIMARY KEY UNIQUE auto_increment,
customer_id INT NOT NULL,
bank_id INT,
account_type varchar(50) NOT NULL,
balance decimal(15,2) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY(bank_id) references Bank(bank_id)
);

create table SavingsAccount(
account_id int primary key,
interest_rate decimal(5,2) not null,
foreign key(account_id) references account(account_id)
);

create table CurrentAccount(
account_id int primary key,
overdraft_limit decimal(5,2) not null,
foreign key(account_id) references account(account_id)
);

create table Transaction(
transaction_id int primary key auto_increment,
account_id int not null,
transaction_type varchar(50) not null,
amount decimal(15,2) not null,
transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
foreign key(account_id) references Account(account_id)
);

create table DepositTransaction(
transaction_id INT PRIMARY KEY,
deposit_method varchar(50) NOT NULL,
FOREIGN KEY(transaction_id) references Transaction(transaction_id)
);

create table WithdrawTransaction(
transaction_id INT PRIMARY KEY,
withdraw_method varchar(50) NOT NULL,
FOREIGN KEY(transaction_id) references Transaction(transaction_id)
);

create database sample;
use sample;
create table users(
username varchar(100),
password varchar(100),
user_id int);
select * from users;