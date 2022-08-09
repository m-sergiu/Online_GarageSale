create table if not exists asset
(
	id bigint not null auto_increment,
    category varchar(255),
    price decimal(10,2),
    quantity integer,
    primary key(id)
);

create table if not exists issue
(
	id bigint not null auto_increment,
    description varchar(255),
    asset_id bigint,
    primary key(id),
    foreign key(asset_id) references asset(id)
);

create table if not exists card
(
	id bigint not null auto_increment,
    cardNumber varchar(255),
    cardHolderName varchar(255),
    civ varchar(255),
    year integer,
    month integer,
    balance decimal(10,2),
    card_type varchar(255),
    purchaseOrder_id bigint,
    primary key(id)
);

create table if not exists purchaseOrder
(
	id bigint not null auto_increment,
    customerName varchar(255),
    customerEmail varchar(255),
    card_id bigint,
    purchaseBalance decimal(10,2),
    dateTime dateTime,
    primary key(id),
    foreign key(card_id) references card(id)
);

create table if not exists asset_purchaseOrder
(
    asset_id bigint not null,
    purchaseOrder_id bigint not null,
    primary key(asset_id, purchaseOrder_id)
);

