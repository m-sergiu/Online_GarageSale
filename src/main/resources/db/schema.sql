create table asset
(
	id bigint not null auto_increment,
    category varchar(255),
    price decimal(10,2),
    quantity integer,
    purchaseOrder_id bigint,
    primary key(id),
    foreign key(purchaseOrder_id) references purchaseOrder(id)
);
create table issue
(
	id bigint not null auto_increment,
    description varchar(255),
    asset_id bigint,
    primary key(id)
);
create table card
(
	id bigint not null auto_increment,
    cardNumber varchar(255),
    cardHolderName varchar(255),
    civ varchar(255),
    year integer,
    month integer,
    balance decimal(10,2),
    card_type varchar(255);
    purchaseOrder_id bigint,
    primary key(id)
);
create table purchaseOrder
(
	id bigint not null auto_increment,
    customerName varchar(255),
    customerEmail varchar(255),
    card_id bigint,
    purchaseBalance decimal(10,2),
    primary key(id)
);

alter table asset
	add constraint FK_purchaseOrder_id foreign key(purchaseOrder_id) references purchaseOrder(id);
alter table purchaseOrder
	add constraint FK_card_id foreign key(card_id) references card(id);
alter table card
	add constraint FK_purchaseOrder_card_id foreign key(purchaseOrder_id) references purchaseOrder(id);
alter table issue
	add constraint FK_asset_id foreign key(asset_id) references asset(id);