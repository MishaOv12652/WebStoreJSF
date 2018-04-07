DROP SCHEMA IF EXISTS dreamDB;

CREATE SCHEMA IF NOT EXISTS dreamDB;

create table IF NOT EXISTS  dreamDB.actors
(
  id int not null auto_increment
    primary key,
  name varchar(50) null
)
;

INSERT INTO dreamDB.actors (name) VALUES ('Tom Hanks');
INSERT INTO dreamDB.actors (name) VALUES ('Robert De Niro ');
INSERT INTO dreamDB.actors (name) VALUES ('Jack Nicholson ');
INSERT INTO dreamDB.actors (name) VALUES (' Johnny Depp ');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.age_lvl
(
  id int not null auto_increment
    primary key,
  age_lvl varchar(10) null
)
;

INSERT INTO dreamDB.age_lvl (age_lvl) VALUES ('children');
INSERT INTO dreamDB.age_lvl (age_lvl) VALUES ('youth');
INSERT INTO dreamDB.age_lvl (age_lvl) VALUES ('elder');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.categories
(
  id int not null auto_increment
    primary key,
  category_name varchar(25) null
)
;

INSERT INTO dreamDB.categories (category_name) VALUES ('Books');
INSERT INTO dreamDB.categories (category_name) VALUES ('Cell Phones');
INSERT INTO dreamDB.categories (category_name) VALUES ('Computers and Tablets');
INSERT INTO dreamDB.categories (category_name) VALUES ('DVD And Movies');
INSERT INTO dreamDB.categories (category_name) VALUES ('Other');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.cellphone_brands
(
  id int not null auto_increment
    primary key,
  brand varchar(25) null
)
;

INSERT INTO dreamDB.cellphone_brands (brand) VALUES ('Samsung');
INSERT INTO dreamDB.cellphone_brands (brand) VALUES ('LG');
INSERT INTO dreamDB.cellphone_brands (brand) VALUES ('Huawai');
INSERT INTO dreamDB.cellphone_brands (brand) VALUES ('HTC');
INSERT INTO dreamDB.cellphone_brands (brand) VALUES ('Nokia');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.city
(
  id int not null auto_increment
    primary key,
  name varchar(25) null
)
;

INSERT INTO dreamDB.city (name) VALUES ('Netanya');
INSERT INTO dreamDB.city (name) VALUES ('Kfar Yona');
INSERT INTO dreamDB.city (name) VALUES ('Tel Aviv');
INSERT INTO dreamDB.city (name) VALUES ('Jerusalem');
INSERT INTO dreamDB.city (name) VALUES ('Haifa');
INSERT INTO dreamDB.city (name) VALUES ('Hadera');
INSERT INTO dreamDB.city (name) VALUES ('Eilat');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.cpu_list
(
  id int not null auto_increment
    primary key,
  cpu varchar(25) null
)
;

INSERT INTO dreamDB.cpu_list (cpu) VALUES ('i5 3400');
INSERT INTO dreamDB.cpu_list (cpu) VALUES ('i3 2100');
INSERT INTO dreamDB.cpu_list (cpu) VALUES ('i7 2500');
INSERT INTO dreamDB.cpu_list (cpu) VALUES ('Quad Core');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.credit_companies
(
  id int not null auto_increment
    primary key,
  name varchar(25) null
)
;

INSERT INTO dreamDB.credit_companies (name) VALUES ('Visa');
INSERT INTO dreamDB.credit_companies (name) VALUES ('MasterCard');
INSERT INTO dreamDB.credit_companies (name) VALUES ('American Express');
INSERT INTO dreamDB.credit_companies (name) VALUES ('Isra Card');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.directors
(
  id int not null auto_increment
    primary key,
  name varchar(50) null
)
;

INSERT INTO dreamDB.directors (name) VALUES (' Steven Spielberg ');
INSERT INTO dreamDB.directors (name) VALUES (' Martin Scorsese ');
INSERT INTO dreamDB.directors (name) VALUES (' Quentin Tarantino ');
INSERT INTO dreamDB.directors (name) VALUES (' Alfred Hitchcock ');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.genres
(
  id int not null auto_increment
    primary key,
  genre varchar(25) null
)
;

INSERT INTO dreamDB.genres (genre) VALUES ('Science Fiction');
INSERT INTO dreamDB.genres (genre) VALUES ('Drama');
INSERT INTO dreamDB.genres (genre) VALUES ('Satire');
INSERT INTO dreamDB.genres (genre) VALUES ('Action and Adventure');
INSERT INTO dreamDB.genres (genre) VALUES ('Horror');
INSERT INTO dreamDB.genres (genre) VALUES ('Romance');
INSERT INTO dreamDB.genres (genre) VALUES ('Mystery');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.gpu_list
(
  id int not null auto_increment
    primary key,
  gpu varchar(255) null
)
;

INSERT INTO dreamDB.gpu_list (gpu) VALUES ('Nvidia GeForce Titan V - 12GB');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('Nvidia GeForce Titan Xp	- 11GB');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('Nvidia GeForce GTX 1080 Ti - 8GB	');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('Nvidia GeForce GTX 1080 - 8GB');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('Nvidia GeForce GTX 1070 Ti - 8GB');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('AMD GeForce GTX 1070 - 8GB');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('AMD GeForce GTX 1070 - 8GB');
INSERT INTO dreamDB.gpu_list (gpu) VALUES ('AMD GeForce GTX 1070 - 8GB');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.known_authers
(
  id int not null auto_increment
    primary key,
  auther_name varchar(255) null
)
;

INSERT INTO dreamDB.known_authers (auther_name) VALUES ('J.K. Rowling');
INSERT INTO dreamDB.known_authers (auther_name) VALUES ('Stephan King');
INSERT INTO dreamDB.known_authers (auther_name) VALUES ('Mark Twein');
INSERT INTO dreamDB.known_authers (auther_name) VALUES ('Charles Dickens
');
INSERT INTO dreamDB.known_authers (auther_name) VALUES ('Leo Tolstoy');
INSERT INTO dreamDB.known_authers (auther_name) VALUES ('Agatha Christie
');
INSERT INTO dreamDB.known_authers (auther_name) VALUES ('Dr. Seuss');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.os_systems
(
  id int not null auto_increment
    primary key,
  os varchar(255) null
)
;

INSERT INTO dreamDB.os_systems (os) VALUES ('Android 6');
INSERT INTO dreamDB.os_systems (os) VALUES ('iOS');
INSERT INTO dreamDB.os_systems (os) VALUES ('Windows 10');
INSERT INTO dreamDB.os_systems (os) VALUES ('Linux');
INSERT INTO dreamDB.os_systems (os) VALUES ('Android 7');
INSERT INTO dreamDB.os_systems (os) VALUES ('Android 8');
INSERT INTO dreamDB.os_systems (os) VALUES ('Windows 7');
INSERT INTO dreamDB.os_systems (os) VALUES ('Windows 8.1');
INSERT INTO dreamDB.os_systems (os) VALUES ('Windows 8');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.product_condition
(
  id int not null auto_increment
    primary key,
  `condition` varchar(255) null
)
;

INSERT INTO dreamDB.product_condition (`condition`) VALUES ('New');
INSERT INTO dreamDB.product_condition (`condition`) VALUES ('Used');
INSERT INTO dreamDB.product_condition (`condition`) VALUES ('Seller Refurbished');
INSERT INTO dreamDB.product_condition (`condition`) VALUES ('Manufacture Refurbished');
INSERT INTO dreamDB.product_condition (`condition`) VALUES ('Not Working');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.storage_capacity
(
  id int not null auto_increment
    primary key,
  capacity varchar(8) null
)
;

INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('16');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('32');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('64');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('128');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('256');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('512');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('500');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('1000');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('2000');
INSERT INTO dreamDB.storage_capacity (capacity) VALUES ('3000');

-- auto-generated definition
create table IF NOT EXISTS dreamDB.storage_type
(
  id int not null auto_increment
    primary key,
  storage_type varchar(25) null
)
;

INSERT INTO dreamDB.storage_type (storage_type) VALUES ('SD Card');
INSERT INTO dreamDB.storage_type (storage_type) VALUES ('Micro SD Card');
INSERT INTO dreamDB.storage_type (storage_type) VALUES ('HDD');
INSERT INTO dreamDB.storage_type (storage_type) VALUES ('SSD');


-- auto-generated definition
create table IF NOT EXISTS dreamDB.account
(
  id int not null auto_increment
    primary key,
  email varchar(30) null,
  f_name varchar(20) null,
  l_name varchar(20) null,
  password varchar(20) null,
  phone_number varchar(10) null,
  city int null,
  address varchar(50) null,
  credit_card_number varchar(20) null,
  credit_card_comp int null,
  credit_card_exp varchar(6) null,
  zip int null,
  buyer_id int null,
  constraint User_email_uindex
  unique (email),
  constraint account_buyer_id_uindex
  unique (buyer_id),
  constraint user_city_id_fk
  foreign key (city) references dreamDB.city (id),
  constraint user_credit_companies_id_fk
  foreign key (credit_card_comp) references dreamDB.credit_companies (id)
)
;

create index user_city_id_fk
  on dreamDB.account (city)
;

create index user_credit_companies_id_fk
  on dreamDB.account (credit_card_comp)
;

-- auto-generated definition
create table IF NOT EXISTS dreamDB.books_specs
(
  id int not null auto_increment
    primary key,
  series varchar(25) null,
  age_lvl int null,
  author int null,
  genre int null,
  constraint books_specs_age_lvl_id_fk
  foreign key (age_lvl) references dreamDB.age_lvl (id),
  constraint books_specs_known_authers_id_fk
  foreign key (author) references dreamDB.known_authers (id),
  constraint books_specs_genres_id_fk
  foreign key (genre) references dreamDB.genres (id)
)
;

create index books_specs_age_lvl_id_fk
  on dreamDB.books_specs (age_lvl)
;

create index books_specs_genres_id_fk
  on dreamDB.books_specs (genre)
;

create index books_specs_known_authers_id_fk
  on dreamDB.books_specs (author)
;


-- auto-generated definition
create table IF NOT EXISTS dreamDB.cellphone_specs
(
  id int not null auto_increment
    primary key,
  screen_size double null,
  model varchar(25) null,
  ram int null,
  brand int null,
  mem_card_type int null,
  os int null,
  storage int null,
  battery_cap int null,
  constraint cellphone_specs_cellphone_brands_id_fk
  foreign key (brand) references dreamDB.cellphone_brands (id),
  constraint cellphone_specs_storage_type_id_fk
  foreign key (mem_card_type) references dreamDB.storage_type (id),
  constraint cellphone_specs_os_systems_id_fk
  foreign key (os) references dreamDB.os_systems (id),
  constraint cellphone_specs_storage_capacity_id_fk
  foreign key (storage) references dreamDB.storage_capacity (id)
)
;

create index cellphone_specs_cellphone_brands_id_fk
  on dreamDB.cellphone_specs (brand)
;


create index cellphone_specs_os_systems_id_fk
  on dreamDB.cellphone_specs (os)
;

create index cellphone_specs_storage_type_id_fk
  on dreamDB.cellphone_specs (mem_card_type)
;

create index cellphone_specs_storage_capacity_id_fk
  on dreamDB.cellphone_specs (storage)
;


-- auto-generated definition
create table IF NOT EXISTS dreamDB.computer_specs
(
  id int not null auto_increment
    primary key,
  type varchar(255) null,
  os int null,
  cpu int null,
  cpu_speed double null,
  ram int null,
  gpu int null,
  brand int null,
  screen_size double null,
  release_year int(4) null,
  hdd int null,
  ssd int null,
  model varchar(50) null,
  constraint computer_specs_os_systems_id_fk
  foreign key (os) references dreamDB.os_systems (id),
  constraint computer_specs_cpu_list_id_fk
  foreign key (cpu) references dreamDB.cpu_list (id),
  constraint computer_specs_gpu_list_id_fk
  foreign key (gpu) references dreamDB.gpu_list (id),
  constraint computer_specs_cellphone_brands_id_fk
  foreign key (brand) references dreamDB.cellphone_brands (id),
  constraint computer_specs_storage_capacity_id_fk_hdd
  foreign key (hdd) references dreamDB.storage_capacity (id),
  constraint computer_specs_storage_capacity_id_fk_ssd
  foreign key (ssd) references dreamDB.storage_capacity (id)
)
  comment 'also for tablets'
;

create index computer_specs_cellphone_brands_id_fk
  on dreamDB.computer_specs (brand)
;

create index computer_specs_cpu_list_id_fk
  on dreamDB.computer_specs (cpu)
;

create index computer_specs_gpu_list_id_fk
  on dreamDB.computer_specs (gpu)
;

create index computer_specs_os_systems_id_fk
  on dreamDB.computer_specs (os)
;

create index computer_specs_storage_capacity_id_fk_hdd
  on dreamDB.computer_specs (hdd)
;

create index computer_specs_storage_capacity_id_fk_ssd
  on dreamDB.computer_specs (ssd)
;


-- auto-generated definition
create table IF NOT EXISTS dreamDB.movie_specs
(
  id int not null auto_increment
    primary key,
  director int null,
  length int null,
  year int(4) null,
  actor int null,
  age_lvl int null,
  genre int null,
  constraint movie_specs_directors_id_fk
  foreign key (director) references dreamDB.directors (id),
  constraint movie_specs_actors_id_fk
  foreign key (actor) references dreamDB.actors (id),
  constraint movie_specs_age_lvl_id_fk
  foreign key (age_lvl) references dreamDB.age_lvl (id),
  constraint movie_specs_genres_id_fk
  foreign key (genre) references dreamDB.genres (id)
)
;

create index movie_specs_actors_id_fk
  on dreamDB.movie_specs (actor)
;

create index movie_specs_age_lvl_id_fk
  on dreamDB.movie_specs (age_lvl)
;

create index movie_specs_directors_id_fk
  on dreamDB.movie_specs (director)
;

create index movie_specs_genres_id_fk
  on dreamDB.movie_specs (genre)
;

-- auto-generated definition
create table IF NOT EXISTS dreamDB.products
(
  id int not null auto_increment
    primary key,
  name varchar(100) null,
  price float null,
  item_desc mediumtext null,
  category int null,
  condition_id int null,
  img blob null,
  seller_id int null,
  numOfItems int null,
  book_spec_id int null,
  cellphone_spec_id int null,
  computer_spec_id int null,
  movie_spec_id int null,
  constraint products_categories_id_fk
  foreign key (category) references dreamDB.categories (id),
  constraint products_product_condition_id_fk
  foreign key (condition_id) references dreamDB.product_condition (id),
  constraint products_user_id_fk
  foreign key (seller_id) references dreamDB.account (id),
  constraint products_books_specs_id_fk
  foreign key (book_spec_id) references dreamDB.books_specs (id),
  constraint products_cellphone_specs_id_fk
  foreign key (cellphone_spec_id) references dreamDB.cellphone_specs (id),
  constraint products_computer_specs_id_fk
  foreign key (computer_spec_id) references dreamDB.computer_specs (id),
  constraint products_movie_specs_id_fk
  foreign key (movie_spec_id) references dreamDB.movie_specs (id)
)
;

create index products_categories_id_fk
  on dreamDB.products (category)
;

create index products_product_condition_id_fk
  on dreamDB.products (condition_id)
;

create index products_user_id_fk
  on dreamDB.products (seller_id)
;

create index products_books_specs_id_fk
  on dreamDB.products (book_spec_id)
;

create index products_cellphone_specs_id_fk
  on dreamDB.products (cellphone_spec_id)
;

create index products_computer_specs_id_fk
  on dreamDB.products (computer_spec_id)
;

create index products_movie_specs_id_fk
  on dreamDB.products (movie_spec_id)
;
ALTER TABLE dreamDB.products ADD shippingPrice DOUBLE NULL;

-- auto-generated definition
create table IF NOT EXISTS dreamDB.wish_lists
(
  id int not null auto_increment
    primary key,
  buyer_id int null,
  constraint wish_lists_account_id_fk
  foreign key (buyer_id) references dreamDB.account (id)
)
;

create index wish_lists_account_id_fk
  on dreamDB.wish_lists (buyer_id)
;


create table IF NOT EXISTS dreamDB.wish_list_products
(
  wish_list_id INT NOT NULL,
  product_id   INT NOT NULL,
  PRIMARY KEY (wish_list_id,product_id),
  CONSTRAINT wish_list_products_wish_lists_id_fk FOREIGN KEY (wish_list_id) REFERENCES dreamDB.wish_lists (id),
  CONSTRAINT wish_list_products_products_id_fk FOREIGN KEY (product_id) REFERENCES dreamDB.products (id)
);

-- auto-generated definition
create table IF NOT EXISTS dreamDB.shopping_carts
(
  id int not null auto_increment
    primary key,
  buyer_id int null,
  constraint shopping_carts_account_id_fk
  foreign key (buyer_id) references dreamDB.account (id)
)
;

create index shopping_carts_account_id_fk
  on dreamDB.shopping_carts (buyer_id)
;

create table IF NOT EXISTS dreamDB.shopping_cart_products
(
  shopping_cart_id INT NOT NULL,
  product_id INT NOT NULL,
  numOfItems INT,
  CONSTRAINT PRIMARY KEY (product_id,shopping_cart_id),
  CONSTRAINT shopping_cart_products_shopping_carts_id_fk FOREIGN KEY (shopping_cart_id) REFERENCES dreamDB.shopping_carts (id),
  CONSTRAINT shopping_cart_products_products_id_fk FOREIGN KEY (product_id) REFERENCES dreamDB.products (id)
);
ALTER TABLE dreamdb.shopping_carts ADD price_no_shipping DOUBLE NULL;
ALTER TABLE dreamdb.shopping_carts ADD shipping_price DOUBLE NULL;
ALTER TABLE dreamdb.shopping_carts ADD total_price DOUBLE NULL;



